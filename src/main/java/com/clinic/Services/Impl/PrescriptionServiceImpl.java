package com.clinic.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.Dao.PrescriptionRepository;
import com.clinic.Models.Prescription;
import com.clinic.Payloads.PrescriptionDto;
import com.clinic.Services.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
        
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ModelMapper modelMapper;

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
