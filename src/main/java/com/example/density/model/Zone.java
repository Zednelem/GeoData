package com.example.density.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
public class Zone {
    private static final double INCREMENT = 0.5;
    private final Coordinates coordinatesMax;
    private final Coordinates coordinatesMin;

    public Zone(double lat, double lon) {
        this.coordinatesMin = new Coordinates(rounded(lat),rounded(lon));
        this.coordinatesMax = new Coordinates(coordinatesMin.getLat()+INCREMENT, coordinatesMin.getLon()+INCREMENT);
    }

    private double rounded(double value){
        BigDecimal decimal = BigDecimal.valueOf(value/INCREMENT);
        decimal = decimal.setScale(0, RoundingMode.FLOOR);
        return decimal.doubleValue()*INCREMENT;
    }

    public boolean contains(Coordinates point){
        return (point.getLat() >= this.coordinatesMin.getLat() &&
                point.getLon() >= this.coordinatesMin.getLon() &&
                point.getLat() < this.coordinatesMin.getLat() + INCREMENT &&
                point.getLon() < this.coordinatesMin.getLon() + INCREMENT);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zone)) return false;
        Zone zone = (Zone) o;
        return getCoordinatesMax().equals(zone.getCoordinatesMax()) && getCoordinatesMin().equals(zone.getCoordinatesMin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinatesMax(), getCoordinatesMin());
    }
}
