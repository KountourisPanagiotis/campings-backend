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
/**
 * The EmplacementRestController class provides REST API endpoints for managing emplacements.
 * It allows retrieving, creating, updating, and deleting emplacement records.
 * The endpoints support JSON data format for request and response payloads.
 * This class uses JAX-RS annotations to define the API endpoints and HTTP methods.
 * It interacts with the IEmplacementService implementation to perform the necessary business logic.
 *
 * Base URL: /emplacement
 *
 * Example usage:
 * GET /emplacement/{campCode}/{empNo} - Retrieves a specific emplacement by its camp code and emp number.
 * GET /emplacement - Retrieves a list of all emplacements.
 * POST /emplacement - Creates a new emplacement.
 * PUT /emplacement/{campCode}/{empNo} - Updates an existing emplacement by its camp code and emp number.
 * DELETE /emplacement/{campCode}/{empNo} - Deletes an emplacement by its camp code and emp number.
 *
 * Note: This class assumes the use of JSON data format for request and response payloads.
 *
 * @see IEmplacementService
 * @see EmplacementDTO
 * @see Response
 */
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
