package com.meli.desafioquality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFlightDTO {
    private String userName;
    private float amount;
    private float interest;
    private float total;
    private FlightReservationResponseDTO flightReservation;
    private StatusCodeDTO statusCode;
}
