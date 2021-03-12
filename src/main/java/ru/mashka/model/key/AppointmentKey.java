package ru.mashka.model.key;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class AppointmentKey implements Serializable {

    protected Instant appointmentStart;
    protected int specialistId;

    public Instant getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(Instant appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public AppointmentKey(Instant appointmentStart, int specialistId) {
        this.appointmentStart = appointmentStart;
        this.specialistId = specialistId;
    }

    public AppointmentKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentKey that = (AppointmentKey) o;
        return specialistId == that.specialistId
                && Objects.equals(appointmentStart, that.appointmentStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentStart, specialistId);
    }

    @Override
    public String toString() {
        return "AppointmentKey{" +
                "appointmentStart=" + appointmentStart +
                ", specialistId=" + specialistId +
                '}';
    }
}
