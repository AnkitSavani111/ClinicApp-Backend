package com.clinic.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.clinic.Payloads.AppointmentDto;
import com.clinic.Services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Get All Appointment
    @GetMapping("/")
    public ResponseEntity<?> getAllAppointments() {
        List<AppointmentDto> appointments = this.appointmentService.getAllAppointments();
        if (appointments.size() <= 0) {
            Map<String, String> message = new HashMap<String, String>();
            message.put("message", "Appointment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.of(Optional.of(appointments));
    }

    // Get one Appointment by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") int id) {
        AppointmentDto appointmentById = this.appointmentService.getAppointmentById(id);
        if (appointmentById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(appointmentById));
        }
    }

    // Create new Appointment
    @PostMapping("/")
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointment) {
        try {
            AppointmentDto appointment2 = this.appointmentService.createAppointment(appointment);
            System.out.println(appointment2);
            return ResponseEntity.status(HttpStatus.CREATED).build();
            // return ResponseEntity.ok().body(appointment2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Detele Appointment by id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") int id) {
        try {
            this.appointmentService.deleteAppointmentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update Appointment
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody AppointmentDto appointment,
            @PathVariable("id") int id) {
        try {
            AppointmentDto appointment2 = this.appointmentService.updateAppointmentById(appointment, id);
            return ResponseEntity.ok().body(appointment2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
