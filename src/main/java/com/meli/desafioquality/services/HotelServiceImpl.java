package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.repositories.HotelRepository;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{

    private HotelRepository hotelRepository;

    private DataUtil dataUtil = new DataUtil();

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDataDTO getHotels(Map<String,String> params) throws Exception {
        HotelDataDTO dataDTO= hotelRepository.getHotels();
        if(params.size()>0){
            Date dateFrom=dataUtil.dateFormat(params.get("dateFrom"));
            Date dateTo=dataUtil.dateFormat(params.get("dateTo"));
            List<HotelDTO> listFilter =filterDestination(params.get("destination"),dataDTO.getHotels());
            if(listFilter.size()==0) throw new ApiException(ValidateConfiguration.DESTINATION_NOT_FOUND.getProperty());
            dataDTO = hotelRepository.getFilterHotelRangeDate(dateFrom,dateTo,params.get("destination"));
        }
        return dataDTO;
    }
    public List<HotelDTO> filterDestination(String destination, List<HotelDTO> listHotels){
        List<HotelDTO> listFilter = new ArrayList<>();
        listFilter =listHotels.stream().filter(listFilters -> listFilters.getCity().toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
        return listFilter;
    }
    public boolean validateNumberPeople(int numberPeople, String roomType) throws ApiException {
        boolean result=false;
        switch (roomType) {
            case "Single":
                if (numberPeople == 1)
                    result = true;
                else
                    throw new ApiException(ValidateConfiguration.ROOM_TYPE_NOT_VALID.getProperty());
                break;
            case "Doble":
                if (numberPeople == 2)
                    result = true;
                else
                    throw new ApiException(ValidateConfiguration.ROOM_TYPE_NOT_VALID.getProperty());
                break;
            case "Triple":
                if (numberPeople >= 3)
                    result = true;
                else
                    throw new ApiException(ValidateConfiguration.ROOM_TYPE_NOT_VALID.getProperty());
                break;
            default:
                throw new ApiException(ValidateConfiguration.ROOM_TYPE_NOT_VALID.getProperty());
        }
        return result;
    }

    @Override
    public ResponseBookingDTO createBooking(RequestBookingDTO request) throws Exception {
        HotelDataDTO dataDTO = hotelRepository.getHotels();
        ResponseBookingDTO response = new ResponseBookingDTO();
        List<HotelDTO> listFilter =filterDestination(request.getBooking().getDestination(),dataDTO.getHotels());
        if(listFilter.size()==0) throw new ApiException(ValidateConfiguration.DESTINATION_NOT_FOUND.getProperty());
        boolean validateNumberPerson= validateNumberPeople(request.getBooking().getPeople().size(),request.getBooking().getRoomType());
        boolean validEmail = dataUtil.validateFormatEmail(request.getUserName());
        Date dateFrom= dataUtil.dateFormat(request.getBooking().getDateFrom());
        Date dateTo= dataUtil.dateFormat(request.getBooking().getDateTo());
        for(HotelDTO hotelDTO: dataDTO.getHotels()){
            if(dataUtil.ischeckBetween(dateFrom,dateTo,hotelDTO)){
                response =hotelRepository.createBooking(request,hotelDTO);
            }
        }
        return response;
    }
}
