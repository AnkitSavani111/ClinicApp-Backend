package com.clinic.services;

import java.util.List;

import com.clinic.payloads.PrescriptionDto;

public interface PrescriptionService {
    PrescriptionDto createPrescription(PrescriptionDto prescription);

    PrescriptionDto getPrescriptionById(int id);

    List<PrescriptionDto> getAllPrescriptions();

    void deletePrescriptionById(int id);

    PrescriptionDto updatePrescriptionById(PrescriptionDto prescription, int id);

}
