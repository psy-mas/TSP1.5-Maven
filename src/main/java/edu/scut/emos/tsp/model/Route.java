package edu.scut.emos.tsp.model;

import edu.scut.emos.tsp.tools.Parameters;
import edu.scut.emos.tsp.tools.RouteTools;
import edu.scut.emos.tsp.tools.TimeCompare;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author emos
 *
 */
public class Route {

    //已装载列表
    private LinkedList<ScheduleTask> loadedTasks;
    //计划装载列表
    private LinkedList<ScheduleTask> planTasks;
    // 车辆路线列表
    private LinkedList<ScheduleTask> tasks;

    private double cost;
    private double distanceCost;
    private double waitTimeCost;
    private double delayTimeCost;
    private double drawOutCost;


    public Route() {
        this.loadedTasks = new LinkedList<>();
        this.planTasks = new LinkedList<>();
        this.tasks = new LinkedList<>();

        this.cost = 0;
        this.distanceCost = 0;
        this.waitTimeCost = 0;
        this.delayTimeCost = 0;
        this.drawOutCost = 0;
    }

    public Route(LinkedList<ScheduleTask> doneTasks, LinkedList<ScheduleTask> planTasks) {
        // 将已完成的任务列表转，转化为 已装载在车的任务列表
        this.loadedTasks = RouteTools.loadedTaskInVehicle(doneTasks);

        // 获取计划任务列表的拷贝
        this.planTasks = new LinkedList<>();
        this.planTasks.addAll(planTasks);

        // 获取车辆整个路线列表
        this.tasks = new LinkedList<>();
        this.tasks.addAll(this.loadedTasks);
        this.tasks.addAll(this.planTasks);

        this.cost = 0;
        this.distanceCost = 0;
        this.waitTimeCost = 0;
        this.delayTimeCost = 0;
        this.drawOutCost = 0;
    }

    //将一个新任务插入到计划装载列表
    public void insertAt(int index, ScheduleTask newTask) {
        try {
            this.planTasks.add(index, newTask);
        } catch (Exception e) {
            System.out.println("error with index");
        }
    }

    /**
     * 计算某个订单插入车辆路线后是否合适
     *
     * @param vehicle 行驶路线的车辆对象
     * @param pIndex  计划任务列表中插入订单的取货顺序号
     * @param dIndex  计划任务列表中插入订单的送货顺序号
     * @return 该订单插入是否合适的布尔值
     */
    public boolean isInsertFeasible(Vehicle vehicle, int pIndex, int dIndex) {
        if (pIndex < dIndex && pIndex >= 0 && dIndex < this.planTasks.size()) {
            // 该订单取货之前，车辆所装载的任务
            LinkedList<ScheduleTask> doneTask = RouteTools.loadedTaskInVehicle(tasks.subList(0, pIndex + this.loadedTasks.size()));
            double loadedWeight = 0;
            double loadedVolume = 0;

            for (ScheduleTask st : doneTask) {
                loadedWeight += st.getOrder().getWeightOfGoods();
                loadedVolume += st.getOrder().getVolumeOfGoods();
            }

            // 车辆的载重和体积可以满足插入订单的需求上货量
            return loadedWeight + this.planTasks.get(pIndex).getOrder().getWeightOfGoods() <= vehicle.getMaxWeight()
                    && loadedVolume + this.planTasks.get(pIndex).getOrder().getVolumeOfGoods() <= vehicle.getMaxVolume();

        } else {
            return false;
        }
    }

    // 返回计划服务顺序列表中的任务数量
    public int numOfPlanTasks() {
        return this.planTasks.size();
    }

    public ScheduleTask remove(int index) {
        try {
            return this.planTasks.remove(index);
        } catch (Exception e) {
            System.out.println("error with index");
        }

        return null;
    }

