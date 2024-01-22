package org.example;

import java.io.File;
import java.util.List;

public class Files {

    private final List<File> inputFiles;

    public File getIntegers() {
        return integers;
    }

    public File getFloats() {
        return floats;
    }

    public File getStrings() {
        return strings;
    }

    public void setIntegers(File integers) {
        this.integers = integers;
    }

    public void setFloats(File floats) {
        this.floats = floats;
    }

    public void setStrings(File strings) {
        this.strings = strings;
    }

    private File integers;
    private File floats;
    private File strings;
    public Files(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }


}
