package ru.lakeevda.lesson3.array;

import java.util.Objects;

public class Array {
    public static <E> boolean compareArrays(E[] array1, E[] array2) {
        if (array1 == null) {
            throw new NullPointerException("First array");
        }
        if (array2 == null) {
            throw new NullPointerException("Second array");
        }
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; ++i) {
            if (!Objects.equals(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }
}
