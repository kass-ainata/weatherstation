package com.bankcanada.climate.station.ui;

import com.bankcanada.climate.station.core.service.WeatherStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
public class UIController
{
    private WeatherStationService stationService;
    //todo: add facade layer in order to de-couple service layer from controller layer

    @Autowired
    public UIController(WeatherStationService stationService) {
        this.stationService = stationService;
    }

    //first thing the user loads
    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("RequestDateRange", new ReqDateInterval());
        model.addAttribute("stations", stationService.getAllWeatherStations());
        return "index";
    }

    @PostMapping("/")
    public String getStationsByDateRange(Model model, @ModelAttribute ReqDateInterval dateRange) {
        model.addAttribute("RequestDateRange", dateRange);
        model.addAttribute("stations", stationService.findAllIntervalDates(dateRange.getFromDate(), dateRange.getToDate()));
        return "index";
    }

    //user clicks on mean temp hyper link
    @GetMapping("/details")
    public String getStationDetails(
            @RequestParam(name = "name", required = true, defaultValue = "")  String name,
            @RequestParam(name = "province", required = true, defaultValue = "")  String province,
            //todo: let thymeleaf/spring marshal into localdate
            @RequestParam(name = "date", required = true, defaultValue = "") String date,
            Model model) {
        LocalDate dateE = LocalDate.parse(date);
        model.addAttribute("station", stationService.getWeatherStationByNameProvDate(name, province, dateE));
        return "details";
    }

}