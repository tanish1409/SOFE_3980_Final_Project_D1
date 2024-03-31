package com.ontariotechu.sofe3980U;

public interface Flight_Book {
    public void displayAllFlights(String flights[][]);
    public String chooseFlight(String flights[][]);
    public String[][] addFlight(String flight);
    public boolean validateFlightPlan(String flight [][]);
    public void addBookingDB(String flight [][]);
    public int calculateTotalTime(String flight [][]);
}
