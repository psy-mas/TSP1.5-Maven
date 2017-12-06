package edu.scut.emos.tsp.model;

import edu.scut.emos.tsp.tools.AddressTranslation;

import java.util.Date;

public class Vehicle {

    private String id;
    private double length;
    private double width;
    private double height;
    private String typeOfVehicle;
    private double maxWeight;
    private double maxVolume;
    private Position position;
    private Date gpsUpdateTime;
    private double fuelConsumption;
    private Date lastUpdateTime;
    private double loadedWeight;
    private double loadedVolume;
    private boolean lock;
    private Route route;

    public Vehicle(String id, double length, double width, double height, String typeOfVehicle, double maxWeight, double maxVolume, Position position, Date gpsUpdateTime, double fuelConsumption, Date lastUpdateTime, double loadedWeight, double loadedVolume, boolean lock, Route route) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
        this.typeOfVehicle = typeOfVehicle;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.position = position;
        this.gpsUpdateTime = gpsUpdateTime;
        this.fuelConsumption = fuelConsumption;
        this.lastUpdateTime = lastUpdateTime;
        this.loadedWeight = loadedWeight;
        this.loadedVolume = loadedVolume;
        this.lock = lock;
        this.route = route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getGpsUpdateTime() {
        return gpsUpdateTime;
    }

    public void setGpsUpdateTime(Date gpsUpdateTime) {
        this.gpsUpdateTime = gpsUpdateTime;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public double getLoadedVolume() {
        return loadedVolume;
    }

    public void setLoadedVolume(double loadedVolume) {
        this.loadedVolume = loadedVolume;
    }

    public double getLoadedWeight() {
        return loadedWeight;
    }

    public void setLoadedWeight(double loadedWeight) {
        this.loadedWeight = loadedWeight;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Vehicle clone() {
        Vehicle newVehicle = null;
        Position positionTmp = position == null ? null : position.clone();
        Date gpsUpdateTimeTmp = gpsUpdateTime == null ? null : (Date) gpsUpdateTime.clone();
        Date lastUpdateTimeTmp = lastUpdateTime == null ? null : (Date) lastUpdateTime.clone();
        Route routeTmp = route == null ? null : route.clone();

        newVehicle = new Vehicle(id, length, width, height, typeOfVehicle, maxWeight, maxVolume, positionTmp,
                gpsUpdateTimeTmp, fuelConsumption, lastUpdateTimeTmp, loadedWeight, loadedVolume, lock, routeTmp);

        return newVehicle;
    }

    @Override
    public String toString() {
        return "Vehicle {" + "\n" +
                "   id: " + id + "\n" +
                "   max weight: " + maxWeight + "\n" +
                "   max volume: " + maxVolume + "\n" +
                "   position: " + (position == null ? "" : AddressTranslation.addressTranslation(position) + " " + position.toString()) + "\n" +
                "   gps update time: " + (gpsUpdateTime == null ? "" : gpsUpdateTime.toString()) + "\n" +
                "   " + (route == null ? "no route" : route.toString()) + "\n}\n";
    }
}