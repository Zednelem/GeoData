package com.example.density.service;

import com.example.density.model.PointOfInterest;
import com.example.density.model.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration()
public class ZoneServiceTest {

    @Autowired
    ZoneService zoneService;

    @MockBean
    TSVFileReader tsvFileReader;

    List<PointOfInterest> expectedPOIs;

    @Configuration
    @Import({ZoneServiceImpl.class})
    static class Config{}


    @BeforeEach
    public void init(){

    }

    @Test
    void testInjection(){
        assertNotNull(zoneService);
        assertNotNull(tsvFileReader);
    }

    @Test
    void testGetZoneDensity(){
        expectedPOIs = Stream.of(6.4,6.5,6.7,6.8,6.9,7.0,7.1)
                .map(value-> new PointOfInterest("id"+value,value,-6.6))
                .collect(Collectors.toList());


        when(tsvFileReader.fetch()).thenReturn(expectedPOIs);

        var zone = new Zone(6.5,-7);
        long density = zoneService.getDensity(zone);

        assertEquals(4, density);
    }

    @Test
    void test(){
        expectedPOIs = Stream.of(6.4,6.5,6.7,6.8,6.9,7.0,7.1)
                .map(value-> new PointOfInterest("id"+value,value,-6.6))
                .collect(Collectors.toList());
        expectedPOIs.addAll(Stream.of(6.4,6.5,6.7,6.8,6.9,7.0,7.1)
                .map(value-> new PointOfInterest("id"+value + "lon",7.05,value))
                .collect(Collectors.toList()));
        when(tsvFileReader.fetch()).thenReturn(expectedPOIs);



        var zones = zoneService.getMostDenseZonesWithSize(5);

        assertEquals(5,zones.size());
        assertTrue(zones.contains(new Zone(6.5,-7)));
        assertTrue(zones.contains(new Zone(7,6.5)));
        assertFalse(zones.contains(new Zone(0,-7)));
        assertFalse(zones.contains(new Zone(-74.8,6.5)));
    }


}
