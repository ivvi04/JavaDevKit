package ru.lakeevda.lesson3.calculator;

public class Main {
    public static void main(String[] args) {
        Number result;
        Integer aInteger = 20;
        Float aFloat = 35.65f;
        Byte aByte = 6;
        Short aShort = 12456;
        Double aDouble = 654.345654;
        Long aLong = 23L;

        result = Calculator.sum(aInteger, aFloat);
        System.out.printf("Результат после сложения как Float = %s\n", result.floatValue());
        result = Calculator.subtract(aShort, aDouble);
        System.out.printf("Результат после вычитания как Double = %s\n", result.doubleValue());
        result = Calculator.multiply(aByte, aShort);
        System.out.printf("Результат после умножения как Integer = %s\n", result.intValue());
        result = Calculator.divide(aDouble, aLong);
        System.out.printf("Результат после деления как Long = %s\n", result.longValue());
    }
}
