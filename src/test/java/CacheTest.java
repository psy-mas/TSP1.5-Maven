import edu.scut.emos.tsp.TimeWindows.Cache;
import edu.scut.emos.tsp.TimeWindows.CommitResult;
import edu.scut.emos.tsp.core.Solver;
import edu.scut.emos.tsp.model.AlgorithmResult;
import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.tools.DataGenerate;

import java.util.LinkedList;

public class CacheTest {
    public static void main(String[] args) {
        int orderNum = 4;
        int conflictOrderNum = 3;

        // 车池
        LinkedList<Vehicle> vehicles = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            Vehicle vehicle = DataGenerate.generateVehicle(String.valueOf(i + 1));
            vehicles.add(vehicle);
            System.out.println(vehicle);
        }

        // 订单池
        int orderId = 0;
        LinkedList<Order> orders = new LinkedList<>();
        for (orderId = 0; orderId < orderNum; orderId++) {
            Order order = DataGenerate.generateOrder(String.valueOf(orderId + 1));
            orders.add(order);
            System.out.println(order);
        }

        // 存储每个订单计算后的结果
        int resultIndex = 0;
        LinkedList<AlgorithmResult> algorithmResults = new LinkedList<>();

        // 将需要冲突的订单，全部插入1号车中
        for (int i = 0; i < conflictOrderNum; i++) {
            Order order = orders.get(i);
            Solver solver = new Solver(vehicles.get(0), order);
            algorithmResults.add(solver.getBestResult());
        }

        // 将剩下的订单插入2号车中
        for (int i = conflictOrderNum; i < orderNum; i++) {
            Order order = orders.get(i);
            Solver solver = new Solver(vehicles.get(1), order);
            algorithmResults.add(solver.getBestResult());
        }

        // 将计算结果集algorithmResults，传入cache层
        Cache cache = new Cache(algorithmResults);
        CommitResult commitResult = cache.commit();
        System.out.println(commitResult);

    }
}
