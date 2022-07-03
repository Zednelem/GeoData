package com.example.density.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DensityZone {
    private final Zone zone;
    private int density;

    public DensityZone(Zone zone){
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DensityZone)) return false;
        DensityZone that = (DensityZone) o;
        return getDensity() == that.getDensity() && getZone().equals(that.getZone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZone(), getDensity());
    }

    public void incrementDensity() {
        this.density ++;
    }
}
