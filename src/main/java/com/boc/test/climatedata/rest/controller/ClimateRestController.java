package com.boc.test.climatedata.rest.controller;

import com.boc.test.climatedata.core.service.StationService;
import com.boc.test.climatedata.rest.dto.StationResponse;
import com.boc.test.common.constants.RestConstants;
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
@RequestMapping(RestConstants.V1_PATH_PREFIX + "/stations")
public class ClimateRestController {
    private StationService stationService;

    @Autowired
    public ClimateRestController(StationService stationService) {
        this.stationService = stationService;

    }


    //todo: add rest resource to enable pagination as the data set is quite large

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StationResponse> getAllStations() {
        return this.stationService.getAllStations();
    }
}
