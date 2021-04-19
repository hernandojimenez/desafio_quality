package com.meli.desafioquality.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.exception.ApiException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static HotelDataDTO data;
    private float total=0;
    private float amount=0;
    private float interest=0;
    private boolean validateDate=false;

    //read json and load hotel list
    public HotelDataDTO loadHotels() throws IOException {
        data = objectMapper.readValue(new File(ValidateConfiguration.PATH_HOTEL.getProperty()),
                new TypeReference<>() {
                });
        return data;
    }
    //validate if data exists between a date range
    public boolean ischeckBetween(Date dateEntry, Date dateFinal, HotelDTO listData) throws ParseException, ApiException {
        boolean result = false;

            if(dateEntry.compareTo(dateFinal)>0){
                throw new ApiException(ValidateConfiguration.LESS_DATE.getProperty());
            }else{
                result = dateFormat(listData.getDateFrom()).compareTo(dateEntry) >=0
                        && dateFormat(listData.getDateTo()).compareTo(dateFinal) <=0;
            }
        return result;
    }
    public Date dateFormat(String stringDate) throws ParseException, ApiException {
        boolean value=false;
        String regex = "^[0-3]{1}[0-9]{1}/[0-3]{1}[0-9]{1}/[1-9]{1}[0-9]{3}$";
        value = Pattern.matches(regex, stringDate);
        Date date = new Date();
        if(value){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //15/04/2021
            date = dateFormat.parse(stringDate);
        }else{
            throw new ApiException(ValidateConfiguration.DATE_FORMAT.getProperty());
        }
        return date;
    }
    public Map<String,Float> calculateTotal(HotelDTO listHotels, RequestBookingDTO request){
        Map<String,Float> params = new HashMap<>();
        if(request.getBooking().getPaymentMethod().getType().equals(ValidateConfiguration.TYPE_CREDIT.getProperty())){
            if(request.getBooking().getPaymentMethod().getDues()>3){
                total +=(listHotels.getNightPrice() * request.getBooking().getPeopleAmount())
                        + (listHotels.getNightPrice() * request.getBooking().getPeopleAmount()) * 0.10;
                params.put("amount",total);
                params.put("total",total);
            }else{
                total +=(listHotels.getNightPrice() * request.getBooking().getPeopleAmount())
                        + (listHotels.getNightPrice() * request.getBooking().getPeopleAmount()) * 0.05;
                params.put("amount",total);
                params.put("total",total);
            }
        }else{
            total +=(listHotels.getNightPrice() * request.getBooking().getPeopleAmount());
            params.put("amount",total);
            params.put("total",total);
        }
        return params;
    }
    public Map<String,Float> calculateTotal2(String typeCard, int dues, float price,int numberPeople){
        Map<String,Float> params = new HashMap<>();
        if(typeCard.equals(ValidateConfiguration.TYPE_CREDIT.getProperty())){
            if(dues<=3){
                total= (float) ((price*numberPeople) * 0.05);
                amount =(price*numberPeople);
                interest = (float) (0.05);
                params.put("total",total);
                params.put("amount",amount);
                params.put("interest",interest);
            }else{
                total= (float) ((price*numberPeople) * 0.10);
                amount =(price*numberPeople);
                interest = (float) (0.10);
                params.put("total",total);
                params.put("amount",amount);
                params.put("interest",interest);
            }
        }
        return params;
    }
    public boolean validatePeople(int numberPeople, int peopleAmount) throws ApiException {
        boolean result=false;
        if(numberPeople==peopleAmount){
            result=true;
        }else{
            throw new ApiException("La cantidad de personas no corresponde con la lista de personas");
        }
        return result;
    }
    public boolean validateFormatEmail(String email) throws ApiException {
        boolean result=false;
        Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        result=regex.matcher(email).matches() ? true : false;
        if(!result){
            throw new ApiException("Email no valido");
        }
        return result;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isValidateDate() {
        return validateDate;
    }

    public void setValidateDate(boolean validateDate) {
        this.validateDate = validateDate;
    }
}
