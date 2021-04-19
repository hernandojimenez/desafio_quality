package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.*;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class HotelRepositoryImpl implements HotelRepository{

    private DataUtil dataUtil = new DataUtil();

    List<HotelDTO> list = new ArrayList<>();

    private float amount=0;
    private float interest=0;
    private float total=0;

    @Override
    public HotelDataDTO getHotels() throws IOException {
        return dataUtil.loadHotels();
    }

    @Override
    public HotelDataDTO getFilterHotelRangeDate(String dateEntry, String dateFinal, String destination) throws Exception {
        List<HotelDTO> listFilter = new ArrayList<>();
        HotelDataDTO hotels = dataUtil.loadHotels();
        HotelDataDTO data = new HotelDataDTO();
        Date dateFrom= dataUtil.dateFormat(dateEntry);
        Date dateTo= dataUtil.dateFormat(dateFinal);
        for(HotelDTO list: hotels.getHotels()){
            if(dataUtil.ischeckBetween(dateFrom,dateTo,list)){
                listFilter.add(list);
            }
        }
        listFilter =listFilter.stream().filter(listFilters -> listFilters.getCity().toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
        data.setHotels(listFilter);
        return data;
    }

    @Override
    public ResponseBookingDTO createBooking(RequestBookingDTO request,HotelDTO listData) throws IOException, ApiException {
        Map<String,Float> params = new HashMap<>();
        if(listData.getCodeHotel().contains(request.getBooking().getHotelCode())){
            if(dataUtil.validatePeople(request.getBooking().getPeople().size(),request.getBooking().getPeopleAmount())){
                for(int i=0; i<list.size(); i++){
                    if(!list.get(i).isReserved()){
                        listData.setReserved(true);
                        params = dataUtil.calculateTotal2(request.getBooking().getPaymentMethod().getType(),
                                request.getBooking().getPaymentMethod().getDues(),listData.getNightPrice(),request.getBooking().getPeople().size());
                        if(params.size()>0){
                            total = params.get("total");
                            amount = params.get("amount");
                            interest = params.get("interest");
                        }
                    }else {
                        throw new ApiException(ValidateConfiguration.MESSAGE_ERROR.getProperty());
                    }
                }
            }
        }
        list.add(listData);
        ResponseBookingDTO response = new ResponseBookingDTO(request.getUserName(),amount,interest,total, request.getBooking(),
                new StatusCodeDTO(HttpStatus.OK.value(),ValidateConfiguration.BOOKING_OK.getProperty()));
        return response;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
