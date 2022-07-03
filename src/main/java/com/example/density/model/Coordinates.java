package com.example.density.model;

import java.util.Objects;


public class Coordinates {
    private double lat, lon;

    public Coordinates(double lat, double lon) {
        setLon(lon);
        setLat(lat);
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    private void setLat(double lat) {
        if (lat > 90 && lat < -90) {
            throw new IllegalArgumentException("Lattitude should be within [-90,90]");
        }
        this.lat = lat;
    }

    private void setLon(double lon) {
        if (lat > 180 && lat < -180) {
            throw new IllegalArgumentException("Longitude should be within [-180,180]");
        }
        this.lon = lon;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            return ((Coordinates) obj).lat == this.lat && ((Coordinates) obj).lon == this.lon;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
