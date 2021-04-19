package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.FlightDataDTO;

import java.io.IOException;

public interface FlightService {
    FlightDataDTO getFlights() throws IOException;
}
