package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICustomersDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.CustomerDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.CustomerDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.CustomerServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 * The CustomerRestController class provides REST API endpoints for managing customers.
 * It allows retrieving, creating, updating, and deleting customer records.
 * The endpoints support JSON data format for request and response payloads.
 * This class uses JAX-RS annotations to define the API endpoints and HTTP methods.
 * It interacts with the ICustomerService implementation to perform the necessary business logic.
 *
 * Base URL: /customer
 *
 * Example usage:
 * GET /customer - Retrieves a list of all customers.
 * GET /customer/{custCode} - Retrieves a specific customer by their customer code.
 * POST /customer - Creates a new customer.
 * PUT /customer/{custCode} - Updates an existing customer.
 * DELETE /customer/{custCode} - Deletes a customer by their customer code.
 *
 * Note: This class assumes the use of JSON data format for request and response payloads.
 *
 * @see ICustomerService
 * @see CustomerDTO
 * @see Response
 */
@Path("/customer")
public class CustomerRestController {

    private final ICustomerService customerService;

    public CustomerRestController() {
        ICustomersDAO customersDAO = new CustomerDAOImpl();
        this.customerService = new CustomerServiceImpl(customersDAO);
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all customers", tags = {"customers"})
    public Response getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }

    @Path("/{custCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByCode(@PathParam("custCode") int custCode) {
        CustomerDTO customer = customerService.getCustomerByCode(custCode);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(CustomerDTO customerDTO) {
        try {
            CustomerDTO createdCustomer = customerService.insertCustomer(customerDTO);
            return Response.status(Response.Status.CREATED).entity(createdCustomer).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{custCode}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerDTO customerDTO) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO);
            if (updatedCustomer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedCustomer).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{custCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("custCode") int custCode) {
        try {
            CustomerDTO deletedCustomer = customerService.deleteCustomer(custCode);
            if (deletedCustomer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedCustomer).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
