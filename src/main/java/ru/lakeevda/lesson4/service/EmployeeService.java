package ru.lakeevda.lesson4.service;

import ru.lakeevda.lesson4.entity.Employee;
import ru.lakeevda.lesson4.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    public List<Employee> getEmployeeRepository() {
        return EmployeeRepository.getEmployeeRepository();
    }

    public List<Employee> getEmployeeByExperience (int experience) {
        return getEmployeeRepository().stream().filter(x -> x.getExperience() == experience).toList();
    }

    public List<String> getPhoneEmployeeByFIO (String fio) {
        List<Employee> employeeList = getEmployeeRepository();
        List<String> stringList = new ArrayList<>();
        employeeList.stream().filter(x -> x.getFio() == fio).toList().stream().forEach(x -> stringList.add(x.getPhone()));
        return stringList;
    }

    public Employee getEmployeeByPersonnelNumber (int personnelNumber) {
        List<Employee> employeeList = getEmployeeRepository().stream().filter(x -> x.getPersonnelNumber() == personnelNumber).toList();
        Employee employee = null;
        if (!employeeList.isEmpty()) employee = employeeList.get(0);
        return employee;
    }

    private Integer parseInt(Scanner scanner, String lineName) {
        Integer result = null;
        while (true) {
            System.out.printf("Введите %s:\n", lineName);
            try {
                result = Integer.parseInt(scanner.next());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат. Повторите ввод");
            }
        }
        return result;
    }

    public void addEmployeeConsole() {
        Scanner scanner = new Scanner(System.in);
        int personnelNumber = parseInt(scanner, "Табельный номер");
        System.out.println("Введите ФИО:");
        String fio = scanner.next();
        System.out.println("Введите Телефон:");
        String phone = scanner.next();
        int experience = parseInt(scanner, "Стаж");
        addEmployee(personnelNumber, fio, phone, experience);
    }

    public void addEmployee (int personnelNumber, String fio, String phone, int experience) {
        Employee employee = new Employee(personnelNumber, fio, phone, experience);
        addEmployeeToRepository(employee);
    }

    public void addEmployeeToRepository(Employee employee) {
        EmployeeRepository.addEmployee(employee);
    }
}
