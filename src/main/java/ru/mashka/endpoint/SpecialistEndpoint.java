package ru.mashka.endpoint;

import ru.mashka.dao.SpecialistDao;
import ru.mashka.model.entity.Specialist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/specialists")
public class SpecialistEndpoint {

    private final SpecialistDao specialistDao;

    @Inject
    public SpecialistEndpoint(SpecialistDao specialistDao) {
        this.specialistDao = specialistDao;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Specialist getId(@PathParam("id") int id) {
        return specialistDao.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Specialist specialist) {
        specialistDao.add(specialist);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Specialist specialist) {
        specialistDao.update(specialist);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        specialistDao.delete(id);
    }
}
