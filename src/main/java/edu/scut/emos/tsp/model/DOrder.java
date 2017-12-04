package edu.scut.emos.tsp.model;

import java.util.Date;

public class DOrder {
    private String orderid;

    private Double pickuplongitude;

    private Double pickuplatitude;

    private Double deliverylongitude;

    private Double deliverylatitude;

    private String typeofgoods;

    private Double weightofgoods;

    private Double volumeofgoods;

    private Date earlytimeofpickup;

    private Date earlytimeofdelivery;

    private Date lasttimeofpickup;

    private Date lasttimeofdelivery;

    private Double pickupservicetime;

    private Double deliveryservicetime;

    private String serviceresult;

    private String commendvehicle;

    private String factualvehicle;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getPickuplongitude() {
        return pickuplongitude;
    }

    public void setPickuplongitude(Double pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
    }

    public Double getPickuplatitude() {
        return pickuplatitude;
    }

    public void setPickuplatitude(Double pickuplatitude) {
        this.pickuplatitude = pickuplatitude;
    }

    public Double getDeliverylongitude() {
        return deliverylongitude;
    }

    public void setDeliverylongitude(Double deliverylongitude) {
        this.deliverylongitude = deliverylongitude;
    }

    public Double getDeliverylatitude() {
        return deliverylatitude;
    }

    public void setDeliverylatitude(Double deliverylatitude) {
        this.deliverylatitude = deliverylatitude;
    }

    public String getTypeofgoods() {
        return typeofgoods;
    }

    public void setTypeofgoods(String typeofgoods) {
        this.typeofgoods = typeofgoods == null ? null : typeofgoods.trim();
    }

    public Double getWeightofgoods() {
        return weightofgoods;
    }

    public void setWeightofgoods(Double weightofgoods) {
        this.weightofgoods = weightofgoods;
    }

    public Double getVolumeofgoods() {
        return volumeofgoods;
    }

    public void setVolumeofgoods(Double volumeofgoods) {
        this.volumeofgoods = volumeofgoods;
    }

    public Date getEarlytimeofpickup() {
        return earlytimeofpickup;
    }

    public void setEarlytimeofpickup(Date earlytimeofpickup) {
        this.earlytimeofpickup = earlytimeofpickup;
    }

    public Date getEarlytimeofdelivery() {
        return earlytimeofdelivery;
    }

    public void setEarlytimeofdelivery(Date earlytimeofdelivery) {
        this.earlytimeofdelivery = earlytimeofdelivery;
    }

    public Date getLasttimeofpickup() {
        return lasttimeofpickup;
    }

    public void setLasttimeofpickup(Date lasttimeofpickup) {
        this.lasttimeofpickup = lasttimeofpickup;
    }

    public Date getLasttimeofdelivery() {
        return lasttimeofdelivery;
    }

    public void setLasttimeofdelivery(Date lasttimeofdelivery) {
        this.lasttimeofdelivery = lasttimeofdelivery;
    }

    public Double getPickupservicetime() {
        return pickupservicetime;
    }

    public void setPickupservicetime(Double pickupservicetime) {
        this.pickupservicetime = pickupservicetime;
    }

    public Double getDeliveryservicetime() {
        return deliveryservicetime;
    }

    public void setDeliveryservicetime(Double deliveryservicetime) {
        this.deliveryservicetime = deliveryservicetime;
    }

    public String getServiceresult() {
        return serviceresult;
    }

    public void setServiceresult(String serviceresult) {
        this.serviceresult = serviceresult == null ? null : serviceresult.trim();
    }

    public String getCommendvehicle() {
        return commendvehicle;
    }

    public void setCommendvehicle(String commendvehicle) {
        this.commendvehicle = commendvehicle == null ? null : commendvehicle.trim();
    }

    public String getFactualvehicle() {
        return factualvehicle;
    }

    public void setFactualvehicle(String factualvehicle) {
        this.factualvehicle = factualvehicle == null ? null : factualvehicle.trim();
    }
}