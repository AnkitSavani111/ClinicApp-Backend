package com.clinic.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date timestamp;

    private String diagnosis;
    
    private String treatment;
    
    private String remarks;

    
}
