package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.HotelDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface HotelService {
    List<HotelDTO> getHotels(HashMap<String,String> params) throws IOException;
}
