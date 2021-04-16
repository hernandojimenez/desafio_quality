package com.meli.desafioquality.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<HotelDTO> data;

    //read json and load hotel list
    public  List<HotelDTO> loadHotels() throws IOException {
        data = objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        return data;
    }
    //validate if data exists between a date range
    public boolean ischeckBetween(String dateFrom, String dateTo, HotelDTO listData){
        boolean result = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //15/04/2021
        try {
            result = dateFormat.parse(listData.getDateFrom()).compareTo(dateFormat.parse(dateFrom)) >=0
                    && dateFormat.parse(listData.getDateTo()).compareTo(dateFormat.parse(dateTo)) <=0;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return result;
    }

}
