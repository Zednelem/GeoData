package com.example.density.service;

import com.example.density.model.Zone;

import java.util.Set;

public interface ZoneService {
    long getDensity(Zone zone);

    Set<Zone> getMostDenseZonesWithSize(int count);
}
