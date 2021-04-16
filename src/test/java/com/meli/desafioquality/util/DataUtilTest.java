package com.meli.desafioquality.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataUtilTest {

    private DataUtil dataUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        dataUtil = new DataUtil();
    }

    @Test
    public void loadHotels() throws IOException {
        List<HotelDTO> list =objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        List<HotelDTO> listHotels = dataUtil.loadHotels();
        Assertions.assertEquals(listHotels,list);
    }
    @Test
    public void ischeckBetweenTrue() throws IOException {
        boolean result=false;
        List<HotelDTO> list =objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        for(HotelDTO hotelDTO: list){
            result = dataUtil.ischeckBetween("10/02/2021","20/03/2021",hotelDTO);
        }
        Assertions.assertEquals(false,result);
    }

}