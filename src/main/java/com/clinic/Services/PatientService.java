package com.clinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.Dao.PatientRepository;
import com.clinic.Models.Patient;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    // Service for creating new Patient
    public Patient createPatient(Patient patient) {
        Patient p = patientRepository.save(patient);
        return p;
    }

    // Service for Getting one Patient by id
    public Patient getPatientById(int id) {
        Patient p = patientRepository.findById(id).get();
        return p;
    }

    // Get all Patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    // Delete Patient by id
    public void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

}
