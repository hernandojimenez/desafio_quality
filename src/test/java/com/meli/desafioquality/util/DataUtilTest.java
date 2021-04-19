package com.meli.desafioquality.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DataUtilTest {

    @Mock
    private DataUtil dataUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        dataUtil = new DataUtil();
    }

    @Test
    public void loadHotels() throws IOException {
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        HotelDataDTO listHotels = dataUtil.loadHotels();
        Assertions.assertEquals(listHotels,list);
    }
    @Test
    public void ischeckBetweenTrue() throws Exception {
        boolean result=false;
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        Date dateFrom= dataUtil.dateFormat("10/02/2021");
        Date dateTo= dataUtil.dateFormat("20/03/2021");
        for(HotelDTO hotelDTO: list.getHotels()){
            result = dataUtil.ischeckBetween(dateFrom,dateTo,hotelDTO);
        }
        Assertions.assertEquals(false,result);
    }

}