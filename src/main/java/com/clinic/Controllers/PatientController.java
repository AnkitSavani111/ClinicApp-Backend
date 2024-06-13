package com.clinic.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.payloads.PatientDto;
import com.clinic.services.PatientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Controller for creating new Patient
    @PostMapping("/")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patient) {
        try {
            PatientDto patient2 = this.patientService.createPatient(patient);
            System.out.println(patient2);
            // return ResponseEntity.status(HttpStatus.CREATED).build();
            return ResponseEntity.ok().body(patient2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get one Patient by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable("id") int id) {
        PatientDto p = this.patientService.getPatientById(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(Optional.of(p));
        }
    }

    // Get all Patients
    @GetMapping("/")
    public ResponseEntity<?> getAllPatients() {
        List<PatientDto> patients = this.patientService.getAllPatients();

        if (patients.isEmpty()) { 
            Map<String, String> message = new HashMap<String, String>();
            message.put("message", "Patient not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(Optional.of(patients));
    }

    // Delete Patient by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("id") int id) {
        try {
            this.patientService.deletePatientById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update Patient by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatientById(@RequestBody PatientDto patient, @PathVariable("id") int id) {
        try {
            // PatientDto updatedPatient = this.patientService.updatePatientById(patient, id);
            // System.out.println(updatedPatient);
            return ResponseEntity.ok().body(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
