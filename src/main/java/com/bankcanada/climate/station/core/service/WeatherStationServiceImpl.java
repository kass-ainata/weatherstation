package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.mapper.StationResponseConverter;
import com.bankcanada.climate.station.core.repo.StationRepository;
import com.bankcanada.climate.station.rest.dto.StationResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherStationServiceImpl implements WeatherStationService
{

    Logger logger = LoggerFactory.getLogger(WeatherStationServiceImpl.class);

    private StationResponseConverter responseMapper;
    private StationRepository stationRepository;
    private StationDataLoader stationDataLoader;

    @Autowired
    public WeatherStationServiceImpl(StationResponseConverter responseMapper, StationRepository stationRepository, StationDataLoader stationDataLoader) {
        this.responseMapper = responseMapper;
        this.stationRepository = stationRepository;
        this.stationDataLoader = stationDataLoader;
        stationDataLoader.readLoadCsvData();
    }

    @Override
    public List<StationResp>  getAllStations() {
        return this.responseMapper.toResponseList(this.stationRepository.findAllByOrderByDateAsc());
    }

    @Override
    public StationResp getStationByNameAndProvAndDate(String stationName, String province, LocalDate date) {
        return this.responseMapper.toResponse(stationRepository.findByNameAndProvinceAndDate(stationName, province, date));
    }

    @Override
    public List<StationResp> findAllDateByDate(LocalDate date) {
        return this.responseMapper.toResponseList(stationRepository.findByDate(date));
    }

    @Override
    public List<StationResp> findAllDataBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        return this.responseMapper.toResponseList(stationRepository.findAllByDateBetweenOrderByDate(dateStart, dateEnd));
    }

    @Override
    public List<StationResp> findAllDataBetweenDates(String dateStart, String dateEnd) {
        if ((dateStart == null || dateStart.isEmpty()) && (dateEnd == null || dateEnd.isEmpty())) {
            return this.getAllStations();
        } else if (dateStart == null || dateStart.isEmpty()) {
            return this.findAllDateByDate(mapStringToLocalDate(dateEnd));
        } else if (dateEnd == null || dateEnd.isEmpty()) {
            return this.findAllDateByDate(mapStringToLocalDate(dateStart));
        } else {
            return this.findAllDataBetweenDates(mapStringToLocalDate(dateStart), mapStringToLocalDate(dateEnd));
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
            logger.error("failed to parse date");
            throw ex;
        }
        return dateToReturn;
    }
}
