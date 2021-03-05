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
    //todo: enhancement - the service layer should be always decoupled from the
    // controller layer using using an orch layer
    private WeatherStationService weatherStationService;

    @Autowired
    public UIController(WeatherStationService weatherStationService) {
        this.weatherStationService = weatherStationService;
    }

    /**
     * When the user provieds a date range
     *
     * @param model
     * @param dateRange
     * @return
     */
    @PostMapping("/")
    public String getStationsByDateRange(Model model, @ModelAttribute ReqDateInterval dateRange) {
        model.addAttribute("ReqDateRange", dateRange);
        model.addAttribute("stations", weatherStationService.findAllIntervalDates(dateRange.getFromDate(), dateRange.getToDate()));
        return "index";
    }

    /**
     * initial page
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("ReqDateRange", new ReqDateInterval());
        model.addAttribute("stations", weatherStationService.getAllWeatherStations());
        return "index";
    }

    /**
     * When the user gets the station details
     *
     * @param name
     * @param province
     * @param date
     * @param model
     * @return
     */
    @GetMapping("/station-details")
    public String getStationDetails(
            @RequestParam(name = "name", required = true, defaultValue = "")  String name,
            @RequestParam(name = "province", required = true, defaultValue = "")  String province,
            @RequestParam(name = "date", required = true, defaultValue = "") String date,
            Model model) {
        model.addAttribute("station", weatherStationService.getWeatherStationByNameProvDate(name, province, LocalDate.parse(date)));
        return "station-details";
    }
}