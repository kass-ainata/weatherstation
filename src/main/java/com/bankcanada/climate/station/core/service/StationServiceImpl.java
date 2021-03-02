package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.mapper.StationResponseMapper;
import com.bankcanada.climate.station.core.model.Station;
import com.bankcanada.climate.station.core.repo.StationRepository;
import com.bankcanada.climate.station.rest.dto.StationResponse;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StationServiceImpl implements StationService {
    Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

    private StationResponseMapper responseMapper;
    private StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationResponseMapper responseMapper, StationRepository stationRepository) {
        this.responseMapper = responseMapper;
        this.stationRepository = stationRepository;
        insertDataIntoDB();
    }

    @Override
    public List<StationResponse>  getAllStations() {
        return this.responseMapper.toResponseList(this.stationRepository.findAllByOrderByDateAsc());
    }

    @Override
    public StationResponse getStationByNameAndProvAndDate(String stationName, String province, LocalDate date) {
        return this.responseMapper.toResponse(stationRepository.findByNameAndProvinceAndDate(stationName, province, date));
    }

    @Override
    public List<StationResponse> getStationsByName(String stationName) {
        return this.responseMapper.toResponseList(stationRepository.findAllByName(stationName));
    }

    @Override
    public List<StationResponse> findAllDateByDate(LocalDate date) {
        return this.responseMapper.toResponseList(stationRepository.findByDate(date));
    }

    @Override
    public List<StationResponse> findAllDataBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        return this.responseMapper.toResponseList(stationRepository.findAllByDateBetweenOrderByDate(dateStart, dateEnd));
    }

    @Override
    public List<StationResponse> findAllDataBetweenDates(String dateStart, String dateEnd) {
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

    /**
     * method used to initialize the in-mem database with csv data from data.csv in the resources folder (on the path)
     *
     */
    private void insertDataIntoDB() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Pattern datePattern = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d");

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource("data.csv").getFile());

            try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    String stationName = values[0];
                    String province = values[1];
                    LocalDate date = LocalDate.now();
                    Double meanTemp = null;
                    Double highestMonthlyMaxTemp = null;
                    Double lowestMonthlyMinTemp = null;

                    if (values[2] != null && datePattern.matcher(values[2]).matches()) {
                        date = LocalDate.parse(values[2], dateFormatter);
                    }

                    if (values[3] != null && numberPattern.matcher(values[3]).matches()) {
                        meanTemp = Double.parseDouble(values[3]);
                    }

                    if (values[4] != null && numberPattern.matcher(values[4]).matches()) {
                        highestMonthlyMaxTemp = Double.parseDouble(values[4]);
                    }

                    if (values[5] != null && numberPattern.matcher(values[5]).matches()) {
                        lowestMonthlyMinTemp = Double.parseDouble(values[5]);
                    }

                    this.stationRepository.save(new Station(stationName, province,  date,  meanTemp, highestMonthlyMaxTemp, lowestMonthlyMinTemp));
                }
            }
        } catch (Exception ex) {
            //todo: fail app start-up if data is not successfully loaded into db
            logger.error("failed to load weather data into database", ex);
        }
    }

}
