package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @CsvFileSource({"[\"New York\", \"Los Angeles\", \"2024-03-14\", \"2024-03-15\", \"08:00\", \"17:00\"]", ""})
    void inputValidationTests(String[] input, boolean expected) {
        Flight_search flight = new Flight_search();
        boolean actualValue = flight.inputValidation(input);
        assertEquals(expected, actualValue);
    }
}
