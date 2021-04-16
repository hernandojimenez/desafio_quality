package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelServiceImpl implements HotelService{

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> getHotels(HashMap<String,String> params) throws IOException {
        String dateFrom = "";
        String dateTo = "";
        String destination = "";
        List<HotelDTO> listHotels= new ArrayList<>();
        if(params!=null){
            for(Map.Entry<String,String> entry: params.entrySet()){
                if(entry.getKey().equals("dateFrom")){
                    dateFrom=entry.getValue();
                }
                if(entry.getKey().equals("dateTo")){
                    dateTo=entry.getValue();
                }
                if(entry.getKey().equals("destination")){
                    destination = entry.getValue();
                }
            }
            listHotels = hotelRepository.getFilterHotelRangeDate(dateFrom,dateTo,destination);
        }else {
            listHotels = hotelRepository.getHotels();
        }
        return listHotels;
    }
}
