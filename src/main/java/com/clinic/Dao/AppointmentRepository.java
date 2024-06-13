package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    
}
