package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", name);

        return "hello";
    }
    private final Flight_search flightSearch = new Flight_search();
    private final Round_Trip roundTrip = new Round_Trip(); // Assume this handles both round and one-way trips for simplicity

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
        List<String> tickets = new ArrayList<>();
        for (String flightDetails : selectedFlights) {
            // Assuming flightDetails is a concatenated string of flight information
            // You might need to split it and create a proper structure that your ticket generator expects
            String[] flightArray = flightDetails.split(" ");
            String[][] selectedFlight = new String[1][6];
            System.arraycopy(flightArray, 0, selectedFlight[0], 0, 6);

            String ticket = Ticket_Generator.printTicket(selectedFlight); // Adjust this call according to your actual method signature
            tickets.add(ticket);
        }
        model.addAttribute("tickets", tickets);
        return "tickets"; // HTML page to show all the printed tickets
    }
}