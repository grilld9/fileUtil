package org.example;

import static java.lang.System.exit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final Statistic statistic;
    private final Files files;

    public FileManager(Statistic statistic) {
        this.statistic = statistic;
        this.files = new Files(new ArrayList<>());
    }

    public Files init(List<String> fileNames) {
        initInputFiles(fileNames, files);
        files.setIntegers(new File("integers.txt"));
        files.setFloats(new File("floats.txt"));
        files.setStrings(new File("strings.txt"));
        return files;
    }

    public Files init(List<String> fileNames, String path) {
        initInputFiles(fileNames, files);
        files.setIntegers(new File(path + "\\integers.txt"));
        files.setFloats(new File(path + "\\floats.txt"));
        files.setStrings(new File(path + "\\strings.txt"));
        return files;
    }

    public Files initWithPrefix(List<String> fileNames, String prefix) {
        initInputFiles(fileNames, files);
        files.setIntegers(new File(prefix + "integers.txt"));
        files.setFloats(new File(prefix + "floats.txt"));
        files.setStrings(new File(prefix + "strings.txt"));
        return files;
    }
    public Files initWithPrefix(List<String> fileNames, String prefix, String path) {
        initInputFiles(fileNames, files);
        files.setIntegers(new File(path + "\\" + prefix + "integers.txt"));
        files.setFloats(new File(path + "\\" + prefix + "floats.txt"));
        files.setStrings(new File(path + "\\" + prefix + "strings.txt"));
        return files;
    }

    private void initInputFiles(List<String> fileNames, Files files) {
        for (String fileName : fileNames) {
            File file = new File(fileName);
            try {
                if (file.canRead()) {
                    files.getInputFiles().add(file);
                } else {
                    statistic.addException(new Exception("Не получилось прочитать файл " + fileName));
                }
            } catch (SecurityException se) {
                statistic.addException(se);
            }
        }
        if (files.getInputFiles().isEmpty()) {
            System.out.println("Нет доступных для чтения файлов");
            exit(1);
        }
    }
}