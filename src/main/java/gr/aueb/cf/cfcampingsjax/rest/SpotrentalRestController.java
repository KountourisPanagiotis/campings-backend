package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ISpotrentalsDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.SpotrentalDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.SpotrentalServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ISpotrentalService;
import io.swagger.v3.oas.annotations.Operation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * The SpotrentalRestController class provides REST API endpoints for managing spot rentals.
 * It allows retrieving, creating, updating, and deleting spot rental records.
 * The endpoints support JSON data format for request and response payloads.
 * This class uses JAX-RS annotations to define the API endpoints and HTTP methods.
 * It interacts with the ISpotrentalService implementation to perform the necessary business logic.
 *
 * Base URL: /spotrental
 *
 * Example usage:
 * GET /spotrental/getByKeys?bookCode={bookCode}&campCode={campCode}&empNo={empNo}&startDt={startDt} - Retrieves a specific spot rental by its primary keys.
 * GET /spotrental - Retrieves a list of all spot rentals.
 * POST /spotrental - Creates a new spot rental.
 * PUT /spotrental/{bookCode}/{campCode}/{empNo}/{startDt} - Updates an existing spot rental by its primary keys.
 * DELETE /spotrental/{bookCode}/{campCode}/{empNo}/{startDt} - Deletes a spot rental by its primary keys.
 *
 * Note: This class assumes the use of JSON data format for request and response payloads.
 *
 * @see ISpotrentalService
 * @see SpotrentalDTO
 * @see Response
 */
@Path("/spotrental")
public class SpotrentalRestController {

    private final ISpotrentalService spotrentalService;

    public SpotrentalRestController() {
        ISpotrentalsDAO spotrentalsDAO = new SpotrentalDAOImpl();
        this.spotrentalService = new SpotrentalServiceImpl(spotrentalsDAO);
    }

    @Path("/getByKeys")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get spot rental by keys", tags = {"spotrentals"})
    public Response getSpotRentalByPrimaryKeys(
            @QueryParam("bookCode") int bookCode,
            @QueryParam("campCode") String campCode,
            @QueryParam("empNo") int empNo,
            @QueryParam("startDt") String startDtStr) {

        // Parse the startDt string to a Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDt;
        try {
            startDt = formatter.parse(startDtStr);
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid start date format").build();
        }

        SpotrentalDTO spotRental = spotrentalService.getSpotRentalByKeys(bookCode, campCode, empNo, startDt);
        if (spotRental == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(spotRental).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all spot rentals", tags = {"spotrentals"})
    public Response getAllSpotRentals() {
        List<SpotrentalDTO> spotRentals = spotrentalService.getAllSpotRentals();
        return Response.ok(spotRentals).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSpotRental(SpotrentalDTO spotRentalDTO) {
        try {
            SpotrentalDTO createdSpotRental = spotrentalService.insertSpotRental(spotRentalDTO);
            return Response.status(Response.Status.CREATED).entity(createdSpotRental).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{bookCode}/{campCode}/{empNo}/{startDt}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a spot rental by keys", tags = {"spotrentals"})
    public Response updateSpotRental(
            @PathParam("bookCode") int bookCode,
            @PathParam("campCode") String campCode,
            @PathParam("empNo") int empNo,
            @PathParam("startDt") String startDtStr,
            SpotrentalDTO spotRentalDTO) {

        // Parse the startDt string to a Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDt;

        if (startDtStr != null && !startDtStr.isEmpty()) {
            try {
                startDt = formatter.parse(startDtStr);
            } catch (ParseException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid start date format").build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Start date is null or empty").build();
        }

        try {
            spotRentalDTO.setBookCode(bookCode);
            spotRentalDTO.setCampCode(campCode);
            spotRentalDTO.setEmpNo(empNo);
            spotRentalDTO.setStartDt(startDt);
            SpotrentalDTO updatedSpotRental = spotrentalService.updateSpotRental(spotRentalDTO);
            if (updatedSpotRental == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedSpotRental).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{bookCode}/{campCode}/{empNo}/{startDt}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a spot rental by keys", tags = {"spotrentals"})
    public Response deleteSpotRental(
            @PathParam("bookCode") int bookCode,
            @PathParam("campCode") String campCode,
            @PathParam("empNo") int empNo,
            @PathParam("startDt") String startDtStr) {

        // Parse the startDt string to a Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDt;
        try {
            startDt = formatter.parse(startDtStr);
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid start date format").build();
        }

        try {
            SpotrentalDTO deletedSpotRental = spotrentalService.deleteSpotRental(bookCode, campCode, empNo, startDt);
            if (deletedSpotRental == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedSpotRental).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
