package com.ontariotechu.sofe3980U;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Flight_search {
    private String[][] flightOptions;
    private String[][] DBArray;
    public Flight_search() {
        this.flightOptions = new String[10][6];
        DBArray = new String[][]{
                {"New York", "Costa Rica","8:00 AM", "5:00 PM", "2024-03-14", "2024-03-14" },
                {"Costa Rica", "Los Angeles", "6:00 PM", "8:00 PM", "2024-03-15", "2024-03-15"},
                {"Toronto", "Idaho", "9:00 AM", "6:00 PM", "2024-03-14", "2024-03-14"},
                {"Toronto", "Idaho", "10:00 AM", "7:00 PM", "2024-03-14", "2024-03-14"},
                {"Toronto", "Idaho", "11:00 AM", "8:00 PM", "2024-03-14", "2024-03-14"},
                {"Toronto", "Idaho", "12:00 AM", "9:00 PM", "2024-03-14", "2024-03-14"},
                {"Toronto", "Idaho", "7:00 AM", "10:00 PM", "2024-03-14", "2024-03-14"},
                {"New York", "Los Angeles","7:00 AM", "4:00 PM", "2024-03-14", "2024-03-14" },
                {"New York", "Los Angeles","6:00 AM", "3:00 PM", "2024-03-14", "2024-03-14" },
                {"New York", "Los Angeles","5:00 AM", "2:00 PM", "2024-03-14", "2024-03-14" }
        };

    }
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Set<String> validCities = new HashSet<>(Arrays.asList("New York", "Toronto", "Idaho", "Los Angeles", "Costa Rica"));
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
    public String[][] getFlightOptions() {

        return flightOptions;
    }
    public void queryDB(String startLocation, String endLocation) {
        List<String[]> result = new ArrayList<>();
        for (String[] row : DBArray) {
            if (result.size() >= 10) {
                break; // Stop searching once 10 flights are found
            }
            if (row.length >= 6 && row[0].equalsIgnoreCase(startLocation) && row[1].equalsIgnoreCase(endLocation)) {
                result.add(row);
            }
        }
        // Update flightOptions with the query result
        for (int i = 0; i < result.size(); i++) {
            String[] row = result.get(i);
            for (int j = 0; j < row.length && j < 6; j++) {
                flightOptions[i][j] = row[j];
            }
        }
    }
    public void removeEmptyFlights() {
        List<String[]> tempList = new ArrayList<>(); // Temporary list to hold non-empty flights

        for (String[] flight : flightOptions) {
            boolean isEmptyFlight = true; // Assume the flight is empty until proven otherwise

            for (String field : flight) {
                if (field != null && !field.trim().isEmpty()) {
                    // If any field is not null and not empty, the flight is not empty
                    isEmptyFlight = false;
                    break; // No need to check the rest of the fields
                }
            }

            if (!isEmptyFlight) {
                // If the flight is not empty, add it to the temporary list
                tempList.add(flight);
            }
        }

        // Convert the temporary list back to the array and update flightOptions
        flightOptions = tempList.toArray(new String[0][0]); // Adjust the size dynamically based on the tempList content
    }
}
