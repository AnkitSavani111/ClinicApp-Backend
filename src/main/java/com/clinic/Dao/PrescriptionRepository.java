package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
    
}
