import edu.scut.emos.tsp.core.Solver;
import edu.scut.emos.tsp.model.AlgorithmResult;
import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.tools.DataGenerate;

import java.util.Date;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {


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

