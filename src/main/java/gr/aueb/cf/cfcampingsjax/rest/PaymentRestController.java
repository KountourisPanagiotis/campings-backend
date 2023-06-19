package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IPaymentsDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.PaymentDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.PaymentDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.PaymentServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 * The PaymentRestController class provides REST API endpoints for managing payments.
 * It allows retrieving, creating, updating, and deleting payment records.
 * The endpoints support JSON data format for request and response payloads.
 * This class uses JAX-RS annotations to define the API endpoints and HTTP methods.
 * It interacts with the IPaymentService implementation to perform the necessary business logic.
 *
 * Base URL: /payment
 *
 * Example usage:
 * GET /payment/{payCode} - Retrieves a specific payment by its payment code.
 * GET /payment - Retrieves a list of all payments.
 * POST /payment - Creates a new payment.
 * PUT /payment/{payCode} - Updates an existing payment.
 * DELETE /payment/{payCode} - Deletes a payment by its payment code.
 *
 * Note: This class assumes the use of JSON data format for request and response payloads.
 *
 * @see IPaymentService
 * @see PaymentDTO
 * @see Response
 */
@Path("/payment")
public class PaymentRestController {

    private final IPaymentService paymentService;

    public PaymentRestController() {
        IPaymentsDAO paymentsDAO = new PaymentDAOImpl();
        this.paymentService = new PaymentServiceImpl(paymentsDAO);
    }

    @GET
    @Path("/{payCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get payment by code", tags = {"payments"})
    public Response getPaymentByCode(@PathParam("payCode") int payCode) {
        PaymentDTO payment = paymentService.getPaymentByCode(payCode);
        if (payment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(payment).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all payments", tags = {"payments"})
    public Response getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return Response.ok(payments).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPayment(PaymentDTO paymentDTO) {
        try {
            PaymentDTO createdPayment = paymentService.insertPayment(paymentDTO);
            return Response.status(Response.Status.CREATED).entity(createdPayment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{payCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePayment(@PathParam("payCode") int payCode, PaymentDTO paymentDTO) {
        try {
            paymentDTO.setPayCode(payCode);
            PaymentDTO updatedPayment = paymentService.updatePayment(paymentDTO);
            if (updatedPayment == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedPayment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{payCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePayment(@PathParam("payCode") int payCode) {
        try {
            PaymentDTO deletedPayment = paymentService.deletePayment(payCode);
            if (deletedPayment == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedPayment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
