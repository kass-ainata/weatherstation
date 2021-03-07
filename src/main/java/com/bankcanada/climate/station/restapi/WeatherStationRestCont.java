package com.bankcanada.climate.station.restapi;

import com.bankcanada.climate.station.core.service.WeatherStationService;
import com.bankcanada.climate.station.dto.WeatherStationResp;
import com.bankcanada.climate.station.ui.ReqDateInterval;
import com.bankcanada.climate.station.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static com.bankcanada.climate.station.util.Utils.toLocalDate;
import static com.bankcanada.climate.station.util.Utils.API_V_1_STATIONS;

import java.time.LocalDate;
import java.util.List;

/**
 * REST API endpoints
 */
@AllArgsConstructor
@RestController
@RequestMapping(API_V_1_STATIONS)
public class WeatherStationRestCont
{

    private WeatherStationService stationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WeatherStationResp> getAllStations() {
        return this.stationService.getAllWeatherStations();
    }

    @PostMapping("/")
    public List<WeatherStationResp> getStationsByDateRange(
            @RequestParam(defaultValue = "") String startDate,
            @RequestParam(defaultValue = "") String endDate) {

        return stationService.findAllIntervalDates(startDate, endDate);
    }
    @GetMapping("/station-details")
    public WeatherStationResp getStationDetails(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String province,
            @RequestParam(defaultValue = "") String date) {
         var o =stationService.getWeatherStationByNameProvDate(name, province, Utils.toLocalDate(date));

        return o;
    }
}
