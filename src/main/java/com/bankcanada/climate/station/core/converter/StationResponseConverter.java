package com.bankcanada.climate.station.core.converter;

import com.bankcanada.climate.station.core.model.WeatherStation;
import com.bankcanada.climate.station.dto.WeatherStationResp;
import com.bankcanada.climate.common.ResponseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StationResponseConverter implements ResponseConverter<WeatherStation, WeatherStationResp>
{

    @Override
    public WeatherStationResp toResponseDto(WeatherStation sourceObj) {

        WeatherStationResp stationResp = new WeatherStationResp();
        stationResp.setName(sourceObj.getName());
        stationResp.setProvince(sourceObj.getProvince());
        stationResp.setDate(sourceObj.getDate());
        stationResp.setMeanTemp(sourceObj.getMeanTemp());
        stationResp.setMonthlyMinTemp(sourceObj.getMonthlyMinTemp());
        stationResp.setMonthlyMaxTemp(sourceObj.getMonthlyMaxTemp());
        return stationResp;
    }

    /*

    private final static ObjectMapper mapper = new ObjectMapper();
    @Override
    public StationResp toResponseDto(Station request)
    {
        try
        {
            return mapper.readValue(mapper.writeValueAsString(request), StationResp.class);
        }
        catch (IOException e)
        {
            log.error("Unexpected error happened. Failed to convert {}", request);
            return null;
        }
    }

     */
}
