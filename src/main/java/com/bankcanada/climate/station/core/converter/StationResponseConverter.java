package com.bankcanada.climate.station.core.converter;

import com.bankcanada.climate.station.core.model.WeatherStation;
import com.bankcanada.climate.station.rest.dto.WeatherStationResp;
import com.bankcanada.climate.common.ResponseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StationResponseConverter implements ResponseConverter<WeatherStation, WeatherStationResp>
{

    @Override
    public WeatherStationResp toResponseDto(WeatherStation sourceObj) {

        WeatherStationResp dto = new WeatherStationResp();
        dto.setName(sourceObj.getName());
        dto.setProvince(sourceObj.getProvince());
        dto.setDate(sourceObj.getDate());
        dto.setMeanTemp(sourceObj.getMeanTemp());
        dto.setLowestMonthlyMinTemp(sourceObj.getLowestMonthlyMinTemp());
        dto.setHighestMonthlyMaxTemp(sourceObj.getHighestMonthlyMaxTemp());
        return dto;
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
