package ru.mashka.service;

import ru.mashka.dao.AppointmentDao;
import ru.mashka.dao.SpecWorkTimeDao;
import ru.mashka.model.entity.Appointment;
import ru.mashka.model.OpenAppointment;
import ru.mashka.model.entity.SpecWorkTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.*;

@Singleton
public class AppointmentServiceImpl implements AppointmentService {

    private final SpecWorkTimeDao specWorkTimeDao;
    private final AppointmentDao appointmentDao;

    @Inject
    public AppointmentServiceImpl(SpecWorkTimeDao specWorkTimeDao, AppointmentDao appointmentDao) {
        this.specWorkTimeDao = specWorkTimeDao;
        this.appointmentDao = appointmentDao;
    }

    @Override
    public List<OpenAppointment> getBySpec(int id, Instant from, Instant to) {
        List<SpecWorkTime> workTimes = specWorkTimeDao.getBetween(id, from, to);
        List<Appointment> appointments = appointmentDao.getBetween(id, from, to);

        Map<SpecWorkTime, List<Appointment>> map = new HashMap<>();

        for (SpecWorkTime workTime : workTimes) {
            List<Appointment> appointmentList = new ArrayList<>();

            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentStart().compareTo(workTime.getWorkStart()) >= 0 &&
                        appointment.getAppointmentEnd().compareTo(workTime.getWorkEnd()) <= 0) {
                    appointmentList.add(appointment);
                }
            }
            map.put(workTime, appointmentList);
        }

        List<OpenAppointment> openAppointmentList = new ArrayList<>();

        for (Map.Entry<SpecWorkTime, List<Appointment>> entry : map.entrySet()) {
            List<Appointment> appointmentList = entry.getValue();
            appointmentList.sort(Comparator.comparing(Appointment::getAppointmentStart));

            Instant openStart;
            Instant openEnd;

            for (int i = 0; i < appointmentList.size() - 1; i++) {
                if (!appointmentList.get(i).getAppointmentEnd().equals(appointmentList.get(i + 1).getAppointmentStart())) {
                    openStart = appointmentList.get(i).getAppointmentEnd();
                    openEnd = appointmentList.get(i + 1).getAppointmentStart();
                    OpenAppointment openAppointment = new OpenAppointment(openStart, openEnd, id);
                    openAppointmentList.add(openAppointment);
                }
            }

            if (!entry.getKey().getWorkStart().equals(appointmentList.get(0).getAppointmentStart())) {
                openStart = entry.getKey().getWorkStart();
                openEnd = appointmentList.get(0).getAppointmentStart();
                OpenAppointment openAppointment = new OpenAppointment(openStart, openEnd, id);
                openAppointmentList.add(openAppointment);
            }

            if (!appointmentList.get(appointmentList.size() - 1).getAppointmentEnd().
                    equals(entry.getKey().getWorkEnd())) {
                openStart = appointmentList.get(appointmentList.size() - 1).getAppointmentEnd();
                openEnd = entry.getKey().getWorkEnd();
                OpenAppointment openAppointment = new OpenAppointment(openStart, openEnd, id);
                openAppointmentList.add(openAppointment);
            }
        }
        return openAppointmentList;
    }
}
