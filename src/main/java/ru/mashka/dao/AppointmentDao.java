package ru.mashka.dao;

import ru.mashka.model.entity.Appointment;
import ru.mashka.model.key.AppointmentKey;

import java.time.Instant;
import java.util.List;

public interface AppointmentDao {

    List<Appointment> getBetween(int id, Instant from, Instant to);

    void add(Appointment appointment);

    void delete(AppointmentKey appointmentKey);

    void update(AppointmentKey appointmentKey, Appointment appointment);

    List<Appointment> getByClient(String name);
}
