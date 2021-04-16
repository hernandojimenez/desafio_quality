package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.HotelDTO;

import java.io.IOException;
import java.util.List;

public interface HotelRepository {

    List<HotelDTO> getHotels() throws IOException;
    List<HotelDTO> getFilterHotelRangeDate(String dateFrom, String dateTo, String destination) throws IOException;
}
