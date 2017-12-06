package edu.scut.emos.tsp.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author yichen
 */
public class Routes {

    private LinkedList<Route> routes;
    private double[] costs;

    public Routes() {
        this.routes = new LinkedList<>();
    }

    public Routes(LinkedList<Route> routes) {
        this.routes = new LinkedList<>();
        for (Route route : routes) {
            this.routes.add(route.clone());
        }
        this.routes.addAll(routes);
    }

    public Routes(LinkedList<Route> routes, double[] costs) {
        this.routes = routes;
        this.costs = costs;
    }

    public void add(Route r) {
        routes.add(r);
    }

    public Route remove(int index) {
        return routes.remove(index);
    }

    public Route get(int index) {
        return routes.get(index);
    }

    public int size() {
        return routes.size();
    }

    public double[] getCosts() {
        return costs;
    }

    public void setCosts(double[] costs) {
        this.costs = costs;
    }

    public LinkedList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(LinkedList<Route> routes) {
        this.routes = routes;
    }


    /**
     * @param vehicle      待计算成本的车辆对象
     * @param positionMaps 存储两个位置之间距离和时间的映射
     * @return 返回routes列表中代价值最小的路径，如果有两种代价一样的路径，则返回相同代价中位置靠后的那条路径
     */
    public Route getMinCostRoute(Vehicle vehicle, HashMap<DTKey, DTValue> positionMaps) {
        this.costs = new double[routes.size()];
        Route result = new Route();
        double minCost = 1.0;
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            costs[i] = route.getCost(vehicle, positionMaps);
            if (costs[i] <= minCost) {
                minCost = costs[i];
                result = route;
            }
        }
        return result;
    }

    @Override
    public Routes clone() {
        Routes newRoutes = null;

        newRoutes = new Routes(routes);

        double[] costsTmp = costs == null ? null : costs.clone();
        newRoutes.setCosts(costsTmp);
        return newRoutes;
    }

    @Override
    public String toString() {
        return "Routes {" +
                "   routes: " + routes + "\n" +
                "   costs: " + Arrays.toString(costs) + "\n" +
                "}\n";
    }

}
