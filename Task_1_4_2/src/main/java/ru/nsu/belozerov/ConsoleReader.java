package ru.nsu.belozerov;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class ConsoleReader {

    private final Options options;

    public ConsoleReader() {
        Option name = Option.builder("name")
                .desc("Lets you to make a note with the specified name")
                .numberOfArgs(1)
                .argName("file_name")
                .optionalArg(false)
                .build();

        Option help = Option.builder("help")
                .desc("Lets you see arguments descriptions")
                .build();

        Option add = Option.builder("add")
                .numberOfArgs(2)
                .argName("heading> <text")
                .desc("Provided with two arguments: \"Title of your note\" and \"Note itself\"")
                .optionalArg(false)
                .build();

        Option remove = Option.builder("rm")
                .numberOfArgs(1)
                .argName("heading")
                .desc("Provided with one argument: \"Title of the note you want to delete\"")
                .optionalArg(false)
                .build();

        Option show = Option.builder("show")
                .desc("Provided with tree arguments: \"From date\", \"To date\", \"Key words for searching\";" +
                        " or without args to show all the notes.\n <Date format: dd.MM.yyyy HH:mm:ss>")
                .optionalArg(false)
                .build();

        options = new Options();
        options.addOption(add);
        options.addOption(remove);
        options.addOption(show);
        options.addOption(help);
        options.addOption(name);
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
        File filename = new File("notes.json");
        if (line.hasOption("name")) {
            String[] args = line.getOptionValues("name");
            filename = new File(args[0]);
        }

        Notebook notes = new Notebook();
        JsonHandler json = new JsonHandler(filename);
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