    /**
     * 计算车辆行驶该路线的成本,若参数为null，则返回当前的cost
     *
     * @param vehicle      待计算成本的车辆对象
     * @param positionMaps 存储两个位置之间距离和时间的映射
     * @return 该车辆行驶该路线的成本[0, 1]
     */
    public double getCost(Vehicle vehicle, HashMap<DTKey, DTValue> positionMaps) {
        Date start = new Date();

        if (vehicle != null && positionMaps != null) {
            this.distanceCost = this.computeDistanceCost(vehicle, positionMaps);
            if (this.distanceCost == 1) {
                this.cost = 1;
                return this.cost;
            }

            double[] timeCost = this.computeWaitTimeAndDelayTimeCost(vehicle, positionMaps);
            this.waitTimeCost = timeCost[0];
            this.delayTimeCost = timeCost[1];
            if (this.waitTimeCost == 1 || this.delayTimeCost == 1) {
                this.cost = 1;
                return this.cost;
            }

            this.drawOutCost = this.computeDrawOutTimeCost(vehicle);
            if (this.drawOutCost == 1) {
                this.cost = 1;
                return this.cost;
            }

            this.cost = Parameters.DISTANCE_WEIGHT * this.distanceCost
                    + Parameters.WAIT_TIME_WEIGHT * this.waitTimeCost
                    + Parameters.DELAY_TIME_WEIGHT * this.delayTimeCost
                    + Parameters.DRAW_OUT_TIME_WEIGHT * this.drawOutCost;
        }

        Date end = new Date();
        System.out.println("cost: " + (end.getTime() - start.getTime()));
        return this.cost;
    }

    /**
     * 计算车辆行驶该路线时距离部分的成本
     *
     * @param vehicle      待计算成本的车辆对象
     * @param positionMaps 存储两个位置之间距离和时间的映射
     * @return 归一化后的距离成本[0, 1]
     */
    private double computeDistanceCost(Vehicle vehicle, HashMap<DTKey, DTValue> positionMaps) {
        Position one = vehicle.getPosition();
        Position two = planTasks.get(0).getAction() == 1 ? planTasks.get(0).getOrder().getPickup() : planTasks.get(0).getOrder().getDelivery();   // 计划路线中第一个目的地

        DTKey key = new DTKey(one, two);
        DTValue value = positionMaps.getOrDefault(key, null);

        // 存储两个任务点的之间的距离
        double[] distances = new double[planTasks.size()];

        // 距离时间矩阵中没有这两点之间的距离则返回1，或两个任务点距离不能超过预定最大值
        if (value == null || value.getDistance() > Parameters.MAX_DISTANCE) {
            return 1;
        } else {
            distances[0] = value.getDistance();
            distanceCost += value.getDistance();

            for (int i = 0; i < planTasks.size() - 1; i++) {
                // 设置新的Map键值
                one = planTasks.get(i).getAction() == 1 ? planTasks.get(i).getOrder().getPickup() : planTasks.get(i).getOrder().getDelivery();
                two = planTasks.get(i + 1).getAction() == 1 ? planTasks.get(i + 1).getOrder().getPickup() : planTasks.get(i + 1).getOrder().getDelivery();
                key.setOne(one);
                key.setTwo(two);
                value = positionMaps.getOrDefault(key, null);

                // 距离时间矩阵中没有这两点之间的距离则返回1，或两个任务点距离不能超过预定最大值
                if (value == null || value.getDistance() > Parameters.MAX_DISTANCE) {
                    return 1;
                } else {
                    distances[i + 1] = value.getDistance();
                    distanceCost += value.getDistance();
                }
            }
        }
        Arrays.sort(distances);
        double minDistance = distances[0];
        double maxDistance = distances[distances.length - 1];
        distanceCost = (distanceCost - minDistance * distances.length) / ((maxDistance - minDistance + 1) * distances.length);
        return distanceCost;
    }

