package com.meli.desafioquality.util;


public enum ValidateConfiguration {
    PATH_HOTEL("src/main/resources/dbHotels.json"),
    PATH_FLIGHT("src/main/resources/dbFlight.json"),
    PATH_HOTEL_FILTER_DATA("src/main/resources/dbhotelsfilterdate.json"),
    PATH_REQUEST("src/main/resources/requestbooking.json"),
    STATUS_ERROR("RESPONSE SERVICE STATUS [%s]"),
    MESSAGE_ERROR("THE ROOM IS NOT AVAILABLE"),
    TYPE_CREDIT("It has been\n" +
            "entered an amount of installments\n" +
            "different from 1."),
    BOOKING_OK("The process ended satisfactorily"),
    HIGHER_DATE("The departure date must be greater than the arrival date"),
    LESS_DATE("The arrival date must be less than the departure date"),
    DATE_FORMAT("Date format must be dd/mm/aaaa"),
    DESTINATION_NOT_FOUND("The chosen destination does not exist"),
    ROOM_TYPE_NOT_VALID("The selected room type does not\n" +
            "match the number of people\n" +
            "they will stay in it."),
    EMAIL_NOT_VALID("Please enter a valid email");

    private String property;

    ValidateConfiguration(String property) {
        this.property=property;
    }

    public String getProperty() {
        return property;
    }
}
