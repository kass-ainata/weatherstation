package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.model.WeatherStation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class WeatherWeatherStationServiceImplTest
{

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStations() {
        WeatherStation weatherStation = new WeatherStation("st", "adsf", LocalDate.now(), null, null, null);
    }


    @Test
    void getStationByName() {
    }
}