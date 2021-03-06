package com.meli.desafioquality.controller;

import com.meli.desafioquality.dtos.HotelDataDTO;
import com.meli.desafioquality.dtos.RequestBookingDTO;
import com.meli.desafioquality.dtos.ResponseBookingDTO;
import com.meli.desafioquality.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<HotelDataDTO> getHotels(@RequestParam(required = false) Map<String, String> params) throws Exception {
        return new ResponseEntity(hotelService.getHotels(params), HttpStatus.OK);
    }

    @PostMapping("booking")
    public ResponseEntity<ResponseBookingDTO> createBooking(@RequestBody RequestBookingDTO request) throws Exception {
        return new ResponseEntity(hotelService.createBooking(request), HttpStatus.OK);
    }
}
