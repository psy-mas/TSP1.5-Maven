package edu.scut.emos.tsp.model;

import java.util.Date;

/**
 * @author emos
 *
 */
public class DVehicle {
    private String vehicleid;

    private Double length;

    private Double width;

    private Double height;

    private String typeofvehicle;

    private Double maxweight;

    private Double maxvolume;

    private Double latestlongitude;

    private Double latestlatitude;

    private Date gpsuploadtime;

    private Double fuelconsumption;

    private Double loadedweight;

    private Double loadedvolume;

    private Byte islocked;

    private Date lastupdatetime;

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getTypeofvehicle() {
        return typeofvehicle;
    }

    public void setTypeofvehicle(String typeofvehicle) {
        this.typeofvehicle = typeofvehicle == null ? null : typeofvehicle.trim();
    }

    public Double getMaxweight() {
        return maxweight;
    }

    public void setMaxweight(Double maxweight) {
        this.maxweight = maxweight;
    }

    public Double getMaxvolume() {
        return maxvolume;
    }

    public void setMaxvolume(Double maxvolume) {
        this.maxvolume = maxvolume;
    }

    public Double getLatestlongitude() {
        return latestlongitude;
    }

    public void setLatestlongitude(Double latestlongitude) {
        this.latestlongitude = latestlongitude;
    }

    public Double getLatestlatitude() {
        return latestlatitude;
    }

    public void setLatestlatitude(Double latestlatitude) {
        this.latestlatitude = latestlatitude;
    }

    public Date getGpsuploadtime() {
        return gpsuploadtime;
    }

    public void setGpsuploadtime(Date gpsuploadtime) {
        this.gpsuploadtime = gpsuploadtime;
    }

    public Double getFuelconsumption() {
        return fuelconsumption;
    }

    public void setFuelconsumption(Double fuelconsumption) {
        this.fuelconsumption = fuelconsumption;
    }

    public Double getLoadedweight() {
        return loadedweight;
    }

    public void setLoadedweight(Double loadedweight) {
        this.loadedweight = loadedweight;
    }

    public Double getLoadedvolume() {
        return loadedvolume;
    }

    public void setLoadedvolume(Double loadedvolume) {
        this.loadedvolume = loadedvolume;
    }

    public Byte getIslocked() {
        return islocked;
    }

    public void setIslocked(Byte islocked) {
        this.islocked = islocked;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}