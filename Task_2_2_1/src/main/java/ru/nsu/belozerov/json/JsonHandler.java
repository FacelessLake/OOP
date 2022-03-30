package ru.nsu.belozerov.json;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    public JsonPizzeria jsonHandle() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("PizzeriaData.json"));
        JsonPizzeria pizzeria = gson.fromJson(reader, JsonPizzeria.class);
        reader.close();
        return pizzeria;
    }
}
