package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {

    Notebook nb1 = new Notebook();
    Notebook nb2 = new Notebook();

    @BeforeEach
    void fillIn() {
        nb1.add("meme1", "4:19");
        nb1.add("meme2", "4:20");
    }

    @Test
    void getNotesTest() {
        Note[] arr = new Note[2];
        arr[0] = new Note("meme1", "4:19");
        arr[1] = new Note("meme2", "4:20");
        assertEquals(arr[1].getHeading(), nb1.getNotes()[1].getHeading());
    }

    @Test
    void addAllTest() {
        nb2.addAll(nb1.getNotes());
        assertArrayEquals(nb1.getNotes(), nb2.getNotes());
    }

    @Test
    void removeTest() {
        nb2.addAll(nb1.getNotes());
        nb2.add("meme3", ".)");
        nb2.remove("meme3");
        assertArrayEquals(nb1.getNotes(), nb2.getNotes());
    }

    @Test
    void show_withArgs() {
        Note[] savedNotes = nb1.show("01.01.1990 00:00:00", "20.12.2025 00:00:00", "meme2");
        assertEquals(nb1.getNotes()[1].getText(), savedNotes[0].getText());
    }

    @Test
    void show_withoutArgs() {
        Note[] savedNotes = nb1.show();
        assertArrayEquals(nb1.getNotes(), savedNotes);
    }
}