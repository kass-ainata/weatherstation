package com.boc.test.climatedata.pages.controller;

import com.boc.test.climatedata.core.service.StationService;
import com.boc.test.climatedata.pages.dto.RequestDateRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ClimatePagesController {
    Logger logger = LoggerFactory.getLogger(ClimatePagesController.class);

    private StationService stationService;
    //todo: add facade layer in order to de-couple service layer from controller layer

    @Autowired
    public ClimatePagesController(StationService stationService) {
        this.stationService = stationService;
    }

    //first thing the user loads
    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("RequestDateRange", new RequestDateRange());
        model.addAttribute("stations", stationService.getAllStations());
        return "index";
    }

    @PostMapping("/")
    public String getStationsByDateRange(Model model, @ModelAttribute RequestDateRange dateRange) {
        model.addAttribute("RequestDateRange", dateRange);
        model.addAttribute("stations", stationService.findAllDataBetweenDates(dateRange.getStartDate(), dateRange.getEndDate()));
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
        model.addAttribute("station", stationService.getStationByNameAndProvAndDate(name, province, dateE));
        return "details";
    }

}