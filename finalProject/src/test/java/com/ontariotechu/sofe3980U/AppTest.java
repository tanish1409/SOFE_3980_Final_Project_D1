package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
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
}
