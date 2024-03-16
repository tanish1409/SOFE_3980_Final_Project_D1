package com.ontariotechu.sofe3980U;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/inputValidation.csv", numLinesToSkip = 1)
    void inputValidationTests(String startLoc, String endLoc, String startDate, String endDate, String startTime, String endTime, boolean expected) {
        Flight_search flight = new Flight_search();
        boolean actualValue = flight.inputValidation(startLoc, endLoc, startDate, endDate, startTime, endTime);
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/timeFormat24.csv", numLinesToSkip = 1)
    void timeFormat24Tests(String startLoc, String endLoc, String startTime, String endTime, String startDate, String endDate,
                           String expectedStartLoc, String expectedEndLoc, String expectedStartTime, String expectedEndTime, String expectedStartDate, String expectedEndDate) {
        Flight_search flight = new Flight_search();

        // Construct the input array from the CSV row
        String[][] input = {{startLoc, endLoc, startTime, endTime, startDate, endDate}};

        // Call the method under test
        String[][] actualOutput = flight.timeFormat24(input);

        // Construct the expected output array from the CSV row
        String[][] expectedOutput = {{expectedStartLoc, expectedEndLoc, expectedStartTime, expectedEndTime, expectedStartDate, expectedEndDate}};

        // Assert that the actual output matches the expected output
        assertArrayEquals(expectedOutput, actualOutput, "The time format conversion did not produce the expected result.");
    }

    @Test
    public void timeFormat24ErrorTest() {
        Flight_search flight = new Flight_search();

        // The input that is expected to cause an error
        String[][] input = {{"London", "Paris", "09:30", "6:30 PM", "2024-03-14", "2024-03-15"}};

        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest1() {
        Flight_search flight = new Flight_search();

        // The input that is expected to cause an error
        String[][] input = {{"London", "Paris", "09:30 AM", "6:30", "2024-03-14", "2024-03-15"}};

        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest2() {
        Flight_search flight = new Flight_search();

        // The input that is expected to cause an error
        String[][] input = {{"New York", "Los Angeles", "-08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"}};

        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest3() {
        Flight_search flight = new Flight_search();

        // The input that is expected to cause an error
        String[][] input = {{"New York", "Los Angeles", "08:00 AM", "-05:00 PM", "2024-03-14", "2024-03-15"}};

        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/validateFlightPlan.csv", numLinesToSkip = 1)
    void validateFlightPlanTest(String leg1StartLocation, String leg1EndLocation, String leg1StartTime, String leg1EndTime,
                                String leg1StartDate, String leg1EndDate, String leg2StartLocation, String leg2EndLocation,
                                String leg2StartTime, String leg2EndTime, String leg2StartDate, String leg2EndDate,
                                boolean expectedResult) {
        One_Way flightPlanner = new One_Way();

        // Assuming your ValidateFlightPlan method takes two arrays for each leg of the flight
        String[][] plan = {{leg1StartLocation, leg1EndLocation, leg1StartTime, leg1EndTime, leg1StartDate,
                leg1EndDate}, {leg2StartLocation, leg2EndLocation, leg2StartTime, leg2EndTime, leg2StartDate, leg2EndDate}};

        // Call the method under test
        boolean actualResult = flightPlanner.validateFlightPlan(plan);

        // Assert that the actual result matches the expected result
        assertEquals(expectedResult, actualResult);
    }

}
