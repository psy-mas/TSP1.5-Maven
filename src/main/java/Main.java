import edu.scut.emos.tsp.core.Solver;
import edu.scut.emos.tsp.model.AlgorithmResult;
import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.tools.DataGenerate;

import java.util.Date;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
//        Order one = new Order("A", new Position(23.07263, 113.397157), new Position(23.134612, 113.29652),
//                null, 10, 10,
//                new Date(2017 - 1900, 11 - 1, 21, 9, 0), new Date(2017 - 1900, 11 - 1, 21, 10, 0),
//                new Date(2017 - 1900, 11 - 1, 21, 11, 0), new Date(2017 - 1900, 11 - 1, 21, 14, 0),
//                0.5, 0.5, null, null, null);
//
//        Order two = new Order("B", new Position(23.054872, 113.411971), new Position(23.168899, 113.350188),
//                null, 10, 10,
//                new Date(2017 - 1900, 11 - 1, 21, 9, 0), new Date(2017 - 1900, 11 - 1, 21, 10, 0),
//                new Date(2017 - 1900, 11 - 1, 21, 11, 0), new Date(2017 - 1900, 11 - 1, 21, 14, 0),
//                0.5, 0.5, null, null, null);
//
//        Order three = new Order("C", new Position(23.073091, 113.383236), new Position(23.104508, 113.373171),
//                null, 10, 10,
//                new Date(2017 - 1900, 11 - 1, 21, 9, 0), new Date(2017 - 1900, 11 - 1, 21, 11, 0),
//                new Date(2017 - 1900, 11 - 1, 21, 11, 0), new Date(2017 - 1900, 11 - 1, 21, 13, 0),
//                0.5, 0.5, null, null, null);
//
//        Route route = new Route();
//        LinkedList<ScheduleTask> loadedTasks = new LinkedList<>();
//        loadedTasks.add(0, new ScheduleTask(one, 1));
//        loadedTasks.add(1, new ScheduleTask(two, 1));
//
//        LinkedList<ScheduleTask> planTasks = new LinkedList<>();
//        planTasks.add(0, new ScheduleTask(two, -1));
//        planTasks.add(1, new ScheduleTask(one, -1));
//
//
//        route.setLoadedTasks(loadedTasks);
//        route.setPlanTasks(planTasks);
//
//        Vehicle vehicle = new Vehicle("车",
//                0,
//                0,
//                0, "1", 30, 60, new Position(23.040695, 113.404518),
//                new Date(2017 - 1900, 11 - 1, 21, 9, 0), 10, null,
//                0, 0, false, route);
//
//        Solver solver = new Solver(vehicle, three);
//
//        AlgorithmResult algorithmResult = solver.getBestResult();
//        System.out.println(algorithmResult.toString());

        // 订单池
        int orderId = 0;
        LinkedList<Order> orders = new LinkedList<>();
        for (orderId = 0; orderId < 10; orderId++) {
            Order order = DataGenerate.generateOrder(String.valueOf(orderId + 1));
            orders.add(order);
            System.out.println(order);
        }

        // 车辆池
        LinkedList<Vehicle> vehicles = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = DataGenerate.generateVehicle(String.valueOf(i + 1));
            vehicles.add(vehicle);
            System.out.println(vehicle);
        }


        // 对每个订单计算合适的车辆
        int j = 0;
        for (Order order : orders) {
            Date start = new Date();
            // 记录每辆车的计算结果
            AlgorithmResult[] algorithmResults = new AlgorithmResult[vehicles.size()];
            double minCost = 1;
            int index = -1;

            // 获取最小代价的车辆
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                Solver solver = new Solver(vehicle, order);
                algorithmResults[i] = solver.getBestResult();

                if (algorithmResults[i].getRecommendRoute() != null) {
                    double cost = algorithmResults[i].getRecommendRoute().getCost();
                    if (cost < minCost) {
                        minCost = cost;
                        index = i;
                    }
                }
            }

            if (index == -1) {
                System.out.println("order " + order.getId() + " no matching!");
            } else {
                Vehicle bestVehicle = vehicles.get(index);
                AlgorithmResult bestResult = algorithmResults[index];

                // 将最优路径插入车中
                bestVehicle.setRoute(bestResult.getRecommendRoute());
            }
            Date end = new Date();
            System.out.println("runtime " + (++j) + " :" + (end.getTime() - start.getTime()) / 1000.0);
        }

        System.out.println("----------------train result-------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------");

        // 订单池
        LinkedList<Order> ordersTest = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            Order order = DataGenerate.generateOrder(String.valueOf(++orderId));
            ordersTest.add(order);
            System.out.println(order);
        }

        AlgorithmResult[] algorithmResults = new AlgorithmResult[vehicles.size()];
        double minCost = 1;
        int index = -1;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            Solver solver = new Solver(vehicle, ordersTest.get(0));
            algorithmResults[i] = solver.getBestResult();

            if (algorithmResults[i].getRecommendRoute() != null) {
                double cost = algorithmResults[i].getRecommendRoute().getCost();
                if (cost < minCost) {
                    minCost = cost;
                    index = i;
                }
            }
        }

        if (index == -1) {
            System.out.println("order " + ordersTest.get(0).getId() + " no matching!");
        } else {
            Vehicle bestVehicle = vehicles.get(index);
            AlgorithmResult bestResult = algorithmResults[index];

            // 将最优路径插入车中
            bestVehicle.setRoute(bestResult.getRecommendRoute());
        }

        System.out.println("----------------test result-------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }


    }

}

