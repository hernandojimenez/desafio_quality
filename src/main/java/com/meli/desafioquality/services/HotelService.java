package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.HotelDTO;
import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
import com.meli.desafioquality.exception.ApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HotelService {
    HotelDataDTO getHotels(Map<String,String> params) throws Exception;
    ResponseBookingDTO createBooking(RequestBookingDTO request) throws Exception;
}
