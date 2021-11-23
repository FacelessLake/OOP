package ru.nsu.belozerov;

import org.apache.commons.cli.*;

import java.io.IOException;

public class ConsoleReader {

    private final Options options;
    private final Notebook notes = new Notebook();

    public ConsoleReader() {
        options = new Options();
        Option add = Option.builder("add")
                .numberOfArgs(2)
                .build();

        Option remove = Option.builder("remove")
                .hasArg()
                .build();

        Option show = Option.builder("show")
                .numberOfArgs(3)
                .build();

        options.addOption(add);
        options.addOption(remove);
        options.addOption(show);
    }

    public void run(String[] args) {
        CommandLine line;
        try {
            CommandLineParser parser = new DefaultParser();
            line = parser.parse(options, args);
            parseLine(line);
        } catch (ParseException | IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(CommandLine line) throws IOException, java.text.ParseException {
        JsonHandler json = new JsonHandler();
        json.open();

        if (line.hasOption("add")) {
            String[] args = line.getOptionValues("add");
            notes.uploadFromJson(json.readJson());
            notes.add(args[0], args[1]);
            json.writeJson(notes);
        }

        if (line.hasOption("remove")) {
            String[] args = line.getOptionValues("remove");
            notes.uploadFromJson(json.readJson());
            notes.remove(args[0]);
        }

        if (line.hasOption("show")) {
            String[] args = line.getOptionValues("show");
            Note[] savedNotes;
            if (args.length > 0) {
                savedNotes = notes.show(args[0], args[1], args[2]);
            } else {
                savedNotes = notes.show();
            }

            for (Note savedNote : savedNotes) {
                System.out.println(savedNote.toString());
            }
        }

        json.close();
    }
}

