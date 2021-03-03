package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.rest.dto.StationResp;

import java.time.LocalDate;
import java.util.List;

public interface WeatherStationService
{
    List<StationResp> getAllStations();
    StationResp getStationByNameAndProvAndDate(String stationName, String province, LocalDate date);
    List<StationResp> findAllDateByDate(LocalDate date);
    List<StationResp> findAllDataBetweenDates(LocalDate dateStart, LocalDate dateEnd);
    List<StationResp> findAllDataBetweenDates(String dateStart, String dateEnd);
}
