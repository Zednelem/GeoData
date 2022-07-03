package com.example.density.dto;

import com.example.density.model.Zone;
import lombok.Data;

@Data
public class ZoneDto {
    private double minLat,maxLat,minLon,maxLon;
    public ZoneDto(Zone zone){
        this.maxLat = zone.getCoordinatesMax().getLat();
        this.maxLon = zone.getCoordinatesMax().getLon();
        this.minLat = zone.getCoordinatesMin().getLat();
        this.minLon = zone.getCoordinatesMin().getLon();
    }
}
