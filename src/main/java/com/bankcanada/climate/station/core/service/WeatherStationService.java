package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.rest.dto.StationResp;

import java.time.LocalDate;
import java.util.List;

public interface WeatherStationService
{
    List<StationResp> getAllWeatherStations();
    List<StationResp> findAllByDate(LocalDate aDate);
    List<StationResp> findAllIntervalDates(LocalDate fromDate, LocalDate toDate);
    List<StationResp> findAllIntervalDates(String fromDate, String toDate);
    StationResp getWeatherStationByNameProvDate(String aStationName, String aProvince, LocalDate aDate);
}