    /**
     * 计算车辆行驶该路线时等待时间与延迟时间的成本
     *
     * @param vehicle      待计算成本的车辆对象
     * @param positionMaps 存储两个位置之间距离和时间的映射
     * @return 一维数组，第一个为归一化后等待时间的成本[0,1]，第二个是归一化后延迟时间的成本[0,1]
     */
    private double[] computeWaitTimeAndDelayTimeCost(Vehicle vehicle, HashMap<DTKey, DTValue> positionMaps) {
        double waitTimeCost = 0;
        double delayTimeCost = 0;

        LinkedList<ScheduleTask> planTasks = this.getPlanTasks();
        Date[] arriveTimeList = new Date[planTasks.size()];
        double[] waitTimes = new double[planTasks.size()];
        double[] delayTimes = new double[planTasks.size()];

        Position one = vehicle.getPosition();
        Position two = planTasks.get(0).getAction() == 1 ? planTasks.get(0).getOrder().getPickup() : planTasks.get(0).getOrder().getDelivery();   // 计划路线中第一个目的地
        DTKey key = new DTKey(one, two);
        DTValue value = positionMaps.getOrDefault(key, null);

        if (value == null) {    // 距离时间矩阵中没有这两点之间的距离则返回1
            return new double[]{1, 1};
        } else {
            // 计算车辆到达第一个计划任务点的时间
            arriveTimeList[0] = TimeCompare.addHour(vehicle.getGpsUpdateTime(), value.getTime());
            for (int i = 0; i < planTasks.size(); i++) {
                // 得到该任务点的时间窗与服务时间
                ScheduleTask st = planTasks.get(i);
                Date earlyTime = st.getAction() == 1 ? st.getOrder().getEarlyTimeOfPickup() : st.getOrder().getEarlyTimeOfDelivery();
                Date lastTime = st.getAction() == 1 ? st.getOrder().getLatestTimeOfPickup() : st.getOrder().getLatestTimeOfDelivery();
                double serviceTime = st.getAction() == 1 ? st.getOrder().getPickupServiceTime() : st.getOrder().getDeliveryServiceTime();

                // 计算车辆到达当前计划任务点的等待时间与延迟时间
                double waitTime = TimeCompare.earlyTimeSubArriveTime(earlyTime, arriveTimeList[i]);
                waitTime = waitTime > 0 ? waitTime : 0;
                waitTimes[i] = waitTime;

                double delayTime = TimeCompare.arriveTimeSubLastTime(arriveTimeList[i], lastTime);
                delayTime = delayTime > 0 ? delayTime : 0;
                delayTimes[i] = delayTime;

                // 等待时间和延迟时间不能超过预定最大值
                if (waitTime > Parameters.MAX_WAIT_TIME || delayTime > Parameters.MAX_DELAY_TIME) {
                    return new double[]{1, 1};
                }

                waitTimeCost += waitTime;
                delayTimeCost += delayTime;

                // 最后一个计划任务点无需计算到达下一个计划任务点的时间
                if (i == planTasks.size() - 1) {
                    break;
                }

                // 计算车辆到达下个计划任务点的时间
                one = planTasks.get(i).getAction() == 1 ? planTasks.get(i).getOrder().getPickup() : planTasks.get(i).getOrder().getDelivery();
                two = planTasks.get(i + 1).getAction() == 1 ? planTasks.get(i + 1).getOrder().getPickup() : planTasks.get(i + 1).getOrder().getDelivery();
                key.setOne(one);
                key.setTwo(two);
                value = positionMaps.getOrDefault(key, null);

                if (value == null) {
                    return new double[]{1, 1};
                } else {
                    arriveTimeList[i + 1] = TimeCompare.addHour(arriveTimeList[i], waitTime + serviceTime + value.getTime());
                }
            }
        }

        // 归一化
        Arrays.sort(waitTimes);
        double minWaitTime = waitTimes[0];
        double maxWaitTime = waitTimes[waitTimes.length - 1];

        Arrays.sort(delayTimes);
        double minDelayTime = delayTimes[0];
        double maxDelayTime = delayTimes[delayTimes.length - 1];

        waitTimeCost = (waitTimeCost - minWaitTime * waitTimes.length) / ((maxWaitTime - minWaitTime + 0.1) * waitTimes.length);
        delayTimeCost = (delayTimeCost - minDelayTime * delayTimes.length) / ((maxDelayTime - minDelayTime + 0.1) * delayTimes.length);
        return new double[]{waitTimeCost, delayTimeCost};
    }

