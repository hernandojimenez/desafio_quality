package com.meli.desafioquality.util;


public enum ValidateConfiguration {
    PATH_HOTEL("src/main/resources/dbHotels.json"),
    PATH_HOTEL_FILTER_DATA("src/main/resources/dbhotelsfilterdate.json"),
    PATH_REQUEST("src/main/resources/requestbooking.json"),
    STATUS_ERROR("RESPONSE SERVICE STATUS [%s]"),
    MESSAGE_ERROR("THE ROOM IS NOT AVAILABLE"),
    TYPE_CREDIT("CREDIT"),
    TYPE_DEBIT("CREDIT"),
    BOOKING_OK("El proceso termino satisfactoriamente"),
    HIGHER_DATE("La fecha de salida debe ser mayor a la de entrada"),
    LESS_DATE("La fecha de entrada debe ser menor a la de salida"),
    DATE_FORMAT("Formato de fecha debe ser dd/mm/aaaa");

    private String property;

    ValidateConfiguration(String property) {
        this.property=property;
    }

    public String getProperty() {
        return property;
    }
}
