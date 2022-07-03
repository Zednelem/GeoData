package com.example.density.service;

import com.example.density.model.DensityZone;
import com.example.density.model.PointOfInterest;
import com.example.density.model.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ZoneServiceImpl implements ZoneService {

    final TSVFileReader tsvFileReader;

    public ZoneServiceImpl(TSVFileReader tsvFileReader) {
        this.tsvFileReader = tsvFileReader;
    }

    @Override
    public long getDensity(Zone zone) {
        var pois = tsvFileReader.fetch();

        return pois.stream().filter(poi -> zone.contains(poi.getCoordinates())).count();
    }

    @Override
    public Set<Zone> getMostDenseZonesWithSize(int count) {
        var pois = tsvFileReader.fetch();

        List<Zone> zones = getZonesOf(pois);

        var densityZones = initDensityZones(zones);

        computeZoneDensity(zones, densityZones);

        // on ordonne les zones par ordre décroissant de densité
        densityZones = densityZones.stream().sorted(
                Comparator.comparingInt(DensityZone::getDensity))
                .collect(Collectors.toList());
        Collections.reverse(densityZones);

        if (densityZones.size() < count) {
            return densityZones.stream().map(DensityZone::getZone).collect(Collectors.toSet());
        } else {
            return densityZones.subList(0, count).stream().map(DensityZone::getZone).collect(Collectors.toSet());
        }
    }

    private void computeZoneDensity(List<Zone> zones, List<DensityZone> densityZones) {
        for (Zone zone : zones) {
            densityZones.stream()
                    .filter(dZone -> dZone.getZone().equals(zone))
                    .findFirst()
                    .ifPresent(DensityZone::incrementDensity);
        }
    }

    private List<DensityZone> initDensityZones(List<Zone> zones) {
        return zones.stream()
                .map(DensityZone::new)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Zone> getZonesOf(List<PointOfInterest> pois) {
        return pois.stream()
                .map(poi -> new Zone(poi.getLat(), poi.getLon()))
                .collect(Collectors.toList());
    }
}
