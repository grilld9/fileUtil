package org.example;

public class Main {

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser();
        commandLineParser.parse(args);
        Statistic statistic = new Statistic(commandLineParser.isFullStatisticMod());
        FileManager fileManager = new FileManager(statistic);
        Files files;
        if (commandLineParser.getPath() != null && commandLineParser.getPrefix() != null) {
            files = fileManager.initWithPrefix(commandLineParser.getFileNames(),
                commandLineParser.getPrefix(),
                commandLineParser.getPath());
        } else if (commandLineParser.getPrefix() != null) {
            files = fileManager.initWithPrefix(commandLineParser.getFileNames(),
                commandLineParser.getPrefix());
        } else if (commandLineParser.getPath() != null) {
            files = fileManager.init(commandLineParser.getFileNames(),
                commandLineParser.getPath());
        } else {
            files = fileManager.init(commandLineParser.getFileNames());
        }
        Printer printer = new Printer(files, commandLineParser.isAppendMod() ,statistic);
        FileParser fileParser = new FileParser(statistic, files, printer);
        fileParser.run();
        statistic.showStatistic();
    }
}
