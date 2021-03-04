package com.bankcanada.climate.station.rest.controller;

import com.bankcanada.climate.station.core.service.WeatherStationService;
import com.bankcanada.climate.station.rest.dto.StationResp;
import com.bankcanada.climate.common.Constants;
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
@RequestMapping(Constants.V1_PATH_PREFIX + "/stations")
public class ClimateRestController {
    private WeatherStationService stationService;

    @Autowired
    public ClimateRestController(WeatherStationService stationService) {
        this.stationService = stationService;

    }


    //todo: add rest resource to enable pagination as the data set is quite large

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StationResp> getAllStations() {
        return this.stationService.getAllWeatherStations();
    }
}
