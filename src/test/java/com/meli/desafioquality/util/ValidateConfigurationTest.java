package com.meli.desafioquality.util;

public enum ValidateConfigurationTest {
    PATH_HOTEL("src/test/resources/dbHotelstest.json"),
    PATH_FLIGHT("src/test/resources/dbFlightTest.json"),
    PATH_FLIGHT_FILTER("src/test/resources/dbfilterFlightTest.json"),
    PATH_HOTEL_FILTER_DATA("src/test/resources/dbhotelsfilterdatatest.json"),
    PATH_REQUEST("src/test/resources/requestHotelsTest.json"),
    PATH_REQUEST_FLIGHT("src/test/resources/requestFlightTest.json"),
    PATH_RESPONSE("src/test/resources/responseHotelsTest.json"),
    PATH_RESPONSE_FLIGHT("src/test/resources/responseFlightTest.json");

    private String property;

    ValidateConfigurationTest(String property) {
        this.property=property;
    }

    public String getProperty() {
        return property;
    }

}