package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.util.DataUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HotelRepositoryImpl implements HotelRepository{

    private DataUtil dataUtil = new DataUtil();


    @Override
    public List<HotelDTO> getHotels() throws IOException {
        return dataUtil.loadHotels();
    }

    @Override
    public List<HotelDTO> getFilterHotelRangeDate(String dateFrom, String dateTo, String destination) throws IOException {
        List<HotelDTO> listFilter = new ArrayList<>();
        List<HotelDTO> listHotels = dataUtil.loadHotels();
        for(HotelDTO list: listHotels){
            if(dataUtil.ischeckBetween(dateFrom,dateTo,list)){
                listFilter.add(list);
            }
        }
        listFilter =listFilter.stream().filter(listFilters -> listFilters.getCity().toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
        return listFilter;
    }
}
