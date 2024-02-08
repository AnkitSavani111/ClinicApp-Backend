package com.clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.Payloads.PrescriptionDto;
import com.clinic.Services.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    // Controller for creating new Prescription
    @PostMapping("/")
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto prescription) {
        try {
            PrescriptionDto prescription2 = this.prescriptionService.createPrescription(prescription);
            PrescriptionDto prescription3 = this.prescriptionService.getPrescriptionById(prescription2.getPrescriptionID());
            // return ResponseEntity.status(HttpStatus.CREATED).build();
            return ResponseEntity.ok().body(prescription3);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Controller for getting Prescription by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPrescriptionById(@PathVariable("id") int id) {
        try {
            PrescriptionDto prescription = this.prescriptionService.getPrescriptionById(id);
            return ResponseEntity.ok().body(prescription);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Controller for getting all Prescriptions
    @GetMapping("/")
    public ResponseEntity<?> getAllPrescriptions() {
        try {
            return ResponseEntity.ok().body(this.prescriptionService.getAllPrescriptions());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Controller for Deleting Prescription by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable("id") int id) {
        try {
            this.prescriptionService.deletePrescriptionById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Controller for Updating Prescription by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrescription(@RequestBody PrescriptionDto prescription, @PathVariable("id") int id) {
        try {
            PrescriptionDto prescription2 = this.prescriptionService.updatePrescriptionById(prescription, id);
            return ResponseEntity.ok().body(prescription2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
