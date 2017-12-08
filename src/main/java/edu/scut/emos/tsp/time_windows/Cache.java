package edu.scut.emos.tsp.time_windows;

import edu.scut.emos.tsp.core.Solver;
import edu.scut.emos.tsp.model.AlgorithmResult;
import edu.scut.emos.tsp.model.Vehicle;

import java.util.*;

public class Cache {
    private LinkedList<AlgorithmResult> algorithmResults;
    private HashMap<Vehicle, LinkedList<AlgorithmResult>> map;

    private LinkedList<FailedOrder> failedOrderIDs;     // 计算失败的订单


    public Cache() {
    }

    public Cache(LinkedList<AlgorithmResult> algorithmResults) {
        this.algorithmResults = new LinkedList<>();
        if (algorithmResults != null) {
            this.algorithmResults.addAll(algorithmResults);
        }

        this.failedOrderIDs = new LinkedList<>();

        this.initialMap();
    }

    private void initialMap() {
        map = new LinkedHashMap<>();

        if (algorithmResults != null) {
            for (AlgorithmResult algorithmResult : algorithmResults) {
                // 算法结果数组为空，则不进行之后的计算
                if (algorithmResult == null) {
                    continue;
                }
                // 算法结果推荐路径为空，则说明订单不可插入该车，则将订单存入没有车辆匹配的订单列表中
                if (algorithmResult.getRecommendRoute() == null) {
                    failedOrderIDs.add(new FailedOrder(algorithmResult.getOrder().getId(), 0));
                    continue;
                }

                LinkedList<AlgorithmResult> algorithmResultList = map.getOrDefault(algorithmResult.getVehicle(), null);

                if (algorithmResultList == null) {
                    algorithmResultList = new LinkedList<>();
                }

                algorithmResultList.add(algorithmResult);
                map.put(algorithmResult.getVehicle(), algorithmResultList);
            }
        }
    }

    /**
     * 根据初始化后的cache类，计算经过对算法结果列表处理后的提交结果
     *
     * @return 经过cache层后得到的提交结果
     */
    public CommitResult commit() {
        LinkedList<CacheResult> cacheResults = new LinkedList<>();  // 经过cache类转换过的结果列表

        for (Vehicle vehicle : map.keySet()) {
            LinkedList<AlgorithmResult> arList = map.get(vehicle);      // 该车的所匹配到的算法结果列表
            LinkedList<String> orderIds = new LinkedList<>();           // 初始化该车经过cache计算后所匹配的订单ID列表
            CacheResult cacheResult = null;

            // 该车被多个订单所匹配
            if (arList.size() > 1) {
                cacheResult = mergeVehicleRoute(vehicle, arList);
            } else {
                // 该车只有一个订单匹配
                AlgorithmResult ar = arList.get(0);
                orderIds.add(ar.getOrder().getId());
                cacheResult = new CacheResult(ar.getVehicle().getId(), orderIds, ar.getRecommendRoute(), ar.getVehicle().getLastUpdateTime());
            }
            cacheResults.add(cacheResult);
        }

        CommitResult commitResult = new CommitResult();
        commitResult.setCacheResults(cacheResults);
        commitResult.setFailedOrderIDs(failedOrderIDs);
        return commitResult;
    }

    /**
     * 根据车辆与其对应的算法结果列表，得到可以装载在该车辆中的提交结果；并将不可插入该车辆的订单加入recomputationOrders中
     *
     * @param vehicle 被多个订单匹配的车辆
     * @param results 匹配该车辆的所有订单列表
     * @return 可以装载在该车辆中的提交结果
     */
    private CacheResult mergeVehicleRoute(Vehicle vehicle, List<AlgorithmResult> results) {
        if (vehicle == null || results == null || results.size() == 0)
            return null;

        AlgorithmResult[] resultArrays = new AlgorithmResult[results.size()];
        resultArrays = results.toArray(resultArrays);
        Arrays.sort(resultArrays);  // 按result中路径的cost从小到大排序

        // 将该车匹配的订单ID存入List中
        LinkedList<String> orderIds = new LinkedList<>();
        orderIds.add(resultArrays[0].getOrder().getId());

        Vehicle vehicleTmp = vehicle.clone();
        vehicleTmp.setRoute(resultArrays[0].getRecommendRoute());

        for (int i = 1; i < resultArrays.length; i++) {
            Solver solver = new Solver(vehicleTmp, resultArrays[i].getOrder());
            AlgorithmResult result = solver.getBestResult();

            if (result.getRecommendRoute() != null) {
                vehicleTmp.setRoute(result.getRecommendRoute());   // 将第二个订单插入路径中
                orderIds.add(result.getOrder().getId());
            } else {
                // resultArrays中i之后的所有订单均需要重新计算
                failedOrderIDs.add(new FailedOrder(result.getOrder().getId(), 1));
                for (int j = i + 1; j < resultArrays.length; j++) {
                    failedOrderIDs.add(new FailedOrder(resultArrays[j].getOrder().getId(), 1));
                }
                break;
            }
        }
        return new CacheResult(vehicleTmp.getId(), orderIds, vehicleTmp.getRoute(), vehicleTmp.getLastUpdateTime());
    }
}
