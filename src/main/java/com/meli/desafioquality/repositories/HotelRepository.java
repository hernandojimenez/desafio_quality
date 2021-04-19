package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
import com.meli.desafioquality.exception.ApiException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface HotelRepository {

    HotelDataDTO getHotels() throws IOException;
    HotelDataDTO getFilterHotelRangeDate(Date dateFrom, Date dateTo, String destination) throws Exception;
    ResponseBookingDTO createBooking(RequestBookingDTO request, HotelDTO listData) throws IOException, ApiException;

}
