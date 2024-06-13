package com.clinic.services.impl;

import com.clinic.dao.AppointmentRepository;
import com.clinic.exceptions.ResourceNotFoundException;
import com.clinic.models.Appointment;
import com.clinic.payloads.AppointmentDto;
import com.clinic.services.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    // Service implementation for Getting all Appointments
    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = this.appointmentRepository.findAll();
        return appointments.stream().map(this::appointmentToDto).collect(Collectors.toList());
    }

    // Service implementation for Getting one Appointment by id
    @Override
    public AppointmentDto getAppointmentById(int id) {
        Appointment appointment = this.appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", String.valueOf(id)));
        return appointmentToDto(appointment);
    }

    // Service implementation for Creating new Appointment
    @Override
    public AppointmentDto createAppointment(AppointmentDto appointment) {
        Appointment newAppointment = dtoToAppointment(appointment);
        Appointment savedAppointment = this.appointmentRepository.save(newAppointment);
        return appointmentToDto(savedAppointment);
    }

    // Service implementation for Updating Appointment by id
    @Override
    public AppointmentDto updateAppointmentById(AppointmentDto appointment, int id) {
        Appointment newAppointment = dtoToAppointment(appointment);
        newAppointment.setAppointmentId(id);
        System.out.println(newAppointment);
        Appointment savedAppointment = this.appointmentRepository.save(newAppointment);
        return appointmentToDto(savedAppointment);
    }

    // Service implementation for Deleting Appointment by id
    @Override
    public void deleteAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", String.valueOf(id)));

        appointmentRepository.delete(appointment);
    }

    private AppointmentDto appointmentToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    private Appointment dtoToAppointment(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }
}
