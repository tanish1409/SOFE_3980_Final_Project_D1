// Mock Implementation for integration testing

package com.ontariotechu.sofe3980U;

import java.util.List;

public class FlightService {

    private Flight_search flightSearch;
    private FlightDisplay flightDisplay;

    public FlightService(Flight_search flightSearch, FlightDisplay flightDisplay) {
        this.flightSearch = flightSearch;
        this.flightDisplay = flightDisplay;
    }

    public List<Flight> getFlights(String from, String to, String date, String startTime, String endTime) {
        return flightSearch.getFlights(from, to, date, startTime, endTime);
    }

    public String displayAllFlights(List<Flight> flights) {
        return flightDisplay.displayAllFlights(flights);
    }
}
