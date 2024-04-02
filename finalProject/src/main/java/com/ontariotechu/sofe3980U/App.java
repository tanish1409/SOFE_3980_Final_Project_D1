package com.ontariotechu.sofe3980U;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    public static void displayStringArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            boolean rowIsNull = true;
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != null) {
                    rowIsNull = false;
                    break;
                }
            }
            if (!rowIsNull) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println(); // Move to the next line after printing each row
            }
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        //just to test working
//        Flight_search flightSearch = new Flight_search();
//        flightSearch.queryDB("New York", "Los Angeles");
//        String[][] newarr = flightSearch.getFlightOptions();
//        Round_Trip rdWay = new Round_Trip();
//        String[][] newarr1 = rdWay.getFlights(flightSearch);
//        rdWay.displayAllFlights(newarr1);
//        String flight1 = rdWay.chooseFlight(newarr1);
//        displayStringArray(rdWay.addFlight(flight1));
//        String flight2= rdWay.chooseFlight(newarr1);
//        displayStringArray(rdWay.addFlight(flight2));
//        rdWay.validateFlightPlan(rdWay.getFlightPlan());
//        Ticket_Generator ticketGenerator = new Ticket_Generator();
//        ticketGenerator.printTicket(rdWay.getFlightPlan());
    }
}
