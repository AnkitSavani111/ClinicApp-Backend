package com.clinic.payloads;

import java.util.Date;
import java.util.List;

import com.clinic.models.Patient;
import com.clinic.models.Prescription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private int appointmentId;

    private String status;

    private Date timestamp;

    private String diagnosis;

    private String treatment;

    private String remarks;

    private Patient patient;
    
    private List<Prescription> prescription;
}
