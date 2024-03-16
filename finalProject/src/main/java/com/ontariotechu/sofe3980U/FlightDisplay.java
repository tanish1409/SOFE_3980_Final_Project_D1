// Mock Implementation for integration testing
package com.ontariotechu.sofe3980U;

import java.util.List;

public class FlightDisplay {

    public String displayAllFlights(List<Flight> flights) {
        // Dummy implementation
        StringBuilder displayMessage = new StringBuilder("Displaying flights:\n");
        for (Flight flight : flights) {
            displayMessage.append(flight.toString()).append("\n");
        }
        return displayMessage.toString();
    }
}
