package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FlightServiceImpl implements FlightService{

    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDataDTO getFlights() throws IOException {
        return flightRepository.getFlights();
    }
}
