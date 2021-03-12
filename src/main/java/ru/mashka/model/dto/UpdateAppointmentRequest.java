package ru.mashka.model.dto;

import ru.mashka.model.entity.Appointment;
import ru.mashka.model.key.AppointmentKey;

public class UpdateAppointmentRequest {

    private Appointment appointment;
    private AppointmentKey appointmentKey;

    public UpdateAppointmentRequest() {
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public AppointmentKey getAppointmentKey() {
        return appointmentKey;
    }
}
