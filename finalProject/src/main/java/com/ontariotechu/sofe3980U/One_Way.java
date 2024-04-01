package com.ontariotechu.sofe3980U;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class One_Way implements Flight_Book {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
    private String flightPlan[][];

    public One_Way() {
        this.flightPlan = new String[10][6];}
    public boolean validateFlightPlan(String[][] flights) {
        if (flights == null || flights.length == 0) {
            return false;
        }

        try {
            // Loop through each flight starting from the second one
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


    public int calculateTotalTime(String[][] flights) throws IllegalArgumentException {
        if (flights == null || flights.length == 0 || flights[0].length == 0) {
            throw new IllegalArgumentException("Flight plan cannot be empty");
        }

        if (!validateFlightPlan(flights)) {
            throw new IllegalArgumentException("Invalid flight plan");
        }

        int totalTimeInMinutes = 0;
        try {
            for (int i = 0; i < flights.length; i++) {
                Date departTime = dateFormat.parse(flights[i][4] + " " + flights[i][2]);
                Date arriveTime = dateFormat.parse(flights[i][5] + " " + flights[i][3]);

                // Calculate the flight duration and add to total time
                long flightDuration = arriveTime.getTime() - departTime.getTime();
                totalTimeInMinutes += flightDuration / (60 * 1000);

                // If there's a next flight, calculate the layover time
                if (i < flights.length - 1) {
                    Date nextDepartTime = dateFormat.parse(flights[i + 1][4] + " " + flights[i + 1][2]);
                    // Calculate the layover duration and add to total time
                    long layoverDuration = nextDepartTime.getTime() - arriveTime.getTime();
                    totalTimeInMinutes += layoverDuration / (60 * 1000);
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

    @Override
    public String chooseFlight(String[][] flights) {
        // Display all flights to the user
        displayAllFlights(flights);

        // Prompt the user to choose a flight
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please choose a flight by entering the flight number: ");
        int chosenFlightNumber = scanner.nextInt();

        // Validate the chosen flight number
        if (chosenFlightNumber < 1 || chosenFlightNumber > flights.length) {
            System.out.println("Invalid flight number. Please choose a valid flight.");
            return chooseFlight(flights); // Recursive call until a valid flight number is chosen
        }

        // Convert the chosen flight details to a string and return
        StringBuilder chosenFlightDetails = new StringBuilder();
        String[] chosenFlight = flights[chosenFlightNumber - 1];
        for (String detail : chosenFlight) {
            chosenFlightDetails.append(detail).append(" ");
        }
        return chosenFlightDetails.toString().trim();
    }

    @Override
    public String[][] addFlight(String flight) {

        // Split the flight details string into individual pieces of information
        String[] details = flight.split(" ");
        String startLoc = details[0]+" " +details[1];
        String endLoc = details[2]+" "+details[3];
        String startTime = details[4]+" "+details[5];
        String endTime = details[6] + " " + details[7];
        String startDate =details[8];
        String endDate= details[9];
        // Create a new 2D array to store the flight plan
        int rowIndex = -1;
        for (int i = 0; i < flightPlan.length; i++) {
            if (flightPlan[i][0] == null) {
                rowIndex = i;
                break;
            }
        }
        flightPlan[rowIndex][0]= startLoc;
        flightPlan[rowIndex][1]= endLoc;
        flightPlan[rowIndex][2]= startTime;
        flightPlan[rowIndex][3]= endTime;
        flightPlan[rowIndex][4]= startDate;
        flightPlan[rowIndex][5]= endDate;
        System.out.println(details.length);
        System.out.println(flightPlan.length);
        // Return the flight plan array
        return flightPlan;

    }
    @Override
    public void addBookingDB(String[][] flight) {

    }

    public String[][] getFlights(Flight_search flightSearch) {
        // Get the flight options directly from the Flight_Search object
        String[][] flightOptions = flightSearch.getFlightOptions();

        // Create a new 2D array to store all flights with the same dimensions as flightOptions
        String[][] allFlights = new String[flightOptions.length][flightOptions[0].length];

        // Copy each element from flightOptions to allFlights
        for (int i = 0; i < flightOptions.length; i++) {
            for (int j = 0; j < flightOptions[i].length; j++) {
                allFlights[i][j] = flightOptions[i][j];
            }
        }

        // Return the array containing all flights
        return allFlights;
    }
    public String[][] getFlightPlan() {

        return flightPlan;
    }
}
