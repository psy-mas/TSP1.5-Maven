package edu.scut.emos.tsp.model;

public class DTValue {

    private double distance;
    private double time;

    public DTValue() {
        distance = 0;
        time = 0;
    }

    public DTValue(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTime(double time) {
        this.time = time;
    }


}
