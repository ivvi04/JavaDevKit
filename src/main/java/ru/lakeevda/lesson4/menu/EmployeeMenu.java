package ru.lakeevda.lesson4.menu;

import ru.lakeevda.lesson4.service.EmployeeService;

import java.util.InputMismatchException;

public class EmployeeMenu extends AbstractMenu {
    static EmployeeService employeeService = new EmployeeService();

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Вывести список сотрудников");
        System.out.println("   2   | Добавить сотрудника");
        System.out.println("   3   | Вывести сотрудника по табельному номеру");
        System.out.println("   4   | Вывести сотрудников по стажу");
        System.out.println("   5   | Вывести номера телефона сотрудников по имени");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(employeeService.getEmployeeRepository());
                case "2" -> employeeService.addEmployeeConsole();
                case "3" -> {
                    int employeePersonnelNumber;
                    while (true) {
                        try {
                            System.out.print("Введите табельный номер сотрудника: ");
                            employeePersonnelNumber = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат!");
                        }
                    }
                    System.out.println(employeeService.getEmployeeByPersonnelNumber(employeePersonnelNumber));
                }
                case "4" -> {
                    int employeeExperience;
                    while (true) {
                        try {
                            System.out.print("Введите стаж сотрудника: ");
                            employeeExperience = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат!");
                        }
                    }
                    System.out.println(employeeService.getEmployeeByExperience(employeeExperience));
                }
                case "5" -> {
                    String employeeFIO;
                    while (true) {
                        try {
                            System.out.print("Введите ФИО сотрудника: ");
                            employeeFIO = readMenu();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат!");
                        }
                    }
                    System.out.println(employeeService.getPhoneEmployeeByFIO(employeeFIO));
                }
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }

    }
}
