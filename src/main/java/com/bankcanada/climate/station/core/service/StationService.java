package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.rest.dto.StationResponse;

import java.time.LocalDate;
import java.util.List;

public interface StationService {
    public List<StationResponse> getAllStations();
    StationResponse getStationByNameAndProvAndDate(String stationName, String province, LocalDate date);
    List<StationResponse> getStationsByName(String stationName);
    List<StationResponse> findAllDateByDate(LocalDate date);
    List<StationResponse> findAllDataBetweenDates(LocalDate dateStart, LocalDate dateEnd);
    List<StationResponse> findAllDataBetweenDates(String dateStart, String dateEnd);
}
