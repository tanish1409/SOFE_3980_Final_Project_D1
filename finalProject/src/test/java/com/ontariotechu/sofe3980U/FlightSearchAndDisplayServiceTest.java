package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightSearchAndDisplayServiceTest {

    @Mock
    private Flight_search flightSearchMock;

    @Mock
    private FlightDisplay flightDisplayMock;

    @InjectMocks
    private FlightService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchAndDisplayFlightsIntegration() {
        List<Flight> expectedFlights = Arrays.asList(new Flight("New York", "Los Angeles", "2024-04-15", "08:00", "17:00"));
        when(flightSearchMock.getFlights(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(expectedFlights);
        String expectedDisplayMessage = "Displaying flights from New York to Los Angeles on 2024-04-15.";
        when(flightDisplayMock.displayAllFlights(expectedFlights)).thenReturn(expectedDisplayMessage);

        List<Flight> actualFlights = service.getFlights("New York", "Los Angeles", "2024-04-15", "08:00", "17:00");
        String actualDisplayMessage = service.displayAllFlights(actualFlights);

        boolean flightsMatch = expectedFlights.equals(actualFlights);
        boolean messagesMatch = expectedDisplayMessage.equals(actualDisplayMessage);

        assertTrue(flightsMatch && messagesMatch);
    }
}
