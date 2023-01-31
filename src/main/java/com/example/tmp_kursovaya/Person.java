package com.example.tmp_kursovaya;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private final StringProperty FIO;
    private final StringProperty position;
    private final ObjectProperty<LocalDate> Date;
    private final StringProperty phone;
    private final StringProperty mobilePhone;
    private final StringProperty comments;

    public Person() {
        this.FIO = new SimpleStringProperty("");
        this.position = new SimpleStringProperty("");
        Date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1980, 11, 24));
        this.phone = new SimpleStringProperty("");
        this.mobilePhone = new SimpleStringProperty("");
        this.comments = new SimpleStringProperty("");
    }

    public String getFIO() {
        return FIO.get();
    }

    public String getPosition() {
        return position.get();
    }

    public String getComments() {
        return comments.get();
    }

    public String getMobilePhone() {
        return mobilePhone.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public LocalDate getDate() {
        return Date.get();
    }


    public StringProperty commentsProperty() {
        return comments;
    }

    public StringProperty FIOProperty() {
        return FIO;
    }

    public StringProperty positionProperty() {
        return position;
    }



    //@XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setFIO(String FIO) {
        this.FIO.set(FIO);
    }

    public void setDate(LocalDate date) {
        this.Date.set(date);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public void setPosition(String position) {
        this.position.set(position);
    }



    public ObjectProperty<LocalDate> dateProperty() {
        return Date;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty mobilePhoneProperty() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone.set(mobilePhone);
    }

}
