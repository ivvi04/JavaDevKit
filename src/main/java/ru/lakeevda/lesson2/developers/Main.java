package ru.lakeevda.lesson2.developers;

import ru.lakeevda.lesson2.developers.classes.Backend;
import ru.lakeevda.lesson2.developers.classes.Frontend;
import ru.lakeevda.lesson2.developers.classes.Fullstack;
import ru.lakeevda.lesson2.developers.interfaces.IDeveloper;

public class Main {
    public static void main(String[] args) {
        IDeveloper developer1 = new Fullstack();
        Backend developer2 = new Backend();
        Frontend developer3 = new Frontend();

        if (developer1 instanceof Fullstack) {
            ((Fullstack) developer1).backendDevelopment();
            ((Fullstack) developer1).frontendDevelopment();
        }

        developer2.backendDevelopment();

        developer3.frontendDevelopment();
    }
}
