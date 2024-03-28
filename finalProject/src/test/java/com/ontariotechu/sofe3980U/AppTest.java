package com.ontariotechu.sofe3980U;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



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
        Assertions.assertEquals(expected, actualValue);
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
        String[][] input = {{"London", "Paris", "09:30", "6:30 PM", "2024-03-14", "2024-03-15"}};
        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest1() {
        Flight_search flight = new Flight_search();
        String[][] input = {{"London", "Paris", "09:30 AM", "6:30", "2024-03-14", "2024-03-15"}};
        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest2() {
        Flight_search flight = new Flight_search();
        String[][] input = {{"New York", "Los Angeles", "-08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"}};
        assertThrows(IllegalArgumentException.class, () -> flight.timeFormat24(input), "Expected timeFormat24 to throw an exception due to incorrect time format.");
    }

    @Test
    public void timeFormat24ErrorTest3() {
        Flight_search flight = new Flight_search();
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
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateTotalTime.csv", numLinesToSkip = 1)
    void calculateTotalTimeTest(String startLocation1, String endLocation1, String startTime1, String endTime1,
                                String startDate1, String endDate1, String startLocation2, String endLocation2,
                                String startTime2, String endTime2, String startDate2, String endDate2,
                                int expectedTotalTime) {
        One_Way flightPlanner = new One_Way();

        // Construct the input from the CSV row
        String[][] flightLegs = {
                {startLocation1, endLocation1, startTime1, endTime1, startDate1, endDate1},
                {startLocation2, endLocation2, startTime2, endTime2, startDate2, endDate2}
        };

        // Call the method under test
        int actualTotalTime = flightPlanner.calculateTotalTime(flightLegs);

        // Assert that the actual total time matches the expected total time
        Assertions.assertEquals(expectedTotalTime, actualTotalTime);
    }

    @Test
    public void calculateTotalTimeShouldThrowExceptionForOverlappingFlights1() {
        One_Way oneWay = new One_Way();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Paris", "09:00 AM", "12:00 PM", "2024-03-18", "2024-03-18"}
        };

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 1.");
    }

    @Test
    public void calculateTotalTimeShouldThrowExceptionForOverlappingFlights2() {
        One_Way oneWay = new One_Way();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Paris", "05:10 PM", "9:00 PM", "2024-03-15", "2024-03-15"}
        };

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 2.");
    }

    @Test
    public void calculateTotalTimeShouldThrowExceptionForOverlappingFlights3() {
        One_Way oneWay = new One_Way();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "New York", "08:00 PM", "11:00 PM", "2024-03-15", "2024-03-15"}
        };

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 3.");
    }

    @Test
    public void calculateTotalTimeShouldThrowExceptionForOverlappingFlights4() {
        One_Way oneWay = new One_Way();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Idaho", "04:00 PM", "07:00 PM", "2024-03-15", "2024-03-15"}
        };

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 4.");
    }

    @Test
    public void calculateTotalTimeShouldThrowExceptionForOverlappingFlights5() {
        One_Way oneWay = new One_Way();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Toronto", "Idaho", "08:00 PM", "11:00 PM", "2024-03-15", "2024-03-15"}
        };

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 5.");
    }

    @Test
    public void calculateTotalTimeNull() {
        One_Way oneWay = new One_Way();
        String[][] input = {{}};

        assertThrows(IllegalArgumentException.class, () -> oneWay.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 5.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/convertTime.csv", numLinesToSkip = 1)
    void convertTimeTests(int hr12, int hr24,
                          String leg1StartLocation, String leg1EndLocation, String leg1StartTime, String leg1EndTime,
                          String leg1StartDate, String leg1EndDate, String leg2StartLocation, String leg2EndLocation,
                          String leg2StartTime, String leg2EndTime, String leg2StartDate, String leg2EndDate,
                          String expectedLeg1StartLocation, String expectedLeg1EndLocation, String expectedLeg1StartTime,
                          String expectedLeg1EndTime, String expectedLeg1StartDate, String expectedLeg1EndDate,
                          String expectedLeg2StartLocation, String expectedLeg2EndLocation, String expectedLeg2StartTime,
                          String expectedLeg2EndTime, String expectedLeg2StartDate, String expectedLeg2EndDate) {
        Ticket_Generator timeConverter = new Ticket_Generator();

        String[][] input = {
                {leg1StartLocation, leg1EndLocation, leg1StartTime, leg1EndTime, leg1StartDate, leg1EndDate},
                {leg2StartLocation, leg2EndLocation, leg2StartTime, leg2EndTime, leg2StartDate, leg2EndDate}
        };
        String[][] expected = {
                {expectedLeg1StartLocation, expectedLeg1EndLocation, expectedLeg1StartTime, expectedLeg1EndTime, expectedLeg1StartDate, expectedLeg1EndDate},
                {expectedLeg2StartLocation, expectedLeg2EndLocation, expectedLeg2StartTime, expectedLeg2EndTime, expectedLeg2StartDate, expectedLeg2EndDate}
        };

        String[][] actual = timeConverter.convertTime(hr12, hr24, input);

        Assertions.assertArrayEquals(expected, actual, "The conversion did not produce the expected result.");
    }

    @Test
    public void convertTimeInvalidArg1() {
        Ticket_Generator timeConverter = new Ticket_Generator();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "New York", "08:00 PM", "11:00 PM", "2024-03-19", "2024-03-19"}
        };
        int hr12 = 0;
        int hr24 = 0;

        assertThrows(IllegalArgumentException.class, () -> timeConverter.convertTime(hr12, hr24, input),
                "convertTime should throw IllegalArgumentException for invalid arguments.");
    }

    @Test
    public void convertTimeInvalidArg2() {
        Ticket_Generator timeConverter = new Ticket_Generator();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "New York", "08:00 PM", "11:00 PM", "2024-03-19", "2024-03-19"}
        };
        int hr12 = 1;
        int hr24 = 1;

        assertThrows(IllegalArgumentException.class, () -> timeConverter.convertTime(hr12, hr24, input),
                "convertTime should throw IllegalArgumentException for invalid arguments.");
    }

    @Test
    public void convertTimeInvalidArg3() {
        Ticket_Generator timeConverter = new Ticket_Generator();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "New York", "08:00 PM", "11:00 PM", "2024-03-19", "2024-03-19"}
        };
        int hr12 = -1;
        int hr24 = -1;

        assertThrows(IllegalArgumentException.class, () -> timeConverter.convertTime(hr12, hr24, input),
                "convertTime should throw IllegalArgumentException for invalid arguments.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/validateFlightPlanRound.csv", numLinesToSkip = 1)
    void validateFlightPlanRoundTripTests(String leg1StartLocation, String leg1EndLocation, String leg1StartTime, String leg1EndTime,
                                 String leg1StartDate, String leg1EndDate, String leg2StartLocation, String leg2EndLocation,
                                 String leg2StartTime, String leg2EndTime, String leg2StartDate, String leg2EndDate,
                                 String leg3StartLocation, String leg3EndLocation, String leg3StartTime, String leg3EndTime,
                                 String leg3StartDate, String leg3EndDate, boolean expectedResult) {
        Round_Trip roundTrip = new Round_Trip();

        // Construct the input directly from CSV row values
        String[][] flightPlan = {
                {leg1StartLocation, leg1EndLocation, leg1StartTime, leg1EndTime, leg1StartDate, leg1EndDate},
                {leg2StartLocation, leg2EndLocation, leg2StartTime, leg2EndTime, leg2StartDate, leg2EndDate}
        };

        // Call the method under test
        boolean actualResult = roundTrip.validateFlightPlan(flightPlan);

        // Assert that the actual result matches the expected result
        Assertions.assertEquals(expectedResult, actualResult, "The flight plan validation did not produce the expected result.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateTotalTimeRound.csv", numLinesToSkip = 1)
    void calculateTotalTimeRoundTripTest(String startLocation1, String endLocation1, String startTime1, String endTime1,
                                String startDate1, String endDate1, String startLocation2, String endLocation2,
                                String startTime2, String endTime2, String startDate2, String endDate2,
                                int expectedTotalTime) {
        Round_Trip flightPlanner = new Round_Trip();

        // Construct the input from the CSV row
        String[][] flightLegs = {
                {startLocation1, endLocation1, startTime1, endTime1, startDate1, endDate1},
                {startLocation2, endLocation2, startTime2, endTime2, startDate2, endDate2}
        };

        // Call the method under test
        int actualTotalTime = flightPlanner.calculateTotalTime(flightLegs);

        // Assert that the actual total time matches the expected total time
        Assertions.assertEquals(expectedTotalTime, actualTotalTime);
    }

    @Test
    public void calculateTotalTimeRoundTripShouldThrowExceptionForOverlappingFlights1() {
        Round_Trip roundTrip = new Round_Trip();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Idaho", "04:00 PM", "07:00 PM", "2024-03-15", "2024-03-15"},
                {"Idaho", "New York", "06:00 PM", "07:00 PM", "2024-03-19", "2024-03-19"}
        };

        assertThrows(IllegalArgumentException.class, () -> roundTrip.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 1.");
    }

    @Test
    public void calculateTotalTimeRoundTripShouldThrowExceptionForOverlappingFlights2() {
        Round_Trip roundTrip = new Round_Trip();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Idaho", "05:15 PM", "08:15 PM", "2024-03-15", "2024-03-15"},
                {"Idaho", "New York", "06:00 PM", "07:00 PM", "2024-03-19", "2024-03-19"}
        };

        assertThrows(IllegalArgumentException.class, () -> roundTrip.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 2.");
    }

    @Test
    public void calculateTotalTimeRoundTripShouldThrowExceptionForOverlappingFlights3() {
        Round_Trip roundTrip = new Round_Trip();
        String[][] input = {
                {"New York", "Los Angeles", "08:00 AM", "05:00 PM", "2024-03-14", "2024-03-15"},
                {"Los Angeles", "Idaho", "05:15 PM", "08:15 PM", "2024-03-17", "2024-03-17"},
                {"Idaho", "New York", "06:00 PM", "07:00 PM", "2024-03-19", "2024-03-19"}
        };

        assertThrows(IllegalArgumentException.class, () -> roundTrip.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for input 3.");
    }

    @Test
    public void calculateTotalTimeRoundTripNull() {
        Round_Trip roundTrip = new Round_Trip();
        String[][] input = {{}};

        assertThrows(IllegalArgumentException.class, () -> roundTrip.calculateTotalTime(input),
                "calculateTotalTime should throw IllegalArgumentException for empty input.");
    }


}
