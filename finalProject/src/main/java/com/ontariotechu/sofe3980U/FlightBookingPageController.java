package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlightBookingPageController {

    @GetMapping("/")
    public String showFlightBookingPage() {
        return "flight_booking";
    }

    @PostMapping("/search")
    public String search() {
        return "flight_results";
    }
}
// Path: src/main/resources/templates/flight_booking.html