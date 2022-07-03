package com.example.density.model;

public class PointOfInterest {
    private final String id;
    private final Coordinates coordinates;
    public PointOfInterest(String id, double lat, double lon) {
        this.id = id;
        this.coordinates = new Coordinates(lat,lon);

    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return coordinates.getLat();
    }

    public double getLon() {
        return coordinates.getLon();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
