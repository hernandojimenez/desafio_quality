package com.meli.desafioquality.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import com.meli.desafioquality.util.ValidateConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelRepositoryImplTest {

    @Mock
    private HotelRepository hotelRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        hotelRepository = new HotelRepositoryImpl();
    }

    @Test
    public void getHotels() throws IOException {
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        HotelDataDTO listData =hotelRepository.getHotels();
        Assertions.assertEquals(list,listData);
    }
    @Test
    public void getFilterHotelRangeDate() throws Exception {
        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL_FILTER_DATA.getProperty()),
                new TypeReference<>() {
                });
        HotelDataDTO listData= hotelRepository.getFilterHotelRangeDate("10/02/2021","20/03/2021","Puerto Iguazú");
        Assertions.assertEquals(list,listData);
    }

}