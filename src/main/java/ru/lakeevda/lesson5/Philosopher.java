package ru.lakeevda.lesson5;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int id;
    private final String name;
    private final Semaphore table;
    static private int count = 0;

    public Philosopher(String name, Semaphore table) {
        this.id = count++;
        this.name = name;
        this.table = table;
    }

    private void think() throws InterruptedException {
        System.out.println("Филосов № " + id + " " + name + " думает");
        Thread.sleep(500);
    }

    private void lunch() throws InterruptedException {
        System.out.println("Филосов № " + id + " " + name + " обедает");
        Thread.sleep(500);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                table.acquire();
                lunch();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                table.release();
            }
        }
    }
}
