package com.bankcanada.climate.station.core.service;

import com.bankcanada.climate.station.core.model.Station;
import com.bankcanada.climate.station.core.repo.StationRepository;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StationDataLoader
{
    public static final String DATE_REGEX = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DOUBLE_REGEX = "-?\\d+(\\.\\d+)?";
    public static final String CSV_FILE = "data.csv";

    StationRepository stationRepository;
    Logger logger = LoggerFactory.getLogger(StationDataLoader.class);

    public StationDataLoader(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    /**
     *  loads the csv file
     *  loads the data in the database
     */
    public void readLoadCsvData() {
        try {
            for (String[] line : readAll()) {
                this.stationRepository.save(new Station(line[0], line[1],  getDate(line[2]),
                        getParseDouble(line[3]), getParseDouble(line[4]), getParseDouble(line[5])));
            }
        } catch (Exception ex) {
            logger.error("Unexpected error occured, failed to load the cvs data into the database", ex);
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

    private List<String[]> readAll() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(CSV_FILE).toURI()));

        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }
}
