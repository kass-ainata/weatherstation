package com.bankcanada.climate.station.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
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
