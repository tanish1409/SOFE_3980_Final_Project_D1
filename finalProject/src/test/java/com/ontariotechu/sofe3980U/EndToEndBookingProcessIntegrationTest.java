import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

class EndToEndBookingProcessIntegrationTest {

    @Mock
    private FlightSearchService flightSearchService;

    @Mock
    private BookingDatabase bookingDatabase;

    @Mock
    private TicketPrintingService ticketPrintingService;

    @InjectMocks
    private BookingSystem bookingSystem;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEndToEndBookingProcess() {
        // Setup mock responses for each step of the process
        when(flightSearchService.searchFlights(anyString(), anyString(), anyString()))
                .thenReturn(new Flight("New York", "Los Angeles", "2024-04-15", "08:00", "17:00"));

        when(bookingDatabase.addBookingDB(any(Booking.class)))
                .thenReturn(true); // Simulate successful booking

        String expectedTicketContent = "Booking confirmed for [User] on Flight: New York to Los Angeles, 2024-04-15 at 08:00 AM";
        when(ticketPrintingService.printTicket(any(Booking.class)))
                .thenReturn(expectedTicketContent);

        // Execute the end-to-end booking process
        Flight selectedFlight = bookingSystem.searchAndSelectFlight("New York", "Los Angeles", "2024-04-15", "08:00 AM");
        assertNotNull("Flight should be selected", selectedFlight);

        Booking booking = bookingSystem.enterPersonalDetailsAndConfirm("User", selectedFlight);
        assertNotNull("Booking should be successful", booking);

        String ticket = bookingSystem.printTicket(booking);
        assertEquals("The printed ticket content should match the expected output", expectedTicketContent, ticket);

        // Verify that each method was called with the expected parameters
        verify(flightSearchService, times(1)).searchFlights("New York", "Los Angeles", "2024-04-15");
        verify(bookingDatabase, times(1)).addBookingDB(booking);
        verify(ticketPrintingService, times(1)).printTicket(booking);
    }
}
