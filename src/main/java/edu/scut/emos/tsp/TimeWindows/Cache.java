package edu.scut.emos.tsp.TimeWindows;

import edu.scut.emos.tsp.core.Solver;
import edu.scut.emos.tsp.model.AlgorithmResult;
import edu.scut.emos.tsp.model.Vehicle;

import java.util.*;

public class Cache {
    private LinkedList<AlgorithmResult> algorithmResults;
    private HashMap<Vehicle, LinkedList<AlgorithmResult>> map;

    private LinkedList<String> recomputationOrderIDs;  // 需要重新计算的订单列表

    public Cache() {
    }

    public Cache(LinkedList<AlgorithmResult> algorithmResults) {
        this.algorithmResults = new LinkedList<>();
        this.algorithmResults.addAll(algorithmResults);
        this.generateMap();
        this.recomputationOrderIDs = new LinkedList<>();
    }

    private void generateMap() {
        map = new LinkedHashMap<>();

        for (AlgorithmResult algorithmResult : algorithmResults) {
            LinkedList<AlgorithmResult> algorithmResultList = map.getOrDefault(algorithmResult.getVehicle(), null);

            if (algorithmResultList == null) {
                algorithmResultList = new LinkedList<>();
            }

            algorithmResultList.add(algorithmResult);
            map.put(algorithmResult.getVehicle(), algorithmResultList);
        }
    }

    public CommitResult commit() {
        CommitResult commitResult = new CommitResult();
        LinkedList<CacheResult> cacheResults = new LinkedList<>();  // 经过cache类转换过的结果列表

        for (Vehicle vehicle : map.keySet()) {
            LinkedList<AlgorithmResult> arList = map.get(vehicle);
            LinkedList<String> orderIds = new LinkedList<>();           // 该车所匹配的订单ID
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

        commitResult.setCacheResults(cacheResults);
        commitResult.setRecomputationOrderIDs(recomputationOrderIDs);
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
        AlgorithmResult[] resultArrays = (AlgorithmResult[]) results.toArray();
        Arrays.sort(resultArrays);  // 按result中路径的cost从小到大排序

        // 将该车匹配的订单ID存入List中
        LinkedList<String> orderIds = new LinkedList<>();
        orderIds.add(resultArrays[0].getOrder().getId());

        Vehicle vehicleTmp = vehicle.clone();
        vehicleTmp.setRoute(resultArrays[0].getRecommendRoute());

        for (int i = 1; i < resultArrays.length; i++) {
            // TODO 可以考虑把调用API的Map计算提出来
            Solver solver = new Solver(vehicleTmp, resultArrays[i].getOrder());
            AlgorithmResult result = solver.getBestResult();

            if (result.getRecommendRoute() != null) {
                vehicleTmp.setRoute(result.getRecommendRoute());   // 将第二个订单插入路径中
                orderIds.add(result.getOrder().getId());
            } else {
                // resultArrays中i之后的所有订单均需要重新计算
                recomputationOrderIDs.add(result.getOrder().getId());
                for (int j = i + 1; j < resultArrays.length; j++) {
                    recomputationOrderIDs.add(resultArrays[j].getOrder().getId());
                }
                break;
            }
        }
        return new CacheResult(vehicleTmp.getId(), orderIds, vehicleTmp.getRoute(), vehicleTmp.getLastUpdateTime());
    }
}
