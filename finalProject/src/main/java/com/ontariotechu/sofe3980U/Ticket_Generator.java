package com.ontariotechu.sofe3980U;

public class Ticket_Generator {

    private static Flight_search fs = new Flight_search();

    public static void convertTime(int hr12, int hr24, String[][] input) throws IllegalArgumentException{
        if ((hr12 + hr24) != 1){
            throw new IllegalArgumentException("Illegal arguments");
        }
        if (hr24 == 1){
            fs.timeFormat24(input);
        }
        else {
            for (String[] flight : input) {
                // Convert departure time
                flight[2] = convertTo12HourFormat(flight[2]);
                // Convert arrival time
                flight[3] = convertTo12HourFormat(flight[3]);
            }
        }

    }

     /**
     * Generates a flight ticket string based on provided details and time format preference.
     * 
     * @param from The departure city.
     * @param to The arrival city.
     * @param departureTime The departure time.
     * @param arrivalTime The arrival time.
     * @param is12HourFormat Indicates if the time should be in 12-hour format (true) or 24-hour format (false).
     * @return A string representation of the flight ticket.
     */
    public String printTicket(String from, String to, String departureTime, String arrivalTime, boolean is12HourFormat) {
        // Dummy implementation for mock 
        if (is12HourFormat) {
            return String.format("Flight Ticket: %s to %s, Departure: %s, Arrival: %s on 2024-04-15", from, to, convertTo12HourFormat(departureTime), convertTo12HourFormat(arrivalTime));
        } else {
            return String.format("Flight Ticket: %s to %s, Departure: %s, Arrival: %s on 2024-04-15", from, to, departureTime, arrivalTime);
        }
    }

    private static String convertTo12HourFormat(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        String minutes = parts[1];
        String amPm = "AM";
        if (hour == 0) {
            hour = 12;
        } else if (hour == 12) {
            amPm = "PM";
        } else if (hour > 12) {
            hour -= 12;
            amPm = "PM";
        }

        return String.format("%02d:%s %s", hour, minutes, amPm);
    }
}

