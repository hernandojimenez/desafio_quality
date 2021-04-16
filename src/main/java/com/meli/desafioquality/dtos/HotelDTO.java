package com.meli.desafioquality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
        private String codeHotel;
        private String name;
        private String city;
        private String roomType;
        private float nightPrice;
        private String dateTo;
        private String dateFrom;
        private boolean reserved;
}
