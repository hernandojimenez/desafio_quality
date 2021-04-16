package com.meli.desafioquality.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelRepositoryImplTest {

    private DataUtil dataUtil;

    private HotelRepository hotelRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        dataUtil = new DataUtil();
        hotelRepository = new HotelRepositoryImpl();
    }

    @Test
    public void getHotels() throws IOException {
        List<HotelDTO> list =objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        List<HotelDTO> listData =hotelRepository.getHotels();
        Assertions.assertEquals(list,listData);
    }
    @Test
    public void getFilterHotelRangeDate() throws IOException {
        List<HotelDTO> list =objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL_FILTER_DATE_TEST.getProperty()),
                new TypeReference<>() {
                });
        List<HotelDTO> listData= hotelRepository.getFilterHotelRangeDate("10/02/2021","20/03/2021","Puerto Iguazú");
        Assertions.assertEquals(list,listData);
    }

}