package ru.nsu.belozerov;

import org.apache.commons.cli.*;

import java.io.IOException;

public class ConsoleReader {

    private final Options options;
    private final Notebook notes = new Notebook();

    public ConsoleReader() {
        Option help = Option.builder("help")
                .desc("Lets you see arguments descriptions")
                .build();

        Option add = Option.builder("add")
                .numberOfArgs(2)
                .desc("Provided with two arguments: \"Title of your note\" and \"Note itself\"")
                .optionalArg(false)
                .build();

        Option remove = Option.builder("rm")
                .numberOfArgs(1)
                .desc("Provided with one argument: \"Title of the note you want to delete\"")
                .optionalArg(false)
                .build();

        Option show = Option.builder("show")
                .desc("Provided with tree arguments: \"From date\", \"To date\", \"Key words for searching\"; or without args to show all the notes")
                .optionalArg(false)
                .build();

        options = new Options();
        options.addOption(add);
        options.addOption(remove);
        options.addOption(show);
        options.addOption(help);
    }

    public void run(String[] args) {
        CommandLine line;
        try {
            CommandLineParser parser = new DefaultParser();
            line = parser.parse(options, args);
            parseLine(line);
        } catch (ParseException | IOException | java.text.ParseException e) {
            System.out.println("Type -help for info");
        }
    }

    private void parseLine(CommandLine line) throws IOException, java.text.ParseException {
        JsonHandler json = new JsonHandler();
        json.openRead();
        notes.addAll(json.readJson());
        json.openWrite();

        if (line.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("notes.jar", options);
        }

        if (line.hasOption("add")) {
            String[] args = line.getOptionValues("add");
            notes.add(args[0], args[1]);
        }

        if (line.hasOption("rm")) {
            String[] args = line.getOptionValues("rm");
            notes.remove(args[0]);
        }

        if (line.hasOption("show")) {
            String[] args = line.getOptionValues("show");
            Note[] savedNotes;
            if (args == null) {
                savedNotes = notes.show();
            } else {
                savedNotes = notes.show(args[0], args[1], args[2]);
            }
            for (Note savedNote : savedNotes) {
                System.out.println(savedNote.toString());
            }
        }

        json.writeJson(notes);
        json.close();
    }
}