    /**
     * 计算车辆行驶该路线时掏货的成本
     *
     * @param vehicle 待计算成本的车辆对象
     * @return 归一化后的掏货成本[0, 1]
     */
    private double computeDrawOutTimeCost(Vehicle vehicle) {
        double drawOutTimeCost = 0;

        for (int i = 0; i < this.planTasks.size(); i++) {
            ScheduleTask st = this.planTasks.get(i);
            if (st.getAction() == -1) {     // 需要下货的订单
                LinkedList<ScheduleTask> doneTask = RouteTools.loadedTaskInVehicle(this.tasks.subList(0, i + this.loadedTasks.size()));
                int pIndex = RouteTools.getUpIndex(doneTask, st.getOrder().getId());
                if (pIndex == -1) {
                    System.out.println("获取订单的上货点错误!");
                    return 1;   // cost 最大为1
                }

                for (int j = pIndex + 1; j < doneTask.size(); j++) {
                    Order order = doneTask.get(j).getOrder();
                    // 掏去该货物所消耗的时间代价
                    drawOutTimeCost += order.getDeliveryServiceTime() + order.getPickupServiceTime();
                }
            }
        }

        // 掏货时间不能超过预定最大值
        if (drawOutTimeCost > Parameters.MAX_DRAW_OUT_TIME) {
            return 1;
        }

        // 归一化
        drawOutTimeCost = drawOutTimeCost / (Parameters.MAX_DRAW_OUT_TIME * this.planTasks.size());
        return drawOutTimeCost;
    }

    public LinkedList<ScheduleTask> getPlanTasks() {
        return planTasks;
    }

    public LinkedList<ScheduleTask> getTasks() {
        return tasks;
    }

    public LinkedList<ScheduleTask> getLoadedTasks() {
        return loadedTasks;
    }

    public void setLoadedTasks(LinkedList<ScheduleTask> loadedTasks) {
        this.loadedTasks = loadedTasks;
    }

    public void setPlanTasks(LinkedList<ScheduleTask> planTasks) {
        this.planTasks = planTasks;
    }

    public ScheduleTask getTaskByIndex(int index) {
        return this.planTasks.get(index);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDistanceCost() {
        return distanceCost;
    }

    public void setDistanceCost(double distanceCost) {
        this.distanceCost = distanceCost;
    }

    public double getWaitTimeCost() {
        return waitTimeCost;
    }

    public void setWaitTimeCost(double waitTimeCost) {
        this.waitTimeCost = waitTimeCost;
    }

    public double getDelayTimeCost() {
        return delayTimeCost;
    }

    public void setDelayTimeCost(double delayTimeCost) {
        this.delayTimeCost = delayTimeCost;
    }

    public double getDrawOutCost() {
        return drawOutCost;
    }

    public void setDrawOutCost(double drawOutCost) {
        this.drawOutCost = drawOutCost;
    }

    @Override
    public Route clone() {
        Route newRoute = new Route(this.loadedTasks, this.planTasks);
        newRoute.setCost(this.cost);
        newRoute.setDistanceCost(this.distanceCost);
        newRoute.setWaitTimeCost(this.waitTimeCost);
        newRoute.setDelayTimeCost(this.delayTimeCost);
        newRoute.setDrawOutCost(this.drawOutCost);
        return newRoute;
    }

    @Override
    public String toString() {
        String tmp = "";
        tmp += "loaded tasks: ";
        for (ScheduleTask scheduleTask : loadedTasks) {
            tmp += " " + scheduleTask.toString();
        }
        tmp += "\n";

        tmp += "plan tasks: ";
        for (ScheduleTask scheduleTask : planTasks) {
            tmp += " " + scheduleTask.toString();
        }
        tmp += "\n";

        tmp += "cost: " + this.cost + "\n" +
                "distance cost: " + this.distanceCost + "\n" +
                "wait time cost: " + this.waitTimeCost + "\n" +
                "delay time cost: " + this.delayTimeCost + "\n" +
                "draw out cost: " + this.drawOutCost + "\n";
        return tmp;
    }
}
