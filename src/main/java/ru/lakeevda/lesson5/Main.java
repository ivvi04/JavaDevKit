package ru.lakeevda.lesson5;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        String[] names = {"Денис", "Анна", "Андрей", "Елена", "Кристина"};
        Philosopher[] philosophers = new Philosopher[5];
        Semaphore table = new Semaphore(philosophers.length - 1);

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(names[i], table);
            new Thread(philosophers[i]).start();
        }
    }
}
