package com.meli.desafioquality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookingDTO {
    private String userName;
    private BookingDTO booking;
}
