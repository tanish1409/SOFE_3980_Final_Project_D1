package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightsController {

    private final Flight_search flightSearch = new Flight_search();
    private final Round_Trip roundTrip = new Round_Trip();

    @GetMapping("/")
    public String searchPage() {
        return "search"; // HTML page for searching flights
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam String from, @RequestParam String to, Model model) {
        try {
            flightSearch.queryDB(from, to);
            String[][] flights = flightSearch.getFlightOptions();
            flightSearch.removeEmptyFlights();
            if (flights == null || flights.length == 0) {
                // Using a model attribute to send a message back to the search page
                model.addAttribute("message", "No flights found.");
                return "search"; // Stay on the search page and show the message
            }
            model.addAttribute("flights", flights);
            return "flights"; // Proceed to the page that lists flights
        } catch (Exception e) {
            // Log the error for debugging purposes
            // logger.error("Error searching for flights: ", e); // Consider using a logger here
            model.addAttribute("errorMessage", "An error occurred while searching for flights.");
            return "search"; // Consider redirecting to the search page with an error message
        }
    }


    @PostMapping("/select")
    public String selectFlight(@RequestParam String flightDetails, Model model) {
        String[][] selectedFlight = roundTrip.addFlight(flightDetails);
        String ticket = Ticket_Generator.printTicket(selectedFlight);
        model.addAttribute("ticket", ticket);
        return "ticket"; // HTML page to show the ticket
    }

    @PostMapping("/printTickets")
    public String printSelectedTickets(@RequestParam List<String> selectedFlights, Model model) {
        List<String> ticketsHtml = new ArrayList<>();
        for (String flightDetails : selectedFlights) {
            // Assuming flightDetails is a concatenated string of flight information
            String[] flightArray = flightDetails.split("\\s+", 6); // Split by whitespace but limit to 6 elements
            String[][] selectedFlight = new String[1][];
            selectedFlight[0] = flightArray;

            // Generate the HTML content for the ticket here
            String ticketHtml = generateTicketHtml(selectedFlight);
            ticketsHtml.add(ticketHtml);
        }
        model.addAttribute("tickets", ticketsHtml);
        return "tickets"; // HTML page to show all the printed tickets
    }

    private String generateTicketHtml(String[][] flightDetails) {
        StringBuilder ticketHtml = new StringBuilder();
        ticketHtml.append("<div class=\"ticket-container\">");
        ticketHtml.append("<div class=\"ticket-header\"><h3>TICKET</h3></div>");
        ticketHtml.append("<div class=\"ticket-body\">");
        ticketHtml.append("<div class=\"flight-detail\"><span>Flight #1:</span></div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>From:</span> ").append(flightDetails[0][0]).append("</div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>To:</span> ").append(flightDetails[0][1]).append("</div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>Departure Time:</span> ").append(flightDetails[0][2]).append("</div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>Arrival Time:</span> ").append(flightDetails[0][3]).append("</div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>Departure Date:</span> ").append(flightDetails[0][4]).append("</div>");
        ticketHtml.append("<div class=\"flight-detail\"><span>Arrival Date:</span> ").append(flightDetails[0][5]).append("</div>");
        ticketHtml.append("</div></div>");

        return ticketHtml.toString();
    }


}