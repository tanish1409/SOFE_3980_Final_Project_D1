package com.ontariotechu.sofe3980U;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Round_Trip {

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

    public int calculateTotalTime(String[][] input) throws IllegalArgumentException{
        return -1;
    }
}
