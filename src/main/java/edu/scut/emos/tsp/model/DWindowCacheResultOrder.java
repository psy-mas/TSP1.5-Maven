package edu.scut.emos.tsp.model;

/**
 * @author emos
 *
 */
public class DWindowCacheResultOrder {
    private String cacheresultordersid;

    private String vehicleid;

    private String orderid;

    public String getCacheresultordersid() {
        return cacheresultordersid;
    }

    public void setCacheresultordersid(String cacheresultordersid) {
        this.cacheresultordersid = cacheresultordersid == null ? null : cacheresultordersid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }
}