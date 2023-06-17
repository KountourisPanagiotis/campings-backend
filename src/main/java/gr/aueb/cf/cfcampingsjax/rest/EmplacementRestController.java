package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IEmplacementsDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.EmplacementDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.EmplacementDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.EmplacementServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IEmplacementService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/emplacement")
public class EmplacementRestController {

    private final IEmplacementService emplacementService;

    public EmplacementRestController() {
        IEmplacementsDAO emplacementsDAO = new EmplacementDAOImpl();
        this.emplacementService = new EmplacementServiceImpl(emplacementsDAO);
    }

    @GET
    @Path("/{campCode}/{empNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplacementByCampCodeAndEmpNo(@PathParam("campCode") String campCode, @PathParam("empNo") int empNo) {
        EmplacementDTO emplacement = emplacementService.getEmplacementByCampCodeAndEmpNo(campCode, empNo);
        if (emplacement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(emplacement).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmplacements() {
        List<EmplacementDTO> emplacements = emplacementService.getAllEmplacements();
        return Response.ok(emplacements).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmplacement(EmplacementDTO emplacementDTO) {
        try {
            EmplacementDTO createdEmplacement = emplacementService.insertEmplacement(emplacementDTO);
            return Response.status(Response.Status.CREATED).entity(createdEmplacement).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{campCode}/{empNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmplacement(@PathParam("campCode") String campCode, @PathParam("empNo") int empNo, EmplacementDTO emplacementDTO) {
        try {
            emplacementDTO.setCampCode(campCode);
            emplacementDTO.setEmpNo(empNo);
            EmplacementDTO updatedEmplacement = emplacementService.updateEmplacement(emplacementDTO);
            if (updatedEmplacement == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedEmplacement).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{campCode}/{empNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmplacement(@PathParam("campCode") String campCode, @PathParam("empNo") int empNo) {
        try {
            EmplacementDTO deletedEmplacement = emplacementService.deleteEmplacement(campCode, empNo);
            if (deletedEmplacement == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedEmplacement).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
