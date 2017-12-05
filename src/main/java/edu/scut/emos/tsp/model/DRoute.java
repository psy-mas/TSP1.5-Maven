package edu.scut.emos.tsp.model;

/**
 * @author emos
 *
 */
public class DRoute {
    private String routeid;

    private String vehicleid;

    private String routeorderid;

    private Byte action;

    private Byte isloaded;

    private Integer sequence;

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid == null ? null : routeid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getRouteorderid() {
        return routeorderid;
    }

    public void setRouteorderid(String routeorderid) {
        this.routeorderid = routeorderid == null ? null : routeorderid.trim();
    }

    public Byte getAction() {
        return action;
    }

    public void setAction(Byte action) {
        this.action = action;
    }

    public Byte getIsloaded() {
        return isloaded;
    }

    public void setIsloaded(Byte isloaded) {
        this.isloaded = isloaded;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}