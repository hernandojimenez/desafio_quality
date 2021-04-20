package com.meli.desafioquality.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
            result = dataUtil.ischeckBetween(hotelDTO.getDateFrom(),hotelDTO.getDateTo(),dateFrom,dateTo);
        }
        Assertions.assertEquals(false,result);
    }
    @Test
    public void dateFormatTest_OK() throws ParseException, ApiException {
        String stringDate="10/02/2021";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //15/04/2021
        Date dateFrom= dateFormat.parse(stringDate);

        Date dateTo = dataUtil.dateFormat(stringDate);
        Assertions.assertEquals(dateFrom,dateTo);
    }
    @Test
    public void calculateTotalTest_Credit() throws ApiException {
        String typeCard="CREDIT";
        int dues=4;
        float price=7020.0f;
        int numberPeople=2;
        Map<String,Float> paramsIn = new HashMap<>();
        paramsIn.put("total", (float) ((price*numberPeople) + (price *0.10)));
        paramsIn.put("amount",(price*numberPeople));
        paramsIn.put("interest",10.0f);
        Map<String,Float> paramsOut = new HashMap<>();
        paramsOut = dataUtil.calculateTotal(typeCard,dues,price,numberPeople);
        Assertions.assertEquals(paramsIn,paramsOut);
    }
    @Test
    public void calculateTotalTest_Debit() throws ApiException {
        String typeCard="DEBIT";
        int dues=1;
        float price=7020.0f;
        int numberPeople=2;
        Map<String,Float> paramsIn = new HashMap<>();
        paramsIn.put("total", (float) (price*numberPeople));
        paramsIn.put("amount",(price*numberPeople));
        paramsIn.put("interest",0.0f);
        Map<String,Float> paramsOut = new HashMap<>();
        paramsOut = dataUtil.calculateTotal(typeCard,dues,price,numberPeople);
        Assertions.assertEquals(paramsIn,paramsOut);
    }
    @Test
    public void validateFormatEmailTest() throws ApiException, IOException {
        RequestBookingDTO request= objectMapper.readValue(new File(ValidateConfigurationTest.PATH_REQUEST.getProperty()),
                new TypeReference<>() {
                });
        String email="seba_gonzalez@unmail.com";
        Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        boolean resultMock=regex.matcher(email).matches() ? true : false;

        boolean result=dataUtil.validateFormatEmail(email,request.getBooking().getPeople());
        Assertions.assertEquals(resultMock,result);
    }


}