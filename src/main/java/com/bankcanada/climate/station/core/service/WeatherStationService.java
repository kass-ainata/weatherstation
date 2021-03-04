package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.rest.dto.WeatherStationResp;

import java.time.LocalDate;
import java.util.List;

public interface WeatherStationService
{
    List<WeatherStationResp> getAllWeatherStations();
    List<WeatherStationResp> findAllByDate(LocalDate aDate);
    List<WeatherStationResp> findAllIntervalDates(LocalDate fromDate, LocalDate toDate);
    List<WeatherStationResp> findAllIntervalDates(String fromDate, String toDate);
    WeatherStationResp getWeatherStationByNameProvDate(String aStationName, String aProvince, LocalDate aDate);
}
