package com.meli.desafioquality.controller;

import com.meli.desafioquality.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public ResponseEntity getHotels(@RequestParam(required = false) HashMap<String, String> params) throws IOException {
        return new ResponseEntity(hotelService.getHotels(params), HttpStatus.OK);
    }
}
