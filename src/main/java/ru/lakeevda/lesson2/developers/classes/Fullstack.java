package ru.lakeevda.lesson2.developers.classes;

import ru.lakeevda.lesson2.developers.interfaces.IFullstack;

public class Fullstack implements IFullstack {
    @Override
    public void backendDevelopment() {
        System.out.println("Backend-development for Full Stack developer");
    }

    @Override
    public void frontendDevelopment() {
        System.out.println("Frontend-development for Full Stack developer");
    }
}
