package com.clinic.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.clinic.Models.Patient;
import com.clinic.Payloads.PatientDto;
import com.clinic.Services.PatientService;
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
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get one Patient by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") int id) {
        PatientDto p = this.patientService.getPatientById(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(p));
        }
    }

    // Get all Patients
    @GetMapping("/")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = this.patientService.getAllPatients();
        
            if (patients.size() <= 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        return ResponseEntity.of(Optional.of(patients));
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
    public ResponseEntity<PatientDto> updatePatientById(@RequestBody PatientDto patient, @PathVariable("id") int id) {
        try {
            PatientDto updatedPatient = this.patientService.updatePatientById(patient, id);
            // System.out.println(updatedPatient);
            return ResponseEntity.ok().body(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
