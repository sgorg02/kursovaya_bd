package com.example.tmp_kursovaya;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private String FIO;
    private String Date;
    private int phone;
    private String department;
    private String position;
    private String countries;
    private String address;

    public Person(String FIO, String date, int phone, String department, String position, String countries, String address) { //String countries, String address
        this.FIO = FIO;
        Date = date;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.countries = countries;
        this.address = address;
    }

    public String getFIO() {
        return FIO;
    }

    public String getDate() {
        return Date;
    }

    public int getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getCountries() {
        return countries;
    }

    public String getAddress() {
        return address;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public void setCountries(String countries) {
//        this.countries = countries;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}
