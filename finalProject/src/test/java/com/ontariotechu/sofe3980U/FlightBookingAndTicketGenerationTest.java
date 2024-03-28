package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;


class FlightBookingAndTicketGenerationTest {

    @Mock
    private DatabaseService databaseService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingService bookingService;

   @Test
void testSuccessfulBookingAndTicketPrinting() {
    // Setup
    when(databaseService.addBookingDB(any(Booking.class))).thenReturn(true);
    String expectedTicket = "Ticket for John Doe: New York to Los Angeles on 2024-04-15 at 08:00 AM";
    // Adjusted to match the method signature of printTicket
       Booking b1 = new Booking("John Doe", "New York", "Los Angeles", "2024-04-15", "17:00");
    when(TicketService.printTicket(b1)).thenReturn(expectedTicket);

    // Action
    Booking bookingRequest = new Booking("John Doe", "New York", "Los Angeles", "2024-04-15", "08:00 AM");
    boolean bookingAdded = bookingService.addBookingDB(bookingRequest);
    // Adjusted call to printTicket to match its actual parameters
    String actualTicket = ticketService.printTicket(bookingRequest); // Assuming Booking class has these getter methods

    // Assertions
    assertAll("Booking and ticket generation",
            () -> assertTrue(bookingAdded, "Booking should be added successfully"),
            () -> Assertions.assertEquals(expectedTicket, actualTicket, "Ticket information should match expected output")
    );
}

//    @Test
//    void testBookingFailure() {
//        // Setup
//        when(databaseService.addBookingDB(any(Booking.class))).thenReturn(false);
//
//        // Action
//        Booking bookingRequest = new Booking("John Doe", "New York", "Los Angeles", "2024-04-15", "08:00 AM");
//        boolean bookingAdded = bookingService.addBookingDB(bookingRequest);
//
//        // Assertions
//        assertAll("Booking failure handling",
//                () -> assertFalse(bookingAdded, "Booking should not be added"),
//                () -> verify(ticketService, never()).printTicket(any(Booking.class), "Ticket printing should not occur on booking failure")
//        );
//    }
}
