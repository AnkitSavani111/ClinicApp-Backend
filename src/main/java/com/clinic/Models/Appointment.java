package com.clinic.Models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date timestamp;

    private String diagnosis;

    private String treatment;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonBackReference
    private Patient patient;

    // @JsonBackReference
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Prescription> prescription;
}
