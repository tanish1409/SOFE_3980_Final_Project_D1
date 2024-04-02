package com.ontariotechu.sofe3980U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightBookingPageController {

    Flight_search flightSearch = new Flight_search();
    One_Way oneWay = new One_Way();
    //    @Autowired
//    private One_Way oneWay;
//
//    @Autowired
//    private Round_Trip roundTrip;
//
//    @Autowired
//    private Flight_Search flightSearch = new Flight_Search();
//
//    @Autowired
//    private Ticket_Generate ticketGenerate;

    @GetMapping("/")
    public String showFlightBookingPage() {
        return "flight_booking";
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam("flightType") String flightType,
                                @RequestParam("from") String from,
                                @RequestParam("to") String to,
                                Model model) {
        String[][] flights = null;
        flightSearch.queryDB(from, to);
        flights = flightSearch.getFlightOptions();
//        if ("oneWay".equals(flightType)) {
//            flights = oneWay.searchFlights(from, to);
//        } else if ("roundTrip".equals(flightType)) {
//            flights = roundTrip.searchFlights(from, to);
//        }
        model.addAttribute("flights", flights);
        return "flight_results";
    }
}