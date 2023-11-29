package ru.lakeevda.lesson2.developers.classes;

import ru.lakeevda.lesson2.developers.interfaces.IFrontend;

public class Frontend implements IFrontend {
    @Override
    public void frontendDevelopment() {
        System.out.println("Frontend-development for Frontend-developer");
    }
}
