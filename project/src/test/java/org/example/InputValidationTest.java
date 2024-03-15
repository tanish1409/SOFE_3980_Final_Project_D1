package org.example;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import junit.framework.TestCase;

public class InputValidationTest extends TestCase {
    @Test
    public void testInputValidation() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", "2024-03-15", "08:00", "17:00"}));
        assertFalse(validator.validateInput(
                new String[]{"ABCD", "Los Angeles", "2024-03-14", "2024-03-15", "08:00", "17:00"}));
        assertFalse(validator.validateInput(
                new String[]{"New York", "ABCD", "2024-03-14", "2024-03-15", "08:00", "17:00"}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", "2024-03-15", " ", "08:00"}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", "2024-03-15", "08:00 ", " "}));
        assertFalse(validator.validateInput(
                new String[]{" ", "Los Angeles", "2024-03-14", "2024-03-15", "08:00", "17:00"}));
        assertFalse(validator.validateInput(
                new String[]{"New York", " ", "2024-03-14", "2024-03-15", "08:00", "17:00"}));
        assertFalse(validator.validateInput(
                new String[]{"New York", "Los Angeles", " ", "2024-03-15", "08:00", "17:00"}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", " ", "08:00", "17:00"}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", " ", " "}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", " ", "08:00", " "}));
        assertTrue(validator.validateInput(
                new String[]{"New York", "Los Angeles", "2024-03-14", " ", " ", " "}));
    }
}