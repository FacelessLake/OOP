package ru.nsu.belozerov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Note {
    @SerializedName("heading")
    private String heading;
    @SerializedName("time")
    private LocalDateTime time;
    @SerializedName("text")
    private String text;

    Note(String heading, LocalDateTime time, String text) {
        this.heading =  heading;
        this.time = time;
        this.text = text;
    }

    protected String getHeading() {
        return this.heading;
    }

    protected void setTime(LocalDateTime time) {
        this.time = time;
    }

    protected LocalDateTime getTime() {
        return this.time;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected String getText() {
        return this.text;
    }

}
