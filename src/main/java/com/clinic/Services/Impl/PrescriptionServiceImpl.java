package com.clinic.services.impl;

import com.clinic.dao.PrescriptionRepository;
import com.clinic.models.Prescription;
import com.clinic.payloads.PrescriptionDto;
import com.clinic.services.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
        
    private final PrescriptionRepository prescriptionRepository;

    private final ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    // Service implemantation for Creating Prescription by id
    public PrescriptionDto createPrescription(PrescriptionDto prescription) {
        Prescription presc = dtoToPrescription(prescription);
        prescriptionRepository.save(presc);
        return prescriptionToDto(presc);
    }

    // Service implemantation for Getting Prescription by id
    public PrescriptionDto getPrescriptionById(int id) {
        return prescriptionToDto(prescriptionRepository.findById(id).isPresent() ? prescriptionRepository.findById(id).get() : null);
    }

    // Service for Getting all Prescriptions
    public List<PrescriptionDto> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        // Using Java 8 Streams to convert List of Prescription to List of PrescriptionDto
        // prescriptions.stream() - Convert List to Stream
        // map(prescriptionToDto) - Convert each element of a Stream to other object using prescriptionToDto method
        // collect(Collectors.toList()) - Collect the elements of the stream to a List
        return prescriptions.stream().map(this::prescriptionToDto).collect(Collectors.toList());
    }   

    // Service implemantation for Deleting Prescription by id
    public void deletePrescriptionById(int id) {
        prescriptionRepository.deleteById(id);
    }

    // Service implemantation for Updating Prescription by id
    public PrescriptionDto updatePrescriptionById(PrescriptionDto prescription, int id) {
        Prescription presc = dtoToPrescription(prescription);
        presc.setPrescriptionID(id);
        prescriptionRepository.save(presc);
        return prescriptionToDto(presc);
    }

    private PrescriptionDto prescriptionToDto(Prescription prescription) {
        return modelMapper.map(prescription, PrescriptionDto.class);
    }

    private Prescription dtoToPrescription(PrescriptionDto prescription) {
        return modelMapper.map(prescription, Prescription.class);
    }
}
