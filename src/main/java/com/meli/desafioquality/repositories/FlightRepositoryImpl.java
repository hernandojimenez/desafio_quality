package com.meli.desafioquality.repositories;

import com.meli.desafioquality.dtos.*;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.util.DataUtil;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private DataUtil dataUtil = new DataUtil();

    List<FlightDTO> listReservation = new ArrayList<>();

    private float amount = 0;
    private float interest = 0;
    private float total = 0;

    @Override
    public FlightDataDTO getFlights() throws IOException {
        return dataUtil.loadFlight();
    }

    @Override
    public FlightDataDTO getFilterFlightRangeDate(Date dateFrom, Date dateTo, String origin, FlightDataDTO data) throws ParseException, ApiException {
        List<FlightDTO> listFlight = new ArrayList<>();
        FlightDataDTO flightDataDTO = new FlightDataDTO();
        for (FlightDTO list : data.getFlight()) {
            if (dataUtil.ischeckBetween(list.getDateFrom(), list.getDateTo(), dateFrom, dateTo)) {
                listFlight.add(list);
            }
        }
        flightDataDTO.setFlight(listFlight);
        return flightDataDTO;
    }

    @Override
    public ResponseFlightDTO createFlightReservation(RequestFlightDTO request, FlightDTO flight) throws ApiException {
        Map<String, Float> params = new HashMap<>();
        if (flight.getFlightNumber().contains(request.getFlightReservation().getFlightNumber())) {
            for (int i = 0; i < listReservation.size(); i++) {
                if (!listReservation.get(i).isReserved()) {
                    flight.setReserved(true);
                    params = dataUtil.calculateTotal(request.getFlightReservation().getPaymentMethod().getType(),
                            request.getFlightReservation().getPaymentMethod().getDues(), flight.getPeoplePrice(), request.getFlightReservation().getPeople().size());
                    if (params.size() > 0) {
                        total = params.get("total");
                        amount = params.get("amount");
                        interest = params.get("interest");
                    }
                } else {
                    throw new ApiException(ValidateConfiguration.MESSAGE_ERROR_FLIGHT.getProperty());
                }
            }
        }
        listReservation.add(flight);
        FlightReservationResponseDTO flightReservationResponseDTO = new FlightReservationResponseDTO(request.getFlightReservation().getDateFrom(), request.getFlightReservation().getDateTo(), request.getFlightReservation().getOrigin(),
                request.getFlightReservation().getDestination(), request.getFlightReservation().getFlightNumber(), request.getFlightReservation().getSeats(),
                request.getFlightReservation().getSeatType(), request.getFlightReservation().getPeople());
        ResponseFlightDTO response = new ResponseFlightDTO(request.getUserName(), amount, interest, total, flightReservationResponseDTO, new StatusCodeDTO(HttpStatus.OK.value(), ValidateConfiguration.BOOKING_OK.getProperty()));
        return response;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
