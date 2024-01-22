package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class FileParser {

    private final Statistic statistic;
    private final Files files;
    private final Printer printer;

    public FileParser(Statistic statistic, Files files, Printer printer) {
        this.statistic = statistic;
        this.files = files;
        this.printer = printer;
    }

    public void run() {
        for (File file : files.getInputFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String curr;
                while ((curr = reader.readLine()) != null) {
                    try {
                        BigInteger bigInteger = new BigInteger(curr);
                        printer.print(bigInteger);
                    } catch (NumberFormatException biNfe) {
                        try {
                            BigDecimal bigDecimal = new BigDecimal(curr);
                            printer.print(bigDecimal);
                        } catch (NumberFormatException bdNfe) {
                            printer.print(curr);
                        }
                    }
                }
            } catch (IOException e) {
                statistic.addException(e);
            }
        }
    }
}
