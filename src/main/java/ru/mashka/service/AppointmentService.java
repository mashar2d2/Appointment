package ru.mashka.service;

import ru.mashka.model.OpenAppointment;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface AppointmentService {
    List<OpenAppointment> getBySpec(int id, Instant from, Instant to);
}

