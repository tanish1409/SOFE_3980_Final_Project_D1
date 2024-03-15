package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    void inputValidationTests(String[] input, String expected) {
        Flight_search flight = new Flight_search();
        boolean actualValue = flight.inputValidation(input);
        assertEquals(expected, actualValue);
    }
}
