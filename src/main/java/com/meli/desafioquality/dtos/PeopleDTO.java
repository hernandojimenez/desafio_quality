package com.meli.desafioquality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {
    private String dni;
    private String name;
    private String lastName;
    private String birthDate;
    private String mail;
}
