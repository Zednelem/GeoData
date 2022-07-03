package com.example.density.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {


    @Test
    void containsTest() {
        Zone zone = new Zone(6.5,-7);
        Coordinates coordinates = new Coordinates(6.6,-6.6);
        assertTrue(zone.contains(coordinates));
    }
    @Test
    void constructorTest() {
        Zone zone;

        zone = new Zone(-2.1,38.1);

        assertTrue(zone.contains(zone.getCoordinatesMin()));
        assertFalse(zone.contains(zone.getCoordinatesMax()));

        assertEquals(-2.5,zone.getCoordinatesMin().getLat());
        assertEquals(38,zone.getCoordinatesMin().getLon());
        assertNotSame(-2, zone.getCoordinatesMax().getLat());
        assertEquals(38.5 , zone.getCoordinatesMax().getLon());

        zone = new Zone(6.6,-7.2);
        Coordinates coordinates = new Coordinates(6.5,-7.5);

        assertTrue(zone.contains(zone.getCoordinatesMin()));
        assertEquals(6.5,zone.getCoordinatesMin().getLat());
        assertEquals(-7.5,zone.getCoordinatesMin().getLon());
        assertNotSame(coordinates, zone.getCoordinatesMin());
        assertEquals(coordinates , zone.getCoordinatesMin());

    }

    @Test
    void doesNotContainsLonMinTest() {
        Zone zone = new Zone(6.5,-7);
        Coordinates coordinates = new Coordinates(6.6,-7.1);
        assertFalse(zone.contains(coordinates));
    }

    @Test
    void doesNotContainsLatMinTest() {
        Zone zone = new Zone(6.5,-7);
        Coordinates coordinates = new Coordinates(6.4,-6.6);
        assertFalse(zone.contains(coordinates));
    }
    @Test
    void doesNotContainsLonMaxTest() {
        Zone zone = new Zone(6.5,-7);
        Coordinates coordinates = new Coordinates(6.6,-6.5);
        assertFalse(zone.contains(coordinates));
    }
    @Test
    void doesNotContainsLatMaxTest() {
        Zone zone = new Zone(6.5,-7);
        Coordinates coordinates = new Coordinates(7,-6.6);


        assertFalse(zone.contains(coordinates));
    }


}
