package com.meli.desafioquality.util;


public enum ValidateConfiguration {
    PATH_HOTEL("src/main/resources/dbHotels.json"),
    PATH_HOTEL_FILTER_DATE_TEST("src/main/resources/dbhotelsfilterdate.json");

    private String property;

    ValidateConfiguration(String property) {
        this.property=property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
