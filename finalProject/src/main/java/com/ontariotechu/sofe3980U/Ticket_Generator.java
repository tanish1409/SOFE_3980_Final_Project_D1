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

