package ru.nsu.belozerov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File file = new File("notes.json");
    private Reader reader = null;
    private Writer writer = null;

    public void open() throws IOException {
        if (file.createNewFile()) {
            System.out.println("\"notes.json\" is not found, new file has been created");
        }
        reader = Files.newBufferedReader(Paths.get("notes.json"));
        writer = Files.newBufferedWriter(Paths.get("notes.json"));
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
    }

    public Note[] readJson() {
        return gson.fromJson(reader, Note[].class);
    }

    public void writeJson(Notebook notes) {
        gson.toJson(notes, writer);
    }
}
