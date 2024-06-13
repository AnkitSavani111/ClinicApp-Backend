package com.clinic.payloads;

import com.clinic.models.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto {
    private int prescriptionID;

    private String name;

    private int day;

    private boolean morning;

    private boolean afternoon;
    
    private boolean night;

    private boolean afterMedication;

    private boolean beforeMedication;

    private Appointment appointment;
}
