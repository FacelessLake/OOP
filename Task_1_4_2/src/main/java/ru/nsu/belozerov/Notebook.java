package ru.nsu.belozerov;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Notebook {
    private final List<Note> notebook;
    private final DateTimeFormatter formatter;

    Notebook() {
        notebook = new ArrayList<>();
        formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    }

    public void add(String heading, String text) {
        LocalDateTime now = LocalDateTime.now();
        Note newNote = new Note(heading, text, now);
        notebook.add(newNote);
    }

    public void uploadFromJson(Note[] notes) {
        notebook.addAll(Arrays.asList(notes));
    }

    public void remove(String heading) {
        notebook.removeIf(note -> note.getHeading().equals(heading));
    }

    public Note[] show() {
        return notebook.toArray(Note[]::new);
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
