package com.meli.desafioquality.controller;

import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.dtos.RequestFlightDTO;
import com.meli.desafioquality.dtos.ResponseFlightDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<FlightDataDTO> getFlights(@RequestParam(required = false) Map<String, String> params) throws IOException, ParseException, ApiException {
        return new ResponseEntity(flightService.getFlights(params), HttpStatus.OK);
    }

    @PostMapping("/flight-reservation")
    public ResponseEntity<ResponseFlightDTO> createFlightReservation(@RequestBody RequestFlightDTO request) throws ParseException, ApiException, IOException {
        return new ResponseEntity(flightService.createFlightReservation(request), HttpStatus.OK);
    }
}
