package ru.nsu.belozerov;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class helps to create notes and manipulate them
 */
public class Notebook {
    private final List<Note> notebook;
    private final DateTimeFormatter formatter;

    public Notebook() {
        notebook = new ArrayList<>();
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }


    /**
     * Used to add new note
     *
     * @param heading - heading of the note will be used in subsequent manipulations
     * @param text    - text of the note
     */
    public void add(String heading, String text) {
        Note newNote = new Note(heading, text);
        notebook.add(newNote);
    }


    /**
     * Used to move all existed notes
     *
     * @param notes - notes that have been made
     */
    public void addAll(Note[] notes) {
        if (notes == null) {
            return;
        }
        notebook.addAll(Arrays.asList(notes));
    }


    /**
     * Used to take notes from the notebook
     *
     * @return - an array of the notes
     */
    public Note[] getNotes() {
        return (notebook.toArray(Note[]::new));
    }


    /**
     * Used to remove note
     *
     * @param heading - title of the note you want to delete
     */
    public void remove(String heading) {
        notebook.removeIf(note -> Objects.equals(note.getHeading(), heading));
    }


    /**
     * Just returns all the notes, was made for easier usability
     *
     * @return - all the notes
     */
    public Note[] show() {
        return getNotes();
    }


    /**
     * Returns notes filtered by next parameters:
     *
     * @param afterStr  - note was made after this date
     * @param beforeStr - note was made before this date
     * @param keyWord   - heading of the note contains this word
     * @return - array of the suitable notes
     */
    public Note[] show(String afterStr, String beforeStr, String keyWord) {
        LocalDateTime after = LocalDateTime.parse(afterStr, formatter);
        LocalDateTime before = LocalDateTime.parse(beforeStr, formatter);

        Stream<Note> notebookStream = notebook.stream();
        return notebookStream
                .filter(note -> note.getTime().isAfter(after) && note.getTime().isBefore(before))
                .filter(note -> note.getHeading().contains(keyWord))
                .toArray(Note[]::new);
    }
}