package ru.mashka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.mashka.injection.InjectionExtension;
import ru.mashka.model.entity.Appointment;
import ru.mashka.model.key.AppointmentKey;
import ru.mashka.utils.DateUtils;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;

@ExtendWith(InjectionExtension.class)
public class AppointmentDaoTest {

    @Inject
    SessionFactory sessionFactory;

    @Inject
    AppointmentDao appointmentDao;

    @Test
    void testAppointment() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("09.03.2021 16:00");
            Instant instant2 = DateUtils.parse("09.03.2021 17:00");
            Instant instant3 = DateUtils.parse("18.03.2021 15:00");
            Instant instant4 = DateUtils.parse("18.03.2021 16:00");

            Appointment appointment1 = new Appointment("Kate", instant1, instant2, 1);
            appointmentDao.add(appointment1);
            Appointment appointment2 = new Appointment("Alina", instant3, instant4, 1);
            appointmentDao.add(appointment2);
            List<Appointment> between = appointmentDao.getBetween(1, instant1, instant4);

            Assertions.assertTrue(between.contains(appointment1), "ap1");
            Assertions.assertTrue(between.contains(appointment2), "ap2");
            tr.rollback();
        }
    }

    @Test
    void test2() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("12.03.2021 13:00");
            Instant instant2 = DateUtils.parse("12.03.2021 17:00");
            Instant instant3 = DateUtils.parse("12.03.2021 16:00");
            Instant instant4 = DateUtils.parse("12.03.2021 15:00");

            Appointment appointment = new Appointment("test2", instant1, instant2, 1);
            appointmentDao.add(appointment);

            List<Appointment> between = appointmentDao.getBetween(1, instant4, instant3);
            Assertions.assertTrue(between.contains(appointment));
            tr.rollback();
        }
    }

    @Test
    void test3() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("13.03.2021 13:00");
            Instant instant2 = DateUtils.parse("13.03.2021 17:00");
            Instant instant3 = DateUtils.parse("13.03.2021 18:00");
            Instant instant4 = DateUtils.parse("13.03.2021 19:00");

            Appointment appointment = new Appointment("test3", instant1, instant3, 1);
            appointmentDao.add(appointment);

            List<Appointment> between = appointmentDao.getBetween(1, instant2, instant4);
            Assertions.assertTrue(between.contains(appointment));
            tr.rollback();
        }
    }

    @Test
    void test4() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("14.03.2021 12:00");
            Instant instant2 = DateUtils.parse("14.03.2021 13:00");
            Instant instant3 = DateUtils.parse("14.03.2021 14:00");
            Instant instant4 = DateUtils.parse("14.03.2021 18:00");

            Appointment appointment = new Appointment("test4", instant1, instant3, 1);
            appointmentDao.add(appointment);

            List<Appointment> between = appointmentDao.getBetween(1, instant2, instant4);
            Assertions.assertTrue(between.contains(appointment));
            tr.rollback();
        }
    }

    @Test
    void test5() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();

            Instant instant1 = DateUtils.parse("15.03.2021 12:00");
            Instant instant2 = DateUtils.parse("15.03.2021 13:00");
            Instant instant3 = DateUtils.parse("15.03.2021 14:00");
            Instant instant4 = DateUtils.parse("15.03.2021 18:00");

            Appointment appointment = new Appointment("test5", instant2, instant3, 1);
            appointmentDao.add(appointment);

            List<Appointment> between = appointmentDao.getBetween(1, instant1, instant4);
            Assertions.assertTrue(between.contains(appointment));
            tr.rollback();
        }
    }

    @Test
    void test6() throws CloneNotSupportedException {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();

            Instant instant1 = DateUtils.parse("15.03.2021 12:00");
            Instant instant2 = DateUtils.parse("15.03.2021 13:00");
            Instant instant3 = DateUtils.parse("15.03.2021 14:00");
            Instant instant4 = DateUtils.parse("15.03.2021 18:00");

            Appointment appointment = new Appointment("test6", instant1, instant2, 1);
            appointmentDao.add(appointment);

            appointmentDao.getByClient("test6");

            Appointment cloneAppointment = appointment.clone();
            Assertions.assertEquals(appointment, cloneAppointment);

            AppointmentKey appointmentKey = appointment.getAppointmentKey(appointment);

            cloneAppointment.setClientName("test6 2.0");
            cloneAppointment.setAppointmentStart(instant3);
            cloneAppointment.setAppointmentEnd(instant4);

            appointmentDao.update(appointmentKey, cloneAppointment);

            appointmentDao.delete(appointmentKey);
            tr.rollback();
        }
    }
}
