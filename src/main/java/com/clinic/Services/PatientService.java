package com.clinic.Services;

import com.clinic.Payloads.PatientDto;

import java.util.List;

public interface PatientService {

    // Service for creating new Patient
    PatientDto createPatient(PatientDto patient);

    // Service for Getting one Patient by id
    PatientDto getPatientById(int id);

    // Get all Patients
    List<PatientDto> getAllPatients();

    // Delete Patient by id
    void deletePatientById(int id);

    // Update Patient by id
    PatientDto updatePatientById(PatientDto patient, int id);


}
