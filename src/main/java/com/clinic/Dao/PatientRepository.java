package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.Appointment;
import com.clinic.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
    // Get Appointment by Patient Id
    public List<Appointment> findByPatientId(int id); 
}
