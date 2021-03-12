package ru.mashka.endpoint;

import ru.mashka.dao.SpecWorkTimeDao;
import ru.mashka.model.dto.UpdateSpecWorkTimeRequest;
import ru.mashka.model.entity.SpecWorkTime;
import ru.mashka.model.key.SpecWorkTimeKey;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/specWorkTimes")
public class SpecWorkTimeEndpoint {

    private final SpecWorkTimeDao specWorkTimeDao;

    @Inject
    public SpecWorkTimeEndpoint(SpecWorkTimeDao specWorkTimeDao) {
        this.specWorkTimeDao = specWorkTimeDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(SpecWorkTime specWorkTime) {
        specWorkTimeDao.add(specWorkTime);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(SpecWorkTimeKey specWorkTimeKey) {
        specWorkTimeDao.delete(specWorkTimeKey);
    }

    @GET
    @Path("{specId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SpecWorkTime> get(@PathParam("specId") int id) {
        return specWorkTimeDao.getBySpecId(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(UpdateSpecWorkTimeRequest request) {
        specWorkTimeDao.update(request.getSpecWorkTimeKey(), request.getSpecWorkTime());
    }
}
