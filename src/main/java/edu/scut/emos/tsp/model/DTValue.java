package edu.scut.emos.tsp.model;

/**
 * @author emos
 *
 */
public class DTValue {

    private double distance;
    private double time;

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


}
