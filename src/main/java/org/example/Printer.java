package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Printer {

    private BufferedWriter integersWriter;
    private BufferedWriter decimalsWriter;
    private BufferedWriter stringsWriter;
    private final Statistic statistic;
    private final Files files;

    private boolean integersCreated = false;
    private boolean integersWriteFail = false;
    private boolean decimalsCreated = false;
    private boolean decimalsWriteFail = false;
    private boolean stringsCreated = false;
    private boolean stringsWriteFail = false;

    private final boolean appendMod;


    public Printer(Files files, Boolean appendMod,Statistic statistic) {
        this.statistic = statistic;
        this.appendMod = appendMod;
        this.files = files;
    }

    public void print(BigInteger integer) {
        if (!integersWriteFail){
            if (!integersCreated) {
                try {
                    integersWriter = new BufferedWriter(new FileWriter(files.getIntegers(), appendMod));
                    integersCreated = true;
                } catch (SecurityException | IOException e) {
                    statistic.addException(e);
                    integersWriteFail = true;
                }
            }
            try {
                integersWriter.write(integer.toString() + "\n");
                integersWriter.flush();
                statistic.addInteger(integer);
            } catch (IOException e) {
                statistic.addException(e);
            }
        }
    }

    public void print(BigDecimal decimal) {
        if (!decimalsWriteFail) {
            if (!decimalsCreated) {
                try {
                    decimalsWriter = new BufferedWriter(new FileWriter(files.getFloats(), appendMod));
                    decimalsCreated = true;
                } catch (SecurityException | IOException e) {
                    statistic.addException(e);
                    decimalsWriteFail = true;
                }
            }
            try {
                decimalsWriter.write(decimal.toString() + "\n");
                decimalsWriter.flush();
                statistic.addDecimal(decimal);
            } catch (IOException e) {
                statistic.addException(e);
            }
        }
    }

    public void print(String string) {
        if (!stringsWriteFail) {
            if (!stringsCreated) {
                try {
                    stringsWriter = new BufferedWriter(new FileWriter(files.getStrings(), appendMod));
                    stringsCreated = true;
                } catch (SecurityException | IOException e) {
                    statistic.addException(e);
                    stringsWriteFail = true;
                }
            }
            try {
                stringsWriter.write(string + "\n");
                stringsWriter.flush();
                statistic.addString(string);
            } catch (IOException e) {
                statistic.addException(e);
            }
        }
    }
}
