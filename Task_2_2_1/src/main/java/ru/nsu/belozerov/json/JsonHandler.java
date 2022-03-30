package ru.nsu.belozerov.json;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that is responsible for work with json
 */
public class JsonHandler {
    /**
     * Find file with appropriate name and takes some date from it
     *
     * @return structure that contains all necessary parameters to make the pizzeria work
     * @throws IOException - thrown if problems with reader occurs
     */
    public JsonPizzeria jsonHandle() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("PizzeriaData.json"));
        JsonPizzeria pizzeria = gson.fromJson(reader, JsonPizzeria.class);
        reader.close();
        return pizzeria;
    }
}
