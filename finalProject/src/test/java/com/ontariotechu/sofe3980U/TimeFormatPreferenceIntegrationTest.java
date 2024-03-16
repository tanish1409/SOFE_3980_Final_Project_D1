package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TimeFormatPreferenceIntegrationTest {

    @Mock
    private TicketGenerator ticketGenerator;

    @Test
    void test24HourFormatPreference() {
        String expectedTicket = "Flight Ticket: New York to Los Angeles, Departure: 08:00, Arrival: 17:00 on 2024-04-15";
        when(ticketGenerator.printTicket(anyString(), anyString(), anyString(), anyString(), eq(false))).thenReturn(expectedTicket);

        String ticket = ticketGenerator.printTicket("New York", "Los Angeles", "08:00", "17:00", false);

        assertEquals(expectedTicket, ticket);
    }

    @Test
    void test12HourFormatPreference() {
        String expectedTicket = "Flight Ticket: New York to Los Angeles, Departure: 08:00 AM, Arrival: 05:00 PM on 2024-04-15";
        when(ticketGenerator.printTicket(anyString(), anyString(), anyString(), anyString(), eq(true))).thenReturn(expectedTicket);

        String ticket = ticketGenerator.printTicket("New York", "Los Angeles", "08:00", "17:00", true);

        assertEquals(expectedTicket, ticket);
    }
}
