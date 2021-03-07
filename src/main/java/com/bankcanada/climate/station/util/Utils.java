package com.bankcanada.climate.station.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Utils
{
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final String API_V_1_STATIONS = "/api/v1/stations";

    public static LocalDate toLocalDate(String dateStr) {
        LocalDate dateToReturn;
        try {
            dateToReturn = LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (Exception e) {
            //Todo - need to handle this better
            log.error("Unexpected error happened "+ e.getMessage());
            throw e;
        }
        return dateToReturn;
    }
}
