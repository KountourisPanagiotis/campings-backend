package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IStaffDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.StaffDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.StaffDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.StaffServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IStaffService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 *
 * The StaffRestController class provides REST API endpoints for managing staff members.
 * It allows retrieving, creating, updating, and deleting staff records.
 * The endpoints support JSON data format for request and response payloads.
 * This class uses JAX-RS annotations to define the API endpoints and HTTP methods.
 * It interacts with the IStaffService implementation to perform the necessary business logic.
 *
 * Base URL: /staff
 *
 * Example usage:
 * GET /staff - Retrieves a list of all staff members.
 * GET /staff/{staffNo} - Retrieves a specific staff member by their staff number.
 * POST /staff - Creates a new staff member.
 * PUT /staff/{staffNo} - Updates an existing staff member.
 * DELETE /staff/{staffNo} - Deletes a staff member by their staff number.
 *
 * Note: This class assumes the use of JSON data format for request and response payloads.
 *
 * @see IStaffService
 * @see StaffDTO
 * @see Response
 */
@Path("/staff")
public class StaffRestController {

    private final IStaffService staffService;

    public StaffRestController() {
        IStaffDAO staffDAO = new StaffDAOImpl();
        this.staffService = new StaffServiceImpl(staffDAO);
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all staff", tags = {"staff"})
    public Response getAllStaff() {
        List<StaffDTO> staffs = staffService.getAllStaff();
        return Response.ok(staffs).build();
    }

    @Path("/{staffNo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStaffByNo(@PathParam("staffNo") int staffNo) {
        StaffDTO staff = staffService.getStaffByNumber(staffNo);
        if (staff == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(staff).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertStaff(StaffDTO staffDTO) {
        try {
            StaffDTO createdStaff = staffService.insertStaff(staffDTO);
            return Response.status(Response.Status.CREATED).entity(createdStaff).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{staffNo}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(StaffDTO staffDTO) {
        try {
            StaffDTO updatedStaff = staffService.updateStaff(staffDTO);
            if (updatedStaff == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedStaff).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{staffNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStaff(@PathParam("staffNo") int staffNo) {
        try {
            StaffDTO deletedStaff = staffService.deleteStaff(staffNo);
            if (deletedStaff == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedStaff).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
