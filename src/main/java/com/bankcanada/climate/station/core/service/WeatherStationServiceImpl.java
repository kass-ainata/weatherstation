package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.mapper.StationResponseConverter;
import com.bankcanada.climate.station.core.repo.WeatherStationRepository;
import com.bankcanada.climate.station.rest.dto.StationResp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
@Slf4j
public class WeatherStationServiceImpl implements WeatherStationService
{
    private StationResponseConverter responseMapper;
    private WeatherStationRepository weatherStationRepository;
    private WeatherStationDataLoader weatherStationDataLoader;


    @PostConstruct
    private void init(){
        weatherStationDataLoader.readLoadCsvData();
    }

    @Override
    public List<StationResp> getAllWeatherStations() {
        return this.responseMapper.toResponseList(this.weatherStationRepository.findAllByOrderByDateAsc());
    }

    @Override
    public StationResp getWeatherStationByNameProvDate(String stationName, String province, LocalDate date) {
        return this.responseMapper.toResponse(
                weatherStationRepository.findByNameAndProvinceAndDate(stationName, province, date));
    }

    @Override
    public List<StationResp> findAllByDate(LocalDate date) {
        return this.responseMapper.toResponseList(weatherStationRepository.findByDate(date));
    }

    @Override
    public List<StationResp> findAllIntervalDates(LocalDate dateStart, LocalDate dateEnd) {
        return this.responseMapper.toResponseList(
                weatherStationRepository.findAllByDateBetweenOrderByDate(dateStart, dateEnd));
    }

    @Override
    public List<StationResp> findAllIntervalDates(String dateStart, String dateEnd) {
        if ((dateStart == null || dateStart.isEmpty()) && (dateEnd == null || dateEnd.isEmpty())) {
            return this.getAllWeatherStations();
        } else if (dateStart == null || dateStart.isEmpty()) {
            return this.findAllByDate(mapStringToLocalDate(dateEnd));
        } else if (dateEnd == null || dateEnd.isEmpty()) {
            return this.findAllByDate(mapStringToLocalDate(dateStart));
        } else {
            return this.findAllIntervalDates(mapStringToLocalDate(dateStart), mapStringToLocalDate(dateEnd));
        }
    }

    //todo: move this to a mapper class
    //todo: must handle exception when string can't be parsed into localdate - create exception handler
    private LocalDate mapStringToLocalDate (String date) {
        LocalDate dateToReturn;
        try {
             dateToReturn = LocalDate.parse(date);
        } catch (Exception ex) {
            //todo: throw special exception - add exception handler
            log.error("failed to parse date");
            throw ex;
        }
        return dateToReturn;
    }
}
