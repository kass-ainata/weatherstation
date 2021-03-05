package com.bankcanada.climate.station.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeatherStationResp
{
    private Long id;
    private String name;
    private String province;
    private LocalDate date;
    private Double meanTemp;
    private Double monthlyMaxTemp;
    private Double monthlyMinTemp;
}
