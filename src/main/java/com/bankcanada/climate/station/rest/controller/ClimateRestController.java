package com.bankcanada.climate.station.rest.controller;

import com.bankcanada.climate.station.core.service.WeatherStationService;
import com.bankcanada.climate.station.rest.dto.WeatherStationResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST endpoints for retrieving climate data
 */
@RestController
@RequestMapping( "/api/v1/stations")
public class ClimateRestController {
    private WeatherStationService stationService;

    @Autowired
    public ClimateRestController(WeatherStationService stationService) {
        this.stationService = stationService;

    }


    //todo: add rest resource to enable pagination as the data set is quite large

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WeatherStationResp> getAllStations() {
        return this.stationService.getAllWeatherStations();
    }
}
