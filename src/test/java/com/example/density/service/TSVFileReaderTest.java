package com.example.density.service;

import com.example.density.model.PointOfInterest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class TSVFileReaderTest {


    @Autowired
    private TSVFileReader tsvFileReader;

    @Configuration
    @Import(TSVFileReaderImpl.class)
    static class Config {
    }

    @Test
    void testInjectionOk() {
        assertNotNull(tsvFileReader);
    }

    @Test
    void testLoadData() {
        List<PointOfInterest> pois = tsvFileReader.fetch();
        assertNotNull(pois);
        assertNotEquals(0,pois.size());
        assertEquals("id1",pois.get(0).getId());
        assertEquals(-48.6,pois.get(0).getLat());
        assertEquals(-37.7,pois.get(0).getLon());
    }

}
