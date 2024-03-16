package com.ontariotechu.sofe3980U;

public class BookingService {
    private DatabaseService databaseService;
    private TicketService ticketService;

    // Constructor with service dependencies
    public BookingService(DatabaseService databaseService, TicketService ticketService) {
        this.databaseService = databaseService;
        this.ticketService = ticketService;
    }

    public boolean addBookingDB(Booking booking) {
        // Forward the call to the DatabaseService
        return databaseService.addBookingDB(booking);
    }

    public String printTicket(Booking booking) {
        // Forward the call to the TicketService
        return ticketService.printTicket(booking);
    }
}
