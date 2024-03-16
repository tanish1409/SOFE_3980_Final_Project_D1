
package com.ontariotechu.sofe3980U;

public class Booking {
    private String name;
    private String from;
    private String to;
    private String date;
    private String time;

    // Constructor
    public Booking(String name, String from, String to, String date, String time) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
