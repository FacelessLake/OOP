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

/**
 * This class is responsible for work with files
 */
public class JsonHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString(), formatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(formatter.format(src)))
            .setPrettyPrinting()
            .create();

    private final File file;
    private Reader reader = null;
    private FileWriter writer = null;


    /**
     * Takes the name of the file
     *
     * @param filename - the name of the file that will be used
     */
    public JsonHandler(File filename) {
        file = filename;
    }


    /**
     * Opens file for reading
     *
     * @throws IOException - will be thrown if something will go wrong with file
     */
    public void openRead() throws IOException {
        file.createNewFile();
        reader = Files.newBufferedReader(file.toPath());
    }


    /**
     * Opens file for writing
     *
     * @throws IOException - will be thrown if something will go wrong with file
     */
    public void openWrite() throws IOException {
        writer = new FileWriter(file, false);
    }


    /**
     * Closes file for both reading and writing
     *
     * @throws IOException - will be thrown if something will go wrong with file
     */
    public void close() throws IOException {
        reader.close();
        writer.close();
    }


    /**
     * Read from .json file
     *
     * @return - array of the notes
     */
    public Note[] readJson() {
        return gson.fromJson(reader, Note[].class);
    }


    /**
     * Write into the .json file
     *
     * @param notes - notes that will be writen
     * @throws IOException - will be thrown if something will go wrong with file
     */
    public void writeJson(Notebook notes) throws IOException {
        writer = new FileWriter(file, false);
        gson.toJson(notes.getNotes(), writer);
    }
}