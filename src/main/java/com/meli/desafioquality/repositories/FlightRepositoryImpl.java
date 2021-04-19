package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.FlightDataDTO;
import com.meli.desafioquality.util.DataUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class FlightRepositoryImpl implements FlightRepository{

    private DataUtil dataUtil = new DataUtil();

    @Override
    public FlightDataDTO getFlights() throws IOException {
        return dataUtil.loadFlight();
    }
}
