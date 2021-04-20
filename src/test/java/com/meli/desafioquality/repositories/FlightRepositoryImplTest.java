package com.meli.desafioquality.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.FlightDTO;
import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.dtos.RequestFlightDTO;
import com.meli.desafioquality.dtos.ResponseFlightDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public  class FlightRepositoryImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private DataUtil dataUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        flightRepository = new FlightRepositoryImpl();
        dataUtil = new DataUtil();
    }

    @Test
    public void getFlightsTest() throws IOException {
        FlightDataDTO dataDTO = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_FLIGHT.getProperty()),
                new TypeReference<>() {
                });
        FlightDataDTO data = flightRepository.getFlights();
        Assertions.assertEquals(dataDTO, data);
    }

    @Test
    public void getFilterFlightRangeDateTest() throws IOException, ParseException, ApiException {
        FlightDataDTO dataDTO = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_FLIGHT_FILTER.getProperty()),
                new TypeReference<>() {
                });
        Date dateFrom = dataUtil.dateFormat("10/01/2021");
        Date dateTo = dataUtil.dateFormat("01/05/2021");
        FlightDataDTO dataMock = flightRepository.getFilterFlightRangeDate(dateFrom, dateTo, "Buenos Aires", dataDTO);
        Assertions.assertEquals(dataDTO, dataMock);
    }
    @Test
    public void createFlightReservationTest() throws IOException, ApiException, ParseException {
        RequestFlightDTO request = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_REQUEST_FLIGHT.getProperty()),
                new TypeReference<>() {
                });
        FlightDataDTO dataDTO = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_FLIGHT.getProperty()),
                new TypeReference<>() {
                });
        ResponseFlightDTO responseMock = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_RESPONSE_FLIGHT.getProperty()),
                new TypeReference<>() {
                });
        ResponseFlightDTO response = new ResponseFlightDTO();
        Date dateFrom = dataUtil.dateFormat("10/01/2021");
        Date dateTo = dataUtil.dateFormat("01/05/2021");
        for (FlightDTO flightDTO : dataDTO.getFlight()) {
            if (dataUtil.ischeckBetween(flightDTO.getDateFrom(), flightDTO.getDateTo(), dateFrom, dateTo)) {
                response = flightRepository.createFlightReservation(request, flightDTO);
            }

        }
        Assertions.assertEquals(responseMock,response);

    }
}