package edu.scut.emos.tsp.model;

public final class Position {

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
    public boolean equals(Object o) {
        if (this == o) return true;

        Position position = (Position) o;

        if (Double.compare(position.latitude, latitude) != 0) return false;
        return Double.compare(position.longitude, longitude) == 0;
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
    
    public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
