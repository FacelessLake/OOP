package ru.nsu.belozerov;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class Notebook {
    private final List<Note> notebook;
    private final DateTimeFormatter formatter;

    Notebook() {
        notebook = new ArrayList<>();
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }

    public void add(String heading, String text) {
        Note newNote = new Note(heading, text);
        notebook.add(newNote);
    }

    public void addAll(Note[] notes) {
        if (notes == null) {
            return;
        }
        notebook.addAll(Arrays.asList(notes));
    }

    public Note[] getNotes() {
        return (notebook.toArray(Note[]::new));
    }

    public void remove(String heading) {
        notebook.removeIf(note -> Objects.equals(note.getHeading(),heading));
    }

    public Note[] show() {
        return getNotes();
    }

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
