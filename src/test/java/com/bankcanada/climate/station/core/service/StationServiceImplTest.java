package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.model.Station;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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