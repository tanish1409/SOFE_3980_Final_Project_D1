package com.ontariotechu.sofe3980U;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class One_Way {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");

    public boolean validateFlightPlan(String[][] flights) {
        if (flights == null || flights.length == 0) {
            return false;
        }

        try {
            // Loop through each flight starting from the second one
            for (int i = 1; i < flights.length; i++) {
                // Parse the dates and times
                Date previousArrivalDate = dateFormat.parse(flights[i - 1][5] + " " + flights[i - 1][3]); // Use index 5 for arrive_date, 3 for arrive_time
                Date currentDepartureDate = dateFormat.parse(flights[i][4] + " " + flights[i][2]); // Use index 4 for depart_date, 2 for depart_time


                // Check if start destination of the current flight is the same as the end destination of the previous flight
                if (!flights[i - 1][1].equals(flights[i][0])) {
                    return false;
                }

                // Check if the next flight is at least 30 minutes after the previous flight
                if (currentDepartureDate.getTime() - previousArrivalDate.getTime() < 30 * 60 * 1000) {
                    return false;
                }

                // Check if two flights are within 24 hours of each other
                if (currentDepartureDate.getTime() - previousArrivalDate.getTime() > 24 * 60 * 60 * 1000) {
                    return false;
                }
            }

            // Check to prevent cyclic paths
            if (flights[0][0].equals(flights[flights.length - 1][1])) {
                return false;
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
