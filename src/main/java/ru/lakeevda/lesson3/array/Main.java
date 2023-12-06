package ru.lakeevda.lesson3.array;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] array1 = {1, 2, 3, 4, 5};
        Integer[] array2 = {1, 2, 3, 4, 5};
        Integer[] array3 = {6, 7, 8, 9};

        System.out.println("Массивы: ");
        System.out.println("1: " + Arrays.toString(array1));
        System.out.println("2: " + Arrays.toString(array2));
        System.out.println("3: " + Arrays.toString(array3));
        System.out.println(
                Array.compareArrays(array1, array2)
                        ? "Массивы 1 и 2 одинаковые"
                        : "Массивы 1 и 2 различаются");
        System.out.println(
                Array.compareArrays(array1, array3)
                        ? "Массивы 1 и 3 одинаковые"
                        : "Массивы 1 и 3 различаются");
    }
}
