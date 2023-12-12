package ru.lakeevda.lesson4;

import ru.lakeevda.lesson4.menu.EmployeeMenu;
import ru.lakeevda.lesson4.service.FileService;

public class Main {
    public static void main(String[] args) {
        try {
            readData();
            EmployeeMenu.viewMenu();
            writeData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readData() throws Exception {
        FileService writeReadService = new FileService();
        writeReadService.FileReader();
    }

    public static void writeData() throws Exception {
        FileService writeReadService = new FileService();
        writeReadService.FileWriter();
    }
}
