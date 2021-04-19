package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.repositories.HotelRepository;
import com.meli.desafioquality.util.DataUtil;
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
        String[] avalibleParams = {"dateFrom", "dateTo", "destination"};
        HotelDataDTO dataDTO= new HotelDataDTO();
        if(params.size()>0){
            for(Map.Entry<String,String> entry: params.entrySet()){
                if(entry.getKey().equals(avalibleParams[0])){
                    avalibleParams[0]=entry.getValue();
                }
                if(entry.getKey().equals(avalibleParams[1])){
                    avalibleParams[1]=entry.getValue();
                }
                if(entry.getKey().equals(avalibleParams[2])){
                    avalibleParams[2] = entry.getValue();
                }
            }
            dataDTO = hotelRepository.getFilterHotelRangeDate(avalibleParams[0],avalibleParams[1],avalibleParams[2]);
        }else {
            dataDTO = hotelRepository.getHotels();
        }
        return dataDTO;
    }

    @Override
    public ResponseBookingDTO createBooking(RequestBookingDTO request) throws Exception {
        HotelDataDTO dataDTO = hotelRepository.getHotels();
        ResponseBookingDTO response = new ResponseBookingDTO();
        List<HotelDTO> listFilter = new ArrayList<>();
        listFilter =dataDTO.getHotels().stream().filter(listFilters -> listFilters.getCity().toLowerCase().contains(request.getBooking().getDestination().toLowerCase()))
                .collect(Collectors.toList());
        if(listFilter.size()==0) throw new ApiException("El destino no esta registrado");
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
