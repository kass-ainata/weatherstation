package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.model.WeatherStation;
import com.bankcanada.climate.station.core.repo.WeatherStationRepository;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Slf4j
@AllArgsConstructor
@Service
public class WeatherStationDataLoader
{
    public static final String DATE_REGEX = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DOUBLE_REGEX = "-?\\d+(\\.\\d+)?";
    public static final String CSV_FILE = "weather-station-data.csv";

    WeatherStationRepository weatherStationRepository;

    /**
     *  loads the csv file
     *  loads the data in the database
     */
    public void readLoadCsvData() {

        BufferedReader bufferedReader = null;
        CSVReader csvReader = null;
        try
        {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new ClassPathResource(CSV_FILE).getInputStream()));
            csvReader = new CSVReader(bufferedReader);
            String[] line = null;
            while ((line = csvReader.readNext()) != null)
            {
                weatherStationRepository.save(new WeatherStation(line[0], line[1], getDate(line[2]),
                        getParseDouble(line[3]), getParseDouble(line[4]), getParseDouble(line[5])));
            }
        } catch (Exception ex) {
            log.error("Unexpected error happened, failed to load the cvs data into the database", ex);
        } finally
        {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (csvReader != null) csvReader.close();
            } catch (Exception e) {
                log.error("Unexpected error happened, failed to load the cvs data into the database", e);
            }
        }
    }
    private LocalDate getDate(String dateStr){
        if (dateStr != null && Pattern.compile(DATE_REGEX).matcher(dateStr).matches()) {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
        }
        return LocalDate.now();
    }

    private Double getParseDouble(String dString){
        if (dString != null && Pattern.compile(DOUBLE_REGEX).matcher(dString).matches()) {
            return Double.parseDouble(dString);
        }
        return null;
    }
}
