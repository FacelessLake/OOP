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

    Note(String heading, String text) {
        this.heading = heading;
        this.text = text;
        time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String dateTime = formatter.format(time);
        return "\n=======================================\n" +
                "Created: " + dateTime + "\n" +
                "Heading: \"" + heading + "\"\n" +
                "Note: \"" + text + "\"\n" +
                "======================================\n";
    }

    public String getHeading() {
        return heading;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getText() {
        return text;
    }
}