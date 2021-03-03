package com.bankcanada.climate.station.core.mapper;

import com.bankcanada.climate.station.core.model.Station;
import com.bankcanada.climate.station.rest.dto.StationResp;
import com.bankcanada.climate.common.ResponseConverter;
import org.springframework.stereotype.Component;

@Component
public class StationResponseConverter implements ResponseConverter<Station, StationResp>
{

    @Override
    public StationResp toResponse (Station object) {
        StationResp dto = new StationResp();
        dto.setName(object.getName());
        dto.setProvince(object.getProvince());
        dto.setDate(object.getDate());
        dto.setMeanTemp(object.getMeanTemp());
        dto.setLowestMonthlyMinTemp(object.getLowestMonthlyMinTemp());
        dto.setHighestMonthlyMaxTemp(object.getHighestMonthlyMaxTemp());
        return dto;
    }
}
