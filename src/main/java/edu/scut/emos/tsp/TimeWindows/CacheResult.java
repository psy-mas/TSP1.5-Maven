package edu.scut.emos.tsp.TimeWindows;

import edu.scut.emos.tsp.model.Route;

import java.util.Date;
import java.util.LinkedList;

public class CacheResult {
    private String vehicleId;
    private LinkedList<String> orderIds;
    private Route recommendRoute;
    private Date lastUpdateTime;

    public CacheResult() {
    }

    public CacheResult(String vehicleId, LinkedList<String> orderIds, Route recommendRoute, Date lastUpdateTime) {
        this.vehicleId = vehicleId;
        this.orderIds = new LinkedList<>();
        this.orderIds.addAll(orderIds);
        this.recommendRoute = recommendRoute.clone();
        this.lastUpdateTime = lastUpdateTime == null ? null : (Date) lastUpdateTime.clone();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LinkedList<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(LinkedList<String> orderIds) {
        this.orderIds = orderIds;
    }

    public Route getRecommendRoute() {
        return recommendRoute;
    }

    public void setRecommendRoute(Route recommendRoute) {
        this.recommendRoute = recommendRoute;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "CacheResult {\n" +
                "vehicleId: " + vehicleId + '\n' +
                "orderIds: " + orderIds + "\n" +
                "recommendRoute: " + recommendRoute + "\n" +
                "lastUpdateTime: " + lastUpdateTime + "\n" +
                '}';
    }
}
