package ru.mashka.endpoint;

import ru.mashka.dao.AppointmentDao;
import ru.mashka.model.dto.UpdateAppointmentRequest;
import ru.mashka.model.entity.Appointment;
import ru.mashka.model.key.AppointmentKey;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/appointments")
public class AppointmentEndpoint {

    private final AppointmentDao appointmentDao;

    @Inject
    public AppointmentEndpoint(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Appointment appointment) {
        appointmentDao.add(appointment);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(AppointmentKey appointmentKey) {
        appointmentDao.delete(appointmentKey);
    }

    @GET
    @Path("{clientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getByClient(@PathParam("clientName") String clientName) {
        return appointmentDao.getByClient(clientName);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(UpdateAppointmentRequest request) {
        appointmentDao.update(request.getAppointmentKey(), request.getAppointment());
    }
}
