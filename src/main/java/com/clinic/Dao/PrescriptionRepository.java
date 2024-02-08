package com.clinic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.Models.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
    
}
