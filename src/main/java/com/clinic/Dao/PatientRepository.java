package com.clinic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.Models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
    
}
