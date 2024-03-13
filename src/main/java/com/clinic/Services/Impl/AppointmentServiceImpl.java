package com.clinic.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.Dao.AppointmentRepository;
import com.clinic.Exceptions.ResourceNotFoundException;
import com.clinic.Models.Appointment;
import com.clinic.Payloads.AppointmentDto;
import com.clinic.Services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

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
        // System.out.println(newAppointment);
        Appointment savedAppointment = this.appointmentRepository.save(newAppointment);
        return appointmentToDto(savedAppointment);
    }

    // Service implementation for Updating Appointment by id
    @Override
    public AppointmentDto updateAppointmentById(AppointmentDto appointment, int id) {
        Appointment newAppointment = dtoToAppointment(appointment);
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
