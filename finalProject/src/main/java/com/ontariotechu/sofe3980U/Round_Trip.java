package com.ontariotechu.sofe3980U;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Round_Trip implements Flight_Book{

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        public boolean validateFlightPlan(String[][] flights){
        if (flights == null || flights.length == 0) {
            return false;
        }

        // There must be at least two flights for a round trip
        if (flights.length < 2) {
            return false;
        }

        // The start location of the first flight must match the end location of the last flight
        if (!flights[0][0].equals(flights[flights.length - 1][1])) {
            return false;
        }

        // Initialize variables to check the 24 hour gap condition
        boolean has24HourGap = false;

        try {
            for (int i = 1; i < flights.length; i++) {
                // Parse the dates and times
                Date previousArrivalDate = dateFormat.parse(flights[i - 1][5] + " " + flights[i - 1][3]);
                Date currentDepartureDate = dateFormat.parse(flights[i][4] + " " + flights[i][2]);

                // Check if start destination of the current flight is the same as the end destination of the previous flight
                if (!flights[i - 1][1].equals(flights[i][0])) {
                    return false;
                }

                // Check if the next flight is at least 30 minutes after the previous flight
                if (currentDepartureDate.getTime() - previousArrivalDate.getTime() < 30 * 60 * 1000) {
                    return false;
                }

                // Check if two flights are within 24 hours of each other (or exactly 24 hours for a round trip with only 2 flights)
                long timeDifference = currentDepartureDate.getTime() - previousArrivalDate.getTime();
                if (flights.length > 2) {
                    // If there are more than two flights, only one duplet of flights can have a 24-hour gap
                    if (timeDifference > 24 * 60 * 60 * 1000) {
                        if (has24HourGap) {
                            // If we already have one 24-hour gap, this is not allowed
                            return false;
                        } else {
                            // Mark that we have found a 24-hour gap
                            has24HourGap = true;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
        public int calculateTotalTime(String[][] flights) throws IllegalArgumentException{
        if (flights == null || flights.length == 0 || flights[0].length == 0) {
            throw new IllegalArgumentException("Flight plan cannot be empty");
        }

        if (!validateFlightPlan(flights)) {
            throw new IllegalArgumentException("Invalid flight plan");
        }

        int totalTimeInMinutes = 0;
        boolean isSecondLeg = false;

        try {
            for (int i = 0; i < flights.length; i++) {
                Date departTime = dateFormat.parse(flights[i][4] + " " + flights[i][2]);
                Date arriveTime = dateFormat.parse(flights[i][5] + " " + flights[i][3]);

                // Calculate the flight duration in milliseconds and convert it to minutes.
                long flightDuration = arriveTime.getTime() - departTime.getTime();
                totalTimeInMinutes += flightDuration / (60 * 1000);

                // If there's a next flight, check for a layover or the start of the second leg
                if (i < flights.length - 1) {
                    Date nextDepartTime = dateFormat.parse(flights[i + 1][4] + " " + flights[i + 1][2]);

                    // Check if the next flight starts more than 24 hours later (start of the second leg)
                    long timeDifference = nextDepartTime.getTime() - arriveTime.getTime();
                    if (timeDifference > 24 * 60 * 60 * 1000) {
                        if (!isSecondLeg) {
                            // First occurrence of more than 24-hour difference, mark start of the second leg
                            isSecondLeg = true;
                        }
                    } else {
                        // If it's not the start of the second leg, calculate the layover duration and add to total time
                        long layoverDuration = timeDifference;
                        totalTimeInMinutes += layoverDuration / (60 * 1000);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error parsing flight dates/times");
        }

        return totalTimeInMinutes;
    }
    public void displayAllFlights(String[][] flights) {
        for (int i = 0; i < flights.length; i++) {
            System.out.println("Flight " + (i + 1) + ":");
            System.out.println("From: " + flights[i][0]);
            System.out.println("To: " + flights[i][1]);
            System.out.println("Departure Time: " + flights[i][2]);
            System.out.println("Arrival Time: " + flights[i][3]);
            System.out.println("Departure Date: " + flights[i][4]);
            System.out.println("Arrival Date: " + flights[i][5]);
            System.out.println();
        }
    }

    public String chooseFlight(String[][] flights) {
        return null;
    }

    public String[][] addFlight(String flight) {
        return new String[0][];
    }

    public void addBookingDB(String[][] flight) {

    }
}
