package edu.scut.emos.tsp.core;

import edu.scut.emos.tsp.model.*;
import edu.scut.emos.tsp.tools.DistanceTimeMatrix;
import edu.scut.emos.tsp.tools.Parameters;

import java.util.HashMap;
import java.util.LinkedList;

public class Solver {

    private Vehicle vehicle;
    private Order order;
    private Routes routes = new Routes();
    private HashMap<DTKey, DTValue> map;

    public Solver(Vehicle vehicle, Order order) {
        this.vehicle = vehicle;
        this.order = order;
        this.generateDTM();
        this.generateRoutes();
    }

    private void generateDTM() {
        LinkedList<ScheduleTask> planTasks = vehicle.getRoute().getPlanTasks();
        Position[] positions = new Position[planTasks.size() + 3];

        positions[0] = vehicle.getPosition();   // 车辆位置
        positions[1] = order.getPickup();       // 插入订单的上货位置
        positions[2] = order.getDelivery();     // 插入订单的下货位置

        for (int i = 0; i < planTasks.size(); i++) {
            ScheduleTask st = planTasks.get(i);
            Position taskPosition = st.getAction() == 1 ? st.getOrder().getPickup() : st.getOrder().getDelivery();
            positions[i + 3] = taskPosition;
        }

        map = DistanceTimeMatrix.computeDistanceTimeTable(positions);
    }

    private void generateRoutes() {
        //生成取货卸货的两个任务
        ScheduleTask pickupTask = new ScheduleTask(this.order, 1);
        ScheduleTask deliveryTask = new ScheduleTask(this.order, -1);

        //得到车辆中原有的计划任务列表
        LinkedList<ScheduleTask> loadedTaskList = this.vehicle.getRoute().getLoadedTasks();
        LinkedList<ScheduleTask> originPlanTaskList = this.vehicle.getRoute().getPlanTasks();
        LinkedList<ScheduleTask> tmpList = new LinkedList<>();

        //保护originPlanTaskList不被修改
        for (ScheduleTask tmp : originPlanTaskList) {
            tmpList.add(tmp);
        }
        //生成路径集合
        for (int i = 0; i <= originPlanTaskList.size(); i++) {
            tmpList.add(i, pickupTask);
            for (int j = i + 1; j <= tmpList.size(); j++) {
                tmpList.add(j, deliveryTask);
                Route route = new Route(loadedTaskList, tmpList);
                if (route.isInsertFeasible(vehicle)) {
                    routes.add(route);
                }
                tmpList.remove(j);
            }
            tmpList.remove(i);
        }
    }

    public AlgorithmResult getBestResult() {
        Route bestRoute = getBestRoute();
        if (bestRoute.getCost() > Parameters.MAX_COST) {
            bestRoute = null;
        }
        vehicle.setLock(true);  // 对车状态上锁
        return new AlgorithmResult(vehicle, order, bestRoute);
    }

    private Route getBestRoute() {
        return routes.getMinCostRoute(vehicle, map);
    }
}
