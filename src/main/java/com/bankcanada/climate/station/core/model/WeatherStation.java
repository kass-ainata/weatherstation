package com.bankcanada.climate.station.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor()
@Entity
public class WeatherStation
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private LocalDate date;

    private Double meanTemp;
    private Double monthlyMaxTemp;
    private Double monthlyMinTemp;

    public WeatherStation(String name, String province, LocalDate date, Double meanTemp, Double monthlyMaxTemp, Double monthlyMinTemp) {
        super();
        this.name = name;
        this.province = province;
        this.date = date;
        this.meanTemp = meanTemp;
        this.monthlyMaxTemp = monthlyMaxTemp;
        this.monthlyMinTemp = monthlyMinTemp;
    }
}
