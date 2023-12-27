package ru.lakeevda.lesson6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random random;
    static Map<Integer, Boolean> resultFirstPlayer;
    static Map<Integer, Boolean> resultSecondPlayer;
    static int doors;
    static int counts;

    public static void main(String[] args) {
        random = new Random();
        resultFirstPlayer = new HashMap<>();
        resultSecondPlayer = new HashMap<>();
        doors = 3;
        counts = 1000;

        for (int i = 0; i < counts; i++) playerSelections(i);

        int countWin = 0;
        for (Map.Entry<Integer, Boolean> entry : resultFirstPlayer.entrySet()) {
            if (entry.getValue()) countWin++;
        }
        System.out.println("Игрок, не меняющий свой выбор. Побед: " + countWin);

        countWin = 0;
        for (Map.Entry<Integer, Boolean> entry : resultSecondPlayer.entrySet()) {
            if (entry.getValue()) countWin++;
        }
        System.out.println("Игрок, изменяющего свой выбор. Побед: " + countWin);
    }

    private static void playerSelections(int round) {
        int success = random.nextInt(doors);
        int first = random.nextInt(doors);
        int second = -1;
        int openDoor = -1;

        for (int i = 0; i < doors; i++) {
            if (i != success && i != first) openDoor = i;
        }

        for (int i = 0; i < doors; i++) {
            if (i != openDoor && i != first) second = first;
        }
        resultFirstPlayer.put(round, success == second);

        for (int i = 0; i < doors; i++) {
            if (i != openDoor && i != first) second = i;
        }
        resultSecondPlayer.put(round, success == second);
    }
}
