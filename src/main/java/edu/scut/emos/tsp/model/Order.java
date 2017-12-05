package edu.scut.emos.tsp.model;

import edu.scut.emos.tsp.tools.AddressTranslation;

import java.util.Date;

/**
 * @author emos
 *
 */
public class Order {
    private String id;
    private Position pickup;
    private Position delivery;
    private String typeOfGoods;
    private double weightOfGoods;
    private double volumeOfGoods;
    private Date earlyTimeOfPickup;
    private Date latestTimeOfPickup;
    private Date earlyTimeOfDelivery;
    private Date latestTimeOfDelivery;
    private double pickupServiceTime;
    private double deliveryServiceTime;
    private String serviceResult;
    private String commendVehicle;
    private String factualVehicle;


    public Order(String id, Position pickup, Position delivery, String typeOfGoods, double weightOfGoods, double volumeOfGoods, Date earlyTimeOfPickup, Date latestTimeOfPickup, Date earlyTimeOfDelivery, Date latestTimeOfDelivery, double pickupServiceTime, double deliveryServiceTime, String serviceResult, String commendVehicle, String factualVehicle) {
        this.id = id;
        this.pickup = pickup;
        this.delivery = delivery;
        this.typeOfGoods = typeOfGoods;
        this.weightOfGoods = weightOfGoods;
        this.volumeOfGoods = volumeOfGoods;
        this.earlyTimeOfPickup = earlyTimeOfPickup;
        this.latestTimeOfPickup = latestTimeOfPickup;
        this.earlyTimeOfDelivery = earlyTimeOfDelivery;
        this.latestTimeOfDelivery = latestTimeOfDelivery;
        this.pickupServiceTime = pickupServiceTime;
        this.deliveryServiceTime = deliveryServiceTime;
        this.serviceResult = serviceResult;
        this.commendVehicle = commendVehicle;
        this.factualVehicle = factualVehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Position getPickup() {
        return pickup;
    }

    public void setPickup(Position pickup) {
        this.pickup = pickup;
    }

    public Position getDelivery() {
        return delivery;
    }

    public void setDelivery(Position delivery) {
        this.delivery = delivery;
    }

    public String getTypeOfGoods() {
        return typeOfGoods;
    }

    public void setTypeOfGoods(String typeOfGoods) {
        this.typeOfGoods = typeOfGoods;
    }

    public double getVolumeOfGoods() {
        return volumeOfGoods;
    }

    public void setVolumeOfGoods(double volumeOfGoods) {
        this.volumeOfGoods = volumeOfGoods;
    }

    public double getWeightOfGoods() {
        return weightOfGoods;
    }

    public void setWeightOfGoods(double weightOfGoods) {
        this.weightOfGoods = weightOfGoods;
    }

    public Date getEarlyTimeOfPickup() {
        return earlyTimeOfPickup;
    }

    public void setEarlyTimeOfPickup(Date earlyTimeOfPickup) {
        this.earlyTimeOfPickup = earlyTimeOfPickup;
    }

    public Date getLatestTimeOfPickup() {
        return latestTimeOfPickup;
    }

    public void setLatestTimeOfPickup(Date latestTimeOfPickup) {
        this.latestTimeOfPickup = latestTimeOfPickup;
    }

    public Date getEarlyTimeOfDelivery() {
        return earlyTimeOfDelivery;
    }

    public void setEarlyTimeOfDelivery(Date earlyTimeOfDelivery) {
        this.earlyTimeOfDelivery = earlyTimeOfDelivery;
    }

    public Date getLatestTimeOfDelivery() {
        return latestTimeOfDelivery;
    }

    public void setLatestTimeOfDelivery(Date latestTimeOfDelivery) {
        this.latestTimeOfDelivery = latestTimeOfDelivery;
    }

    public double getPickupServiceTime() {
        return pickupServiceTime;
    }

    public void setPickupServiceTime(double pickupServiceTime) {
        this.pickupServiceTime = pickupServiceTime;
    }

    public double getDeliveryServiceTime() {
        return deliveryServiceTime;
    }

    public void setDeliveryServiceTime(double deliveryServiceTime) {
        this.deliveryServiceTime = deliveryServiceTime;
    }

    public String getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(String serviceResult) {
        this.serviceResult = serviceResult;
    }

    public String getCommendVehicle() {
        return commendVehicle;
    }

    public void setCommendVehicle(String commendVehicle) {
        this.commendVehicle = commendVehicle;
    }

    public String getFactualVehicle() {
        return factualVehicle;
    }

    public void setFactualVehicle(String factualVehicle) {
        this.factualVehicle = factualVehicle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public Order clone() {
        Position pickupTmp = pickup == null ? null : pickup.clone();
        Position deliveryTmp = delivery == null ? null : delivery.clone();
        Date earlyTimeOfPickupTmp = earlyTimeOfPickup == null ? null : (Date) earlyTimeOfPickup.clone();
        Date latestTimeOfPickupTmp = latestTimeOfPickup == null ? null : (Date) latestTimeOfPickup.clone();
        Date earlyTimeOfDeliveryTmp = earlyTimeOfDelivery == null ? null : (Date) earlyTimeOfDelivery.clone();
        Date latestTimeOfDeliveryTmp = latestTimeOfDelivery == null ? null : (Date) latestTimeOfDelivery.clone();

        return new Order(id, pickupTmp, deliveryTmp, typeOfGoods, volumeOfGoods, weightOfGoods, earlyTimeOfPickupTmp, latestTimeOfPickupTmp,
                earlyTimeOfDeliveryTmp, latestTimeOfDeliveryTmp, pickupServiceTime, deliveryServiceTime, serviceResult, commendVehicle, factualVehicle);
    }

    @Override
    public String toString() {
        return "Order {" + "\n" +
                "id: " + id + "\n" +
                "weight: " + weightOfGoods + "\n" +
                "volume: " + volumeOfGoods + "\n" +
                "pickup: " + (pickup == null ? "" : AddressTranslation.addressTranslation(pickup) + " " + pickup.toString()) + "\n" +
                "pickup early time: " + (earlyTimeOfPickup == null ? "" : earlyTimeOfPickup.toString()) + "\n" +
                "pickup latest time: " + (latestTimeOfPickup == null ? "" : latestTimeOfPickup.toString()) + "\n" +
                "pickup service time: " + pickupServiceTime + "\n" +
                "delivery: " + (delivery == null ? "" : AddressTranslation.addressTranslation(delivery) + " " + delivery.toString()) + "\n" +
                "delivery early time: " + (earlyTimeOfDelivery == null ? "" : earlyTimeOfDelivery.toString()) + "\n" +
                "delivery latest time: " + (latestTimeOfDelivery == null ? "" : latestTimeOfDelivery.toString()) + "\n" +
                "delivery service time: " + deliveryServiceTime + "\n" + "}\n";
    }
}
