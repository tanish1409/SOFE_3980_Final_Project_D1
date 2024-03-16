package com.ontariotechu.sofe3980U;

public class TicketService {
    public String printTicket(Booking booking) {
        // Dummy implementation
        return "Ticket for " + booking.getName() + ": " + booking.getFrom() + " to " + booking.getTo() + " on " + booking.getDate() + " at " + booking.getTime();
    }
}
