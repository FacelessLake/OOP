package ru.nsu.belozerov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    @SerializedName("heading")
    private String heading;
    @SerializedName("text")
    private String text;
    @SerializedName("time")
    private LocalDateTime time;

    Note(String heading, String text, LocalDateTime time) {
        this.heading = heading;
        this.text = text;
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        String date = formatter.format(time);
        return "Heading: \"" + heading + "\"\n" +
                "Date of creation: " + date + "\n" +
                "Note: \"" + text + "\"";
    }

    protected String getHeading() {
        return this.heading;
    }

    protected LocalDateTime getTime() {
        return this.time;
    }
}