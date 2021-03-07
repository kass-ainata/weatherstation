package com.bankcanada.climate.station.core.service;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.bankcanada.climate.station.util.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static com.bankcanada.climate.station.util.Utils.API_V_1_STATIONS;


@SpringBootTest
@AutoConfigureMockMvc
class WeatherWeatherStationServiceImplTest
{
    public static final String RETURN_ALL_DATE_RANGE = "/?startDate=2000/01/01&endDate=2021/01/01";
    public static final String RETURN_NONE_DATE_RANGE = "/?startDate=2000/01/01&endDate=2000/01/01";
    public static final String RETURN_ONE_DATE_RANGE = "/?startDate=2017/10/20&endDate=2017/10/20";

    public static final String API_V_1_STATION_DETAILS = "/api/v1/stations/station-details";
    public static final String RETURN_ONE_STATION_DETAILS = "/?name=WABUSH A&province=NL&date=2018/10/20";

    @Autowired
    private MockMvc mockMvc;

    // see: bankcanada/test/src/main/resources/weather-station-data.csv - all tests will be done using this data
    // data loaded:
    // MARY'S HARBOUR A,NL,20/10/2018,7.1,21.5,-5.5
    // WABUSH A,NL,20/10/2018,6.3,21.5,-4.8

    @AfterEach
    void tearDown() {}

    @BeforeEach
    public void setUp() {}

    @Test
    public void testGetAllWeatherStations() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get(API_V_1_STATIONS))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(jsonPath("$[1].name").value("WABUSH A"))
                    .andExpect(jsonPath("$[1].province").value("NL"));
    }

    @Test
    public void testGetStationsByDateRangeAll() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.post(API_V_1_STATIONS + RETURN_ALL_DATE_RANGE))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[1].name").value("WABUSH A"))
               .andExpect(jsonPath("$[1].province").value("NL"));
    }

    @Test
    public void testGetStationsByDateRangeNone() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.post(API_V_1_STATIONS + RETURN_NONE_DATE_RANGE))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
               .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetStationsByDateRangeOne() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.post(API_V_1_STATIONS + RETURN_ONE_DATE_RANGE))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
               .andExpect(jsonPath("$", hasSize(1)));
    }

    // details
    @Test
    public void testGetStationDetails() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get(API_V_1_STATION_DETAILS + RETURN_ONE_STATION_DETAILS))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
               .andExpect(jsonPath("$.name").value("WABUSH A"))
               .andExpect(jsonPath("$.province").value("NL"));
    }

    //UIController
    @Test
    public void testGetAllWeatherStationsUiCont() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"));
    }
}