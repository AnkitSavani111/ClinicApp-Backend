package com.clinic.services.impl;

import com.clinic.dao.PatientRepository;
import com.clinic.exceptions.ResourceNotFoundException;
import com.clinic.models.Patient;
import com.clinic.payloads.PatientDto;
import com.clinic.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    // Service implemantation for creating new Patient
    @Override
    public PatientDto createPatient(PatientDto patient) {
        Patient p = dtoToPatient(patient);
        Patient savedPatient = patientRepository.save(p);
        return this.patientToDto(savedPatient);
    }

    // Service implemantation for Getting one Patient by id
    @Override
    public PatientDto getPatientById(int id) {
        Patient p = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", String.valueOf(id)));
        return this.patientToDto(p);
    }

    // Service implemantation for Getting all Patients
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::patientToDto).collect(Collectors.toList());
    }

    // Service implemantation for Deleting Patient by id
    @Override
    public void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

    // Service implemantation for Updating Patient by id
    @Override
    public PatientDto updatePatientById(PatientDto patient, int id) {
        Patient p = dtoToPatient(patient);
        Patient savedPatient = patientRepository.save(p);
        return this.patientToDto(savedPatient);
    }

    private Patient dtoToPatient(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    private PatientDto patientToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

}
