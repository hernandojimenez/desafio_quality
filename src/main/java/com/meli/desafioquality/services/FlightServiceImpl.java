package com.meli.desafioquality.services;

import com.meli.desafioquality.dtos.*;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.repositories.FlightRepository;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    private DataUtil dataUtil = new DataUtil();

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDataDTO getFlights(Map<String, String> params) throws IOException, ParseException, ApiException {
        FlightDataDTO dataFlight = dataUtil.loadFlight();
        if (params.size() > 0) {
            Date dateFrom = dataUtil.dateFormat(params.get("dateFrom"));
            if (dateFrom == null) throw new ApiException(ValidateConfiguration.DATE_FORMAT.getProperty());
            Date dateTo = dataUtil.dateFormat(params.get("dateTo"));
            if (dateTo == null) throw new ApiException(ValidateConfiguration.DATE_FORMAT.getProperty());
            dataFlight = flightRepository.getFilterFlightRangeDate(dateFrom, dateTo, params.get("origin"), dataFlight);
            List<FlightDTO> filterData = filterByOrigin(params.get("origin"), params.get("destination"), dataFlight.getFlight());
            if (filterData.size() == 0)
                throw new ApiException(ValidateConfiguration.DESTINATION_NOT_FOUND.getProperty());
            dataFlight.setFlight(filterData);
        }

        return dataFlight;
    }

    @Override
    public ResponseFlightDTO createFlightReservation(RequestFlightDTO request) throws IOException, ApiException, ParseException {
        FlightDataDTO dataFlight = dataUtil.loadFlight();
        ResponseFlightDTO response = new ResponseFlightDTO();
        List<FlightDTO> filterData = filterByOrigin(request.getFlightReservation().getOrigin(), request.getFlightReservation().getDestination(), dataFlight.getFlight());
        if (filterData.size() == 0) throw new ApiException(ValidateConfiguration.DESTINATION_NOT_FOUND.getProperty());
        boolean validEmail = dataUtil.validateFormatEmail(request.getUserName(), request.getFlightReservation().getPeople());
        Date dateFrom = dataUtil.dateFormat(request.getFlightReservation().getDateFrom());
        if (dateFrom == null) throw new ApiException(ValidateConfiguration.DATE_FORMAT.getProperty());
        Date dateTo = dataUtil.dateFormat(request.getFlightReservation().getDateTo());
        if (dateTo == null) throw new ApiException(ValidateConfiguration.DATE_FORMAT.getProperty());
        for (FlightDTO flightDTO : dataFlight.getFlight()) {
            if (dataUtil.ischeckBetween(flightDTO.getDateFrom(), flightDTO.getDateTo(), dateFrom, dateTo)) {
                response = flightRepository.createFlightReservation(request, flightDTO);
            }
        }
        return response;
    }

    public List<FlightDTO> filterByOrigin(String origin, String destination, List<FlightDTO> listFlight) {
        List<FlightDTO> listFilter = new ArrayList<>();
        listFilter = listFlight.stream().filter(listFlights -> listFlights.getOrigin().toLowerCase().contains(origin.toLowerCase())
                && listFlights.getDestination().toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
        return listFilter;
    }
}
