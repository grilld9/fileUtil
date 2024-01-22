package org.example;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Statistic {

    private final Boolean fullStatisticMod;
    public Statistic(Boolean fullStatisticMod) {
        this.fullStatisticMod = fullStatisticMod;
    }

    private BigInteger maxInteger;
    private BigInteger minInteger;
    private BigDecimal averageInteger;
    private BigInteger integerSum = BigInteger.valueOf(0);
    private BigInteger integerCount = BigInteger.valueOf(0);


    private BigDecimal maxDecimal;
    private BigDecimal minDecimal;
    private BigDecimal averageDecimal;
    private BigDecimal decimalSum = BigDecimal.valueOf(0);
    private BigInteger decimalCount = BigInteger.valueOf(0);

    private BigInteger maxStringLen;
    private BigInteger minStringLen;
    private BigInteger stringCount = BigInteger.valueOf(0);

    private List<Exception> exceptions = new ArrayList<>();

    public void addString(String string) {
        BigInteger strLen = BigInteger.valueOf(string.length());
        if (maxStringLen == null || maxStringLen.compareTo(strLen) < 0) {
            maxStringLen = strLen;
        }
        if (minStringLen == null || minStringLen.compareTo(strLen) > 0) {
            minStringLen = strLen;
        }
        stringCount = stringCount.add(BigInteger.valueOf(1));
    }

    public void addDecimal(BigDecimal number) {
        if (maxDecimal == null || number.compareTo(maxDecimal) > 0) {
            maxDecimal = number;
        }
        if (minDecimal == null || number.compareTo(minDecimal) < 0) {
            minDecimal = number;
        }
        decimalCount = decimalCount.add(BigInteger.valueOf(1));
        decimalSum = decimalSum.add(number);
    }

    public void addInteger(BigInteger number) {
        if (maxInteger == null || number.compareTo(maxInteger) > 0) {
            maxInteger = number;
        }
        if (minInteger == null || number.compareTo(minInteger) < 0) {
            minInteger = number;
        }
        integerCount = integerCount.add(BigInteger.valueOf(1));
        integerSum = integerSum.add(number);
    }

    public void addException(Exception e) {
        exceptions.add(e);
    }

    public void showStatistic() {
        System.out.println("Статистика");
        System.out.println("Записано целых чисел: " + integerCount);
        System.out.println("Записано вещественных чисел: " + decimalCount);
        System.out.println("Записано строк: " + stringCount);
        if (fullStatisticMod) {
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
            System.out.println("Целые числа");
            System.out.println("Максимальное значение " + maxInteger);
            System.out.println("Минимальное значение " + minInteger);
            if (integerCount.compareTo(BigInteger.valueOf(0)) > 0) {
                averageInteger = new BigDecimal(integerSum.divide(integerCount).toString());
                System.out.println("Среднее значение " + averageInteger);
            } else {
                System.out.println("Средние значение не определено");
            }
            System.out.println("Сумма " + integerSum);
            System.out.println();

            System.out.println("Вещественные числа");
            System.out.println("Максимальное значение " + maxDecimal);
            System.out.println("Минимальное значение " + minDecimal);
            if (decimalCount.compareTo(BigInteger.valueOf(0)) > 0) {
                averageDecimal = decimalSum.divide(new BigDecimal(decimalCount.toString()), RoundingMode.CEILING);
                System.out.println("Среднее значение " + averageDecimal);
            } else {
                System.out.println("Средние значение не определено");
            }
            System.out.println("Сумма " + decimalSum);
            System.out.println();

            System.out.println("Строки");
            System.out.println("Размер самой длинной " + maxStringLen);
            System.out.println("Размер самой короткой " + minStringLen);
        }
        if (!exceptions.isEmpty()) {
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
            System.out.println("Исключения: ");
            for (Exception exception : exceptions) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
