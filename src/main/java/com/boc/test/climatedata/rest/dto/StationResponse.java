package com.boc.test.climatedata.rest.dto;

import java.time.LocalDate;

public class StationResponse {
    private String name;
    private String province;
    private LocalDate date;
    private Double meanTemp;
    private Double highestMonthlyMaxTemp;
    private Double lowestMonthlyMinTemp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(Double meanTemp) {
        this.meanTemp = meanTemp;
    }

    public Double getHighestMonthlyMaxTemp() {
        return highestMonthlyMaxTemp;
    }

    public void setHighestMonthlyMaxTemp(Double highestMonthlyMaxTemp) {
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
    }

    public Double getLowestMonthlyMinTemp() {
        return lowestMonthlyMinTemp;
    }

    public void setLowestMonthlyMinTemp(Double lowestMonthlyMinTemp) {
        this.lowestMonthlyMinTemp = lowestMonthlyMinTemp;
    }
}
