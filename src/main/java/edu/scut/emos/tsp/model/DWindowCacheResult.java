package edu.scut.emos.tsp.model;

import java.util.Date;

public class DWindowCacheResult {
    private String cacheresultid;

    private String vehicleid;

    private Date lastupdatetime;

    public String getCacheresultid() {
        return cacheresultid;
    }

    public void setCacheresultid(String cacheresultid) {
        this.cacheresultid = cacheresultid == null ? null : cacheresultid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}