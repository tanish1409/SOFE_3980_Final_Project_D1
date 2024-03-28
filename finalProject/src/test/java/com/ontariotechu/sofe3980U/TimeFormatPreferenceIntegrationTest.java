package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TimeFormatPreferenceIntegrationTest {

    @Mock
    private Ticket_Generator ticketGenerator;

    @Test
    void test24HourFormatPreference() {
        String expectedTicket = "Flight Ticket: New York to Los Angeles, Departure: 08:00, Arrival: 17:00 on 2024-04-15";
        when(ticketGenerator.printTicket(anyString(), anyString(), anyString(), anyString(), eq(false))).thenReturn(expectedTicket);

        String ticket = ticketGenerator.printTicket("New York", "Los Angeles", "08:00", "17:00", false);

        Assertions.assertEquals(expectedTicket, ticket);
    }

    @Test
    void test12HourFormatPreference() {
        String expectedTicket = "Flight Ticket: New York to Los Angeles, Departure: 08:00 AM, Arrival: 05:00 PM on 2024-04-15";
        when(ticketGenerator.printTicket(anyString(), anyString(), anyString(), anyString(), eq(true))).thenReturn(expectedTicket);

        String ticket = ticketGenerator.printTicket("New York", "Los Angeles", "08:00", "17:00", true);

        Assertions.assertEquals(expectedTicket, ticket);
    }
}
