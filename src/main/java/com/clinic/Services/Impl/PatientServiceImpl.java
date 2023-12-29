package com.clinic.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.Dao.PatientRepository;
import com.clinic.Models.Appointment;
import com.clinic.Models.Patient;
import com.clinic.Payloads.AppointmentDto;
import com.clinic.Payloads.PatientDto;
import com.clinic.Services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

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
        Patient p = patientRepository.findById(id).get();
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

    // Service implemantation for Getting all Appointments by Patient id
    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(int id) {
        List<Appointment> appointments = patientRepository.findByPatientId(id);
        return appointments.stream().map(this::appointmentToDto).collect(Collectors.toList());
    }

    private Patient dtoToPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }

    private PatientDto patientToDto(Patient patient) {
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }

    private AppointmentDto appointmentToDto(Appointment appointment) {
        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
        return appointmentDto;
    }
}
