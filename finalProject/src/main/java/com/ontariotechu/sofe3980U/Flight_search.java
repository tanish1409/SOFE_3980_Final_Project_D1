package com.ontariotechu.sofe3980U;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Flight_search {

    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Set<String> validCities = new HashSet<>(Arrays.asList("New York", "Toronto", "Idaho", "Los Angeles"));
    public boolean inputValidation(String[] flight){

        if (flight == null || flight.length < 6) {
            return false;
        }

        for (String field : flight) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }

        Date now = new Date(); // Current date and time for future date validation

        // Validate start and end locations
        if (!validCities.contains(flight[0]) || !validCities.contains(flight[1])) {
            return false;
        }

        // Validate that start and end dates and times are in the future and end is after start
        try {
            Date startTime = dateTimeFormat.parse(flight[4] + " " + flight[2]);
            Date endTime = dateTimeFormat.parse(flight[5] + " " + flight[3]);
            if (!endTime.after(startTime)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
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
