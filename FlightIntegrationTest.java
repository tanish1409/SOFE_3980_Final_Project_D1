package com.ontariotechu.sofe3980U;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.ITest;

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

    ITest
    void testSearchAndDisplayFlightsIntegration() {

        flightSearch.queryDB("New York", "Los Angeles");
        String[][] flights = flightSearch.getFlightOptions();
        // Assert that flights is not null
        assertNotNull(flights);

        // Assert that the flights array has at least one element
        assertTrue(flights.length > 0);

        String[][] newarr1 = roundTrip.getFlights(flightSearch);

        // Assert that newarr1 is not null
        assertNotNull(newarr1);

        // Assert that the newarr1 array has at least one element
        assertTrue(newarr1.length > 0);
    }

    @Test
    void testFlightBookingAndTicketGenerationIntegration() {
        String[][] flights = {
                {"New York", "Costa Rica", "8:00 AM", "5:00 PM", "2024-03-14", "2024-03-14"},
                {"Costa Rica", "Los Angeles", "6:00 PM", "8:00 PM", "2024-03-15", "2024-03-15"}
        };

        // Query the database for flight information
        flightSearch.queryDB("New York", "Costa Rica");
        flightSearch.queryDB("Costa Rica", "Los Angeles");

        // Add flights to the round trip booking
        roundTrip.addFlight("New York Costa Rica 8:00 AM 5:00 PM 2024-03-14 2024-03-14");
        roundTrip.addFlight("Costa Rica Los Angeles 6:00 PM 8:00 PM 2024-03-15 2024-03-15");

        // Generate a ticket based on the selected flights
        String ticket = ticketGenerator.printTicket(flights);

        // Assert that the ticket is not null
        assertNotNull(ticket);

        assertTrue(ticket.contains("New York"));
        assertTrue(ticket.contains("Costa Rica"));
        assertTrue(ticket.contains("8:00 AM"));
        assertTrue(ticket.contains("5:00 PM"));
        assertTrue(ticket.contains("2024-03-14"));
        assertTrue(ticket.contains("2024-03-14"));
    }


    @Test
    void testFlightPlanCreationAndValidationIntegration() {
        String[][] flights = {
                {"Toronto", "New York", "08:00 AM", "10:00 AM", "2024-04-01", "2024-04-01"},
                {"New York", "Toronto", "11:00 AM", "01:00 PM", "2024-04-01", "2024-04-01"}
        };
        assertTrue(roundTrip.validateFlightPlan(flights));
        assertEquals(300, roundTrip.calculateTotalTime(flights));
    }

    @Test
    void testTimeFormatPreferenceIntegration() {
        String[][] flights = {{"Toronto", "New York", "08:00 AM", "10:00 AM", "2024-04-01", "2024-04-01"}};
        ticketGenerator.convertTime(0, 1, flights);
        assertEquals("08:00", flights[0][2]);
        assertEquals("10:00", flights[0][3]);
    }
}
