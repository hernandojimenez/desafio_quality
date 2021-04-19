package com.meli.desafioquality.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.repositories.HotelRepository;
import com.meli.desafioquality.repositories.HotelRepositoryImpl;
import com.meli.desafioquality.util.ValidateConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        hotelRepository = new HotelRepositoryImpl();
    }
    @Test
    public void getHotelsTestAll() throws IOException {
        HotelDataDTO listData =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        HotelDataDTO list = hotelRepository.getHotels();
        Assertions.assertEquals(listData,list);
    }
    @Test
    public void getHotelsTestFilter() throws Exception {
        HotelDataDTO listData =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL_FILTER_DATA.getProperty()),
                new TypeReference<>() {
                });
        HotelDataDTO list = hotelRepository.getFilterHotelRangeDate("10/02/2021","20/03/2021","Puerto Iguazú");
        Assertions.assertEquals(listData,list);
    }

}