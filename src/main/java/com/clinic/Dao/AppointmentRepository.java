package com.clinic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.Models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    
}
