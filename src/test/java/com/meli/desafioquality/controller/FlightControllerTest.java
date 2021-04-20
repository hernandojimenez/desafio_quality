package com.meli.desafioquality.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.services.FlightService;
import com.meli.desafioquality.services.FlightServiceImpl;
import com.meli.desafioquality.util.ValidateConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @Mock
    private FlightController flightController;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        flightController = new FlightController(flightService);
    }
    @Test
    public void getFlightsTest() throws IOException, ParseException, ApiException {
        Map<String,String> params = new HashMap<>();
        FlightDataDTO dataDTO = objectMapper.readValue(new File(ValidateConfigurationTest.PATH_FLIGHT.getProperty()),
                new TypeReference<>() {
                });
        when(flightService.getFlights(params)).thenReturn(dataDTO);
        Assertions.assertEquals(flightController.getFlights(params).getBody(),dataDTO);
    }

}