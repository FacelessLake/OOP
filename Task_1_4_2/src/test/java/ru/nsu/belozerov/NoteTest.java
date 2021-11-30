package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    private final LocalDateTime time = LocalDateTime.now();
    private final String heading = "SecondNote";
    private final String text = "text";
    private final Note note = new Note(heading, text);

    @Test
    void getHeadingTest() {
        assertEquals(heading, note.getHeading());
    }

    @Test
    void getTimeTest() {
        assertEquals(time, note.getTime());
    }

    @Test
    void getTextTest() {
        assertEquals(text, note.getText());
    }
}