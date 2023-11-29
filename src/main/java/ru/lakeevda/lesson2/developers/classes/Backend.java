package ru.lakeevda.lesson2.developers.classes;

import ru.lakeevda.lesson2.developers.interfaces.IBackend;

public class Backend implements IBackend {
    @Override
    public void backendDevelopment() {
        System.out.println("Backend-development for Backend-developer");
    }
}
