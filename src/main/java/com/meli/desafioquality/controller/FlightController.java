package com.meli.desafioquality.controller;

import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<FlightDataDTO> getFlights() throws IOException {
        return new ResponseEntity(flightService.getFlights(), HttpStatus.OK);
    }
}
