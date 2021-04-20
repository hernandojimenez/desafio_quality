package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.FlightDTO;
import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.dtos.RequestFlightDTO;
import com.meli.desafioquality.dtos.ResponseFlightDTO;
import com.meli.desafioquality.exception.ApiException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public interface FlightRepository {
    FlightDataDTO getFlights() throws IOException;
    FlightDataDTO getFilterFlightRangeDate(Date dateFrom, Date dateTo, String origin, FlightDataDTO data) throws ParseException, ApiException;
    ResponseFlightDTO createFlightReservation(RequestFlightDTO request, FlightDTO flight) throws ApiException;
}
