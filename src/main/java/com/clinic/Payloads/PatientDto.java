package com.clinic.Payloads;

import java.util.Date;
import java.util.List;

import com.clinic.Models.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private int patientId;

    private String name;

    private String email;

    private String phone;

    private Date date_registration;

    private String gender;

    private String address;

    private int age;

    private List<Appointment> appointments;
}
