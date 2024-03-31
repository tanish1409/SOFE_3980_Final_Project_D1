package com.ontariotechu.sofe3980U;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
//IS the flight suppsed to have 5 or 6 arguments??????
public class FlightIntegrationTest {

    private Flight_search flightSearch;
    private One_Way oneWay;
    private Round_Trip roundTrip;
    private Ticket_Generator ticketGenerator;

    @BeforeEach
    void setUp() {
        flightSearch = new Flight_search();
        oneWay = new One_Way();
        roundTrip = new Round_Trip();
        ticketGenerator = new Ticket_Generator();
    }

    @Test
    void testSearchAndDisplayFlightsIntegration() {
        String[] flightCriteria = {"Toronto", "New York", "2024-04-01", "08:00 AM", "10:00 AM"};
        assertTrue(flightSearch.inputValidation(flightCriteria));

        List<Flight> flights = flightSearch.getFlights("Toronto", "New York", "2024-04-01", "08:00 AM",  "10:00 AM");
        assertNotNull(flights);
        assertFalse(flights.isEmpty());

        flightSearch.displayAllFlights(flights);
        // There is no exsistence of a method displayAllFlights in the UMLLLLLL
    }

    @Test
    void testFlightBookingAndTicketGenerationIntegration() {
        String[] flightCriteria = {"Toronto", "New York", "2024-04-01", "08:00 AM", "2024-04-01", "10:00 AM"};
        assertTrue(flightSearch.inputValidation(flightCriteria));

        String ticket = ticketGenerator.printTicket("Toronto", "New York", "08:00 AM", "10:00 AM", true);
        assertNotNull(ticket);
        // Verify that the ticket is generated correctly or check the output in the console
    }

    @Test
    void testFlightPlanCreationAndValidationIntegration() {
        String[][] flights = {
                {"Toronto", "New York", "08:00 AM", "10:00 AM", "2024-04-01", "2024-04-01"},
                {"New York", "Toronto", "11:00 AM", "01:00 PM", "2024-04-01", "2024-04-01"}
        };
        assertTrue(oneWay.validateFlightPlan(flights));
        assertEquals(240, oneWay.calculateTotalTime(flights));
    }


    @Test
    void testTimeFormatPreferenceIntegration() {
        String[][] flights = {{"Toronto", "New York", "08:00 AM", "10:00 AM", "2024-04-01", "2024-04-01"}};
        ticketGenerator.convertTime(0, 1, flights);
        assertEquals("08:00 AM", flights[0][2]);
        assertEquals("10:00 AM", flights[0][3]);
    }
}
