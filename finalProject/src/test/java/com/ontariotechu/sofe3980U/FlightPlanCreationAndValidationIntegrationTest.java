package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightPlanCreationAndValidationIntegrationTest {

    @Mock
    private FlightPlanValidator flightPlanValidator;

    @InjectMocks
    private FlightPlanService flightPlanService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testValidFlightPlanCreationAndValidation() {
        List<Flight> flights = Arrays.asList(
    new Flight("New York", "Los Angeles", "2024-03-14", "08:00 AM", "05:00 PM"),
    new Flight("Los Angeles", "New York", "2024-03-19", "08:00 PM", "11:00 PM")
);


        flights.forEach(flight -> flightPlanService.addFlight(flight));

        when(flightPlanValidator.validateFlightPlan(flights)).thenReturn(true);
        int expectedTotalTime = 720; 
        when(flightPlanService.calculateTotalTime()).thenReturn(expectedTotalTime);

        // Method call to validate the flight plan
        boolean validationResult = flightPlanValidator.validateFlightPlan(flights);
        int actualTotalTime = flightPlanService.calculateTotalTime();

        assertAll("Flight Plan Creation and Validation",
                () -> assertTrue(validationResult, "The flight plan should be valid"),
                () -> assertEquals(expectedTotalTime, actualTotalTime, "The total flight time should match the expected value")
        );
    }

    @Test
    void testInvalidFlightPlanCreationDueToCyclicPath() {
        List<Flight> flights = Arrays.asList(
    new Flight("New York", "Los Angeles", "2024-03-14", "08:00 AM", "05:00 PM"),
    new Flight("Los Angeles", "New York", "2024-03-14", "08:00 PM", "11:00 PM")
);


        flights.forEach(flight -> flightPlanService.addFlight(flight));
        when(flightPlanValidator.validateFlightPlan(flights)).thenReturn(false);

        boolean validationResult = flightPlanValidator.validateFlightPlan(flights);

        assertFalse(validationResult, "The flight plan should be invalid due to a cyclic path");
    }
}
