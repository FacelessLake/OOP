package ru.nsu.belozerov;

import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("time")
    private String time;
    @SerializedName("text")
    private String text;

    Note(String time, String text) {
        this.time = time;
        this.text = text;
    }

    protected void setTime(String time) {
        this.time = time;
    }

    protected String getTime() {
        return this.time;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected String getText() {
        return this.text;
    }

}
