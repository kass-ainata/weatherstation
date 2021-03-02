package com.boc.test.climatedata.core.service;

import com.boc.test.climatedata.core.model.Station;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StationServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStations() {
        Station station = new Station("st", "adsf", LocalDate.now(), null, null, null);
    }


    @Test
    void getStationByName() {
    }
}