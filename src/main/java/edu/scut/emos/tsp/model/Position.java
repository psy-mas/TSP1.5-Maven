package edu.scut.emos.tsp.model;

public class Position {
    //定义经纬度信息
    private double latitude;
    private double longitude;

    public Position(double latitude, double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return (int) (latitude * 1000000 + longitude * 1000000);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            Position other = (Position) obj;
            return this.latitude == other.latitude && this.longitude == other.longitude;
        } else {
            return false;
        }
    }

    @Override
    public Position clone() {
        Position newPosition = null;
        newPosition = new Position(this.latitude, this.longitude);
        return newPosition;
    }

    @Override
    public String toString() {
        return String.valueOf(this.latitude) + "," + String.valueOf(this.longitude);
    }
}
