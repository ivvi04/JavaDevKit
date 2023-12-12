package ru.lakeevda.lesson4.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    protected int id;
    protected int personnelNumber;
    protected String fio;
    protected String phone;
    protected int experience;
    private static int count;

    public Employee(int personnelNumber, String fio, String phone, int experience) {
        this.id = count++;
        this.personnelNumber = personnelNumber;
        this.fio = fio;
        this.phone = phone;
        this.experience = experience;
    }

    public void setId(int id) {
        this.id = id;
        if (id >= count) count = ++id;
    }
}
