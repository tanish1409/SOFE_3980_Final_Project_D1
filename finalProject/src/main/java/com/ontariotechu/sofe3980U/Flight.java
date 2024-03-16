// Mock Object Implementation for integration testing
package com.ontariotechu.sofe3980U;

public class Flight {
    private String from;
    private String to;
    private String date;
    private String departureTime;
    private String arrivalTime;

    public Flight(String from, String to, String date, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("Flight from %s to %s on %s, Departure: %s, Arrival: %s", from, to, date, departureTime, arrivalTime);
    }
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDate() {
        return date;
    }
}
