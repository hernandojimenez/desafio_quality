package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.dtos.RequestFlightDTO;
import com.meli.desafioquality.dtos.ResponseFlightDTO;
import com.meli.desafioquality.exception.ApiException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface FlightService {
    FlightDataDTO getFlights(Map<String,String> params) throws IOException, ParseException, ApiException;
    ResponseFlightDTO createFlightReservation(RequestFlightDTO request) throws IOException, ApiException, ParseException;
}
