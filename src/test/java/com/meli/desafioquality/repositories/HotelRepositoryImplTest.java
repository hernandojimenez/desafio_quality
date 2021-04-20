package com.meli.desafioquality.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
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
import java.util.Date;

public class HotelRepositoryImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private DataUtil dataUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        hotelRepository = new HotelRepositoryImpl();
        dataUtil = new DataUtil();
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
        Date dateFrom= dataUtil.dateFormat("10/02/2021");
        Date dateTo= dataUtil.dateFormat("20/03/2021");
        HotelDataDTO listData= hotelRepository.getFilterHotelRangeDate(dateFrom,dateTo,"Puerto Iguazú");
        Assertions.assertEquals(list,listData);
    }
    @Test
    public void createBookingTest_OK() throws IOException, ApiException {
        RequestBookingDTO request= objectMapper.readValue(new File(ValidateConfigurationTest.PATH_REQUEST.getProperty()),
                new TypeReference<>() {
                });
        ResponseBookingDTO response =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_RESPONSE.getProperty()),
                new TypeReference<>() {
                });

        HotelDataDTO list =objectMapper.readValue(new File(ValidateConfigurationTest.PATH_HOTEL_FILTER_DATA.getProperty()),
                new TypeReference<>() {
                });
        ResponseBookingDTO responseMock = new ResponseBookingDTO();
        for(HotelDTO hotelDTO:list.getHotels()){
            responseMock=hotelRepository.createBooking(request,hotelDTO);
        }
        Assertions.assertEquals(response,responseMock);
    }

}