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
    private Long id ;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private LocalDate date;

    private Double meanTemp;
    private Double highestMonthlyMaxTemp;
    private Double lowestMonthlyMinTemp;

    public WeatherStation(String name, String province, LocalDate date, Double meanTemp, Double highestMonthlyMaxTemp, Double lowestMonthlyMinTemp) {
        super();
        this.name = name;
        this.province = province;
        this.date = date;
        this.meanTemp = meanTemp;
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
        this.lowestMonthlyMinTemp = lowestMonthlyMinTemp;
    }
}
