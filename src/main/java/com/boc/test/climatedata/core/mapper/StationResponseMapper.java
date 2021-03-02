package com.boc.test.climatedata.core.mapper;

import com.boc.test.climatedata.core.model.Station;
import com.boc.test.climatedata.rest.dto.StationResponse;
import com.boc.test.common.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class StationResponseMapper implements ResponseMapper<Station, StationResponse> {

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
