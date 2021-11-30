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


    /**
     * One of the notes that will be used in the notebook
     *
     * @param heading - name of the note, which will be used for manipulations with it
     * @param text    - the note itself
     */
    Note(String heading, String text) {
        this.heading = heading;
        this.text = text;
        time = LocalDateTime.now();
    }


    /**
     * Make output more readable
     *
     * @return - date,heading and name
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String dateTime = formatter.format(time);
        return "=======================================\n" +
                "Created: " + dateTime + "\n" +
                "Heading: \"" + heading + "\"\n" +
                "Note: \"" + text + "\"\n" +
                "=======================================\n";
    }


    /**
     * Returns heading of the note
     *
     * @return - heading
     */
    public String getHeading() {
        return heading;
    }


    /**
     * Returns time of the note
     *
     * @return - time
     */
    public LocalDateTime getTime() {
        return time;
    }


    /**
     * Returns text of the note
     *
     * @return - text
     */
    public String getText() {
        return text;
    }
}