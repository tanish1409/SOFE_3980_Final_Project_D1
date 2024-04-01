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

    public static String printTicket(String[][] flights) {
        // Check if flights array is not empty
        if (flights == null || flights.length == 0) {
            return "No flights to generate ticket for.";
        }

        StringBuilder ticket = new StringBuilder();

        // Append ticket header
        ticket.append("--------------------------------------------------\n");
        ticket.append("                  TICKET                          \n");
        ticket.append("--------------------------------------------------\n");

        // Append ticket details for each flight
        for (int i = 0; i < flights.length; i++) {
            ticket.append("Flight ").append(i + 1).append(":\n");
            ticket.append("From: ").append(flights[i][0]).append("\n");
            ticket.append("To: ").append(flights[i][1]).append("\n");
            ticket.append("Departure Time: ").append(flights[i][2]).append("\n");
            ticket.append("Arrival Time: ").append(flights[i][3]).append("\n");
            ticket.append("Departure Date: ").append(flights[i][4]).append("\n");
            ticket.append("Arrival Date: ").append(flights[i][5]).append("\n\n");
        }

        // Append ticket footer
        ticket.append("--------------------------------------------------\n");
        System.out.println(ticket.toString());
        return ticket.toString();
    }

}

