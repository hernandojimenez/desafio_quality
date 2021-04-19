package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.FlightDataDTO;

import java.io.IOException;

public interface FlightRepository {
    public FlightDataDTO getFlights() throws IOException;
}
