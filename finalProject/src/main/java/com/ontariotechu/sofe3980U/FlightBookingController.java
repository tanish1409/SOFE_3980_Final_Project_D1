package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
public class FlightBookingController {

    @GetMapping("/")
    public String index() {
        return "<html><head></head><body>"
                + "<h1>Flight Booking</h1>"
                + "<form action='/flight/search' method='post'>"
                + "From: <input type='text' name='from'><br>"
                + "To: <input type='text' name='to'><br>"
                + "<button type='submit'>Search Flights</button>"
                + "</form>"
                + "</body></html>";
    }

    @PostMapping("/search")
    public String search(@RequestParam String from, @RequestParam String to) {
        // Call your Java code to search for flights
        String[][] flights = searchFlights(from, to);

        // Return the results as HTML
        String result = "<h2>Flights from " + from + " to " + to + "</h2>";
        for (String[] flight : flights) {
            result += "<p>" + flight[0] + " to " + flight[1] + "</p>";
        }
        return result;
    }

    private String[][] searchFlights(String from, String to) {
        // Your Java code to search for flights
        // Return a sample result for demonstration
        return new String[][]{{"Flight 1", "10:00 AM"}, {"Flight 2", "1:00 PM"}};
    }
}
