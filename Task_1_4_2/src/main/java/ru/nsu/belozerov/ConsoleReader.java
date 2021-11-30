package ru.nsu.belozerov;

import org.apache.commons.cli.*;

import java.io.IOException;

public class ConsoleReader {

    private final Options options;
    private final Notebook notes = new Notebook();

    public ConsoleReader() {
        Option add = Option.builder("add")
                .numberOfArgs(2)
                .optionalArg(false)
                .build();

        Option remove = Option.builder("rm")
                .hasArg()
                .optionalArg(false)
                .build();

        Option show = Option.builder("show")
                .optionalArg(false)
                .build();

        options = new Options();
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
        json.openRead();
        notes.addAll(json.readJson());
        json.openWrite();

        if (line.hasOption("add")) {
            String[] args = line.getOptionValues("add");
            notes.add(args[0], args[1]);
            json.writeJson(notes);
        }

        if (line.hasOption("rm")) {
            String[] args = line.getOptionValues("rm");
            notes.remove(args[0]); //should delete right things
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

