package ru.nsu.belozerov;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class JsonHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString(), formatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(formatter.format(src)))
            .setPrettyPrinting()
            .create();

    private final File file = new File("notes.json");
    private Reader reader = null;
    private FileWriter writer = null;


    public void openRead() throws IOException {
        file.createNewFile();
        reader = Files.newBufferedReader(file.toPath());
    }

    public void openWrite() throws IOException {
        writer = new FileWriter(file); //deletes contents of file
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
    }

    public Note[] readJson() {
        return gson.fromJson(reader, Note[].class);
    }

    public void writeJson(Notebook notes) throws IOException {
        writer = new FileWriter(file, false);
        gson.toJson(notes.getNotes(), writer); //brackets
    }
}
