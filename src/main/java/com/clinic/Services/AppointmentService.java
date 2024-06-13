package com.clinic.services;

import java.util.List;

import com.clinic.payloads.AppointmentDto;

public interface AppointmentService {
    // Get all Appointments
    List<AppointmentDto> getAllAppointments();

    // Get one Appointment by id
    AppointmentDto getAppointmentById(int id);

    // Create new Appointment
    AppointmentDto createAppointment(AppointmentDto appointment);

    // Update Appointment by id
    AppointmentDto updateAppointmentById(AppointmentDto appointment, int id);

    // Delete Appointment by id
    void deleteAppointmentById(int id);

}
