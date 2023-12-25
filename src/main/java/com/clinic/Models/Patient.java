package com.clinic.Models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Patient {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Date date_registration;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String address;

    private int age;

    public Patient() {
    }

    public Patient(String name, String email, String phone, Date date_registration, String gender, String address,
            int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date_registration = date_registration;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(Date date_registration) {
        this.date_registration = date_registration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    

    


}
