package ru.mashka.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.mashka.dao.AppointmentDao;
import ru.mashka.model.entity.Appointment;
import ru.mashka.model.key.AppointmentKey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.List;

@Singleton
public class AppointmentDaoImpl implements AppointmentDao {

    private final SessionFactory sessionFactory;

    @Inject
    public AppointmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Appointment> getBetween(int id, Instant from, Instant to) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Appointment where specialistId = :id " +
                        "OR :from between appointmentStart and appointmentEnd " +
                        "OR :to between appointmentStart and appointmentEnd " +
                        "OR (appointmentStart between :from and :to and " +
                        " appointmentStart between :from and :to) ",
                Appointment.class)
                .setParameter("id", id)
                .setParameter("from", from, TemporalType.TIMESTAMP)
                .setParameter("to", to, TemporalType.TIMESTAMP)
                .list();
    }

    @Override
    public void add(Appointment appointment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(appointment);
    }

    @Override
    public void delete(AppointmentKey appointmentKey) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Appointment where specialistId = :specId AND " +
                " appointmentStart = :appointmentStart ");
        query.setParameter("specId", appointmentKey.getSpecialistId());
        query.setParameter("appointmentStart", appointmentKey.getAppointmentStart());
        query.executeUpdate();
    }

    @Override
    public void update(AppointmentKey appointmentKey, Appointment appointment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Appointment set  " +
                " clientName = :newClientName,appointmentStart = :start,  appointmentEnd = :end " +
                " where appointmentStart = :appointmentStartKey " +
                " and specialistId = :specialistIdKey");
        query.setParameter("newClientName", appointment.getClientName());
        query.setParameter("start", appointment.getAppointmentStart());
        query.setParameter("end", appointment.getAppointmentEnd());
        query.setParameter("appointmentStartKey", appointmentKey.getAppointmentStart());
        query.setParameter("specialistIdKey", appointmentKey.getSpecialistId());

        query.executeUpdate();
    }

    @Override
    public List<Appointment> getByClient(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Appointment WHERE clientName = :name ",
                Appointment.class)
                .setParameter("name", name)
                .list();
    }
}
