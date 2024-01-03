package com.clinic.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prescriptionID;

    private String name;

    private int day;

    private boolean morning;

    private boolean afternoon;
    
    private boolean night;

    private boolean afterMedication;

    private boolean beforeMedication;

    // @JsonManagedReference
    @ManyToOne
    private Appointment appointment;
}
