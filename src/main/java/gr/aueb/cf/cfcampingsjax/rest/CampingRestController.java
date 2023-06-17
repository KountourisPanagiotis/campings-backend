package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICampingDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.CampingDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.CampingDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.CampingServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICampingService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/camping")
public class CampingRestController {

    private final ICampingService campingService;

    public CampingRestController() {
        ICampingDAO campingDAO = new CampingDAOImpl();
        this.campingService = new CampingServiceImpl(campingDAO);
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all campings", tags = {"campings"})
    public Response getAllCampings() {
        List<CampingDTO> campings = campingService.getAllCampings();
        return Response.ok(campings).build();
    }

    @Path("/{campCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCampingByCode(@PathParam("campCode") String campCode) {
        CampingDTO camping = campingService.getCampingByCode(campCode);
        if (camping == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(camping).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCamping(CampingDTO campingDTO) {
        try {
            CampingDTO createdCamping = campingService.insertCamping(campingDTO);
            return Response.status(Response.Status.CREATED).entity(createdCamping).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{campCode}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCamping(CampingDTO campingDTO) {
        try {
            CampingDTO updatedCamping = campingService.updateCamping(campingDTO);
            if (updatedCamping == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedCamping).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{campCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCamping(@PathParam("campCode") String campCode) {
        try {
            CampingDTO deletedCamping = campingService.deleteCamping(campCode);
            if (deletedCamping == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedCamping).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
