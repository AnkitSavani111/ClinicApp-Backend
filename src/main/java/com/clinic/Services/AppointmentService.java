package com.clinic.Services;

import java.util.List;

import com.clinic.Payloads.AppointmentDto;

public interface AppointmentService {
    // Get all Appointments
    public List<AppointmentDto> getAllAppointments();

    // Get one Appointment by id
    public AppointmentDto getAppointmentById(int id);

    // Create new Appointment
    public AppointmentDto createAppointment(AppointmentDto appointment);

    // Update Appointment by id
    public AppointmentDto updateAppointmentById(AppointmentDto appointment, int id);

    // Delete Appointment by id
    public void deleteAppointmentById(int id);

}
