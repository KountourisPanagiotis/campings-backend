package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IBookingsDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.BookingDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.BookingServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/booking")
public class BookingRestController {

    private final IBookingService bookingService;

    public BookingRestController() {
        IBookingsDAO BookingDAO = new BookingDAOImpl();
        this.bookingService = new BookingServiceImpl(BookingDAO);
    }

    @Path("/{bookCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get booking by code", tags = {"bookings"})
    public Response getBookingByCode(@PathParam("bookCode") int bookCode) {
        BookingDTO booking = bookingService.getBookingByCode(bookCode);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(booking).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all bookings", tags = {"bookings"})
    public Response getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return Response.ok(bookings).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertBooking(BookingDTO bookingDTO) {
        try {
            BookingDTO createdBooking = bookingService.insertBooking(bookingDTO);
            return Response.status(Response.Status.CREATED).entity(createdBooking).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{bookCode}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBooking(@PathParam("bookCode") int bookCode, BookingDTO bookingDTO) {
        try {
            bookingDTO.setBookCode(bookCode);
            BookingDTO updatedBooking = bookingService.updateBooking(bookingDTO);
            if (updatedBooking == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedBooking).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{bookCode}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBooking(@PathParam("bookCode") int bookCode) {
        try {
            BookingDTO deletedBooking = bookingService.deleteBooking(bookCode);
            if (deletedBooking == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedBooking).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
