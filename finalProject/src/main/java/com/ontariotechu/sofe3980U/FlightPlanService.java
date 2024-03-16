package com.ontariotechu.sofe3980U;

import java.util.ArrayList;
import java.util.List;

public class FlightPlanService {

    private List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public int calculateTotalTime() {
        // Dummy implementation for calculating total time
        // This should be replaced with actual logic to calculate the total time
        return 720; // Returning a fixed value for the dummy implementation
    }

    // Method to access the flights for validation
    public List<Flight> getFlights() {
        return flights;
    }
}
