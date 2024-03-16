package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Flight_Search_Test {

    @ParameterizedTest
    @CsvFileSource(resources = "/inputValidation.csv", numLinesToSkip = 1)
    void inputValidationTests(String input, String expected) {
        Flight_search flight = new Flight_search();
        boolean actualValue = flight.inputValidation(input.split(","));
        assertEquals(Boolean.parseBoolean(expected), actualValue);
    }
}
