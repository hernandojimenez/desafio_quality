package com.meli.desafioquality.util;

public enum ValidateConfigurationTest {
    PATH_HOTEL("src/test/resources/dbHotelstest.json"),
    PATH_HOTEL_FILTER_DATA("src/test/resources/dbhotelsfilterdatatest.json");

    private String property;

    ValidateConfigurationTest(String property) {
        this.property=property;
    }

    public String getProperty() {
        return property;
    }

}