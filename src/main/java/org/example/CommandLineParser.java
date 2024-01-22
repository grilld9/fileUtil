package org.example;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {

    public CommandLineParser() {
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPath() {
        return path;
    }

    public boolean isFullStatisticMod() {
        return fullStatisticMod;
    }

    public boolean isAppendMod() {
        return appendMod;
    }

    private String prefix;
    private String path;

    public List<String> getFileNames() {
        return fileNames;
    }

    private List<String> fileNames;
    private boolean fullStatisticMod = false;
    private boolean appendMod = false;

    public void parse(String[] args) {
        boolean fileNamesParse = false;
        for (int i = 0; i < args.length; i++) {
            if (fileNamesParse) {
                fileNames.add(args[i]);
            } else {
                if (args[i].equals("-o")) {
                    i++;
                    path = args[i];
                } else if (args[i].equals("-p")) {
                    i++;
                    prefix = args[i];
                } else if (args[i].equals("-a")) {
                    appendMod = true;
                } else if (args[i].equals("-f")) {
                    fullStatisticMod = true;
                } else if (args[i].equals("-s")) {
                    fullStatisticMod = false;
                } else {
                    fileNamesParse = true;
                    fileNames = new ArrayList<>();
                    fileNames.add(args[i]);
                }
            }
        }
    }
}
