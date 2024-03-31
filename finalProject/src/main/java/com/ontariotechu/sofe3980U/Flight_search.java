
package com.ontariotechu.sofe3980U;

import java.util.List;

public class Flight_search {
    public boolean inputValidation(String startLoc, String endLoc, String startDate, String endDate, String startTime, String endTime){
        return false;
    }

    public static void timeFormat24(String[][] input) throws IllegalArgumentException {
        for (String[] flight : input) {
            flight[2] = convert12HTo24H(flight[2]);
            flight[3] = convert12HTo24H(flight[3]);
        }
    }

    private static String convert12HTo24H(String time12) throws IllegalArgumentException {
        if (!time12.matches("(1[0-2]|0?[1-9]):[0-5][0-9] [AP]M")) {
            throw new IllegalArgumentException("Invalid time format: " + time12);
        }

        String[] parts = time12.split("[: ]");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String amPm = parts[2];

        if ("PM".equals(amPm) && hour < 12) {
            hour += 12;
        } else if ("AM".equals(amPm) && hour == 12) {
            hour = 0;
        }

        return String.format("%02d:%02d", hour, minute);
    }

     public List<Flight> getFlights(String from, String to, String date, String startTime, String endTime) {
        // Dummy implementation
        // Return an example list of flights or mock data
        return List.of(new Flight(from, to, date, startTime, endTime));
    }
}
