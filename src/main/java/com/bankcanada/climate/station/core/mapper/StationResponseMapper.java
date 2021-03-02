package com.bankcanada.climate.station.core.mapper;

import com.bankcanada.climate.station.core.model.Station;
import com.bankcanada.climate.station.rest.dto.StationResponse;
import com.bankcanada.climate.common.ResponseConverter;
import org.springframework.stereotype.Component;

@Component
public class StationResponseMapper implements ResponseConverter<Station, StationResponse>
{

    @Override
    public StationResponse toResponse (Station object) {
        StationResponse dto = new StationResponse();
        dto.setName(object.getName());
        dto.setProvince(object.getProvince());
        dto.setDate(object.getDate());
        dto.setMeanTemp(object.getMeanTemp());
        dto.setLowestMonthlyMinTemp(object.getLowestMonthlyMinTemp());
        dto.setHighestMonthlyMaxTemp(object.getHighestMonthlyMaxTemp());
        return dto;
    }
}
