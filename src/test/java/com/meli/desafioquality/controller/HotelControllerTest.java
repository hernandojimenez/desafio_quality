package com.meli.desafioquality.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.services.HotelService;
import com.meli.desafioquality.util.ValidateConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @Mock
    private HotelController hotelController;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        hotelController = new HotelController(hotelService);
    }
    @Test
    public void getHotelsTestAll() throws Exception {
        Map<String,String> params = new HashMap<>();
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        when(hotelService.getHotels(any())).thenReturn(list);
        Assertions.assertEquals(hotelController.getHotels(params).getBody(),list);
    }
    @Test
    public void getHotelsTestFilter() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom","10/02/2021");
        params.put("dateTo","20/03/2021");
        params.put("destination","Puerto Iguazú");
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL_FILTER_DATA.getProperty()),
                new TypeReference<>() {
                });
        when(hotelService.getHotels(any())).thenReturn(list);
        Assertions.assertEquals(hotelController.getHotels(params).getBody(),list);
    }


}