package edu.scut.emos.tsp.model;

public class AlgorithmResult implements Comparable<AlgorithmResult> {

    private Vehicle vehicle;
    private Order order;
    private Route recommendRoute;

    public AlgorithmResult(Vehicle vehicle, Order order, Route recommendRoute) {
        this.vehicle = vehicle == null ? null : vehicle.clone();
        this.order = order == null ? null : order.clone();
        this.recommendRoute = recommendRoute == null ? null : recommendRoute.clone();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Order getOrder() {
        return order;
    }

    public Route getRecommendRoute() {
        return recommendRoute;
    }

    @Override
    public int compareTo(AlgorithmResult o) {
        if (o == null) {
            return -1;
        }

        if (this.recommendRoute.getCost() == o.recommendRoute.getCost()) {
            return 0;
        } else if (this.recommendRoute.getCost() > o.recommendRoute.getCost()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public AlgorithmResult clone() {
        Vehicle vehicleTmp = vehicle == null ? null : vehicle.clone();
        Order orderTmp = order == null ? null : order.clone();
        return new AlgorithmResult(vehicleTmp, orderTmp, recommendRoute.clone());
    }

    @Override
    public String toString() {
        if (recommendRoute == null) {
            return "订单不可服务";
        } else {
            return recommendRoute.toString();
        }
    }
}
