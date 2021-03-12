package ru.mashka.model.entity;

import ru.mashka.model.key.AppointmentKey;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@IdClass(AppointmentKey.class)
@Table(name = "appointments")
public class Appointment implements Cloneable {

    @Column(name = "client_name")
    private String clientName;

    @Id
    @Column(name = "date_start")
    private Instant appointmentStart;

    @Id
    @Column(name = "date_end")
    private Instant appointmentEnd;

    @Id
    @Column(name = "specialist_id")
    private int specialistId;

    public Appointment(String clientName, Instant appointmentStart, Instant appointmentEnd, int specialistId) {
        this.clientName = clientName;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.specialistId = specialistId;
    }

    public Appointment() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Instant getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(Instant appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public Instant getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Instant appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return specialistId == that.specialistId && Objects.equals(clientName, that.clientName) && Objects.equals(appointmentStart, that.appointmentStart) && Objects.equals(appointmentEnd, that.appointmentEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, appointmentStart, appointmentEnd, specialistId);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "clientName='" + clientName + '\'' +
                ", appointmentStart=" + appointmentStart +
                ", appointmentEnd=" + appointmentEnd +
                ", specialistId=" + specialistId +
                '}';
    }

    public AppointmentKey getAppointmentKey(Appointment appointment) {
        return new AppointmentKey(
                appointment.getAppointmentStart(), appointment.getSpecialistId());
    }

    @Override
    public Appointment clone() throws CloneNotSupportedException {
        return (Appointment) super.clone();
    }
}
