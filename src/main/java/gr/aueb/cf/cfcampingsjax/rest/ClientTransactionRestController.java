package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IBookingsDAO;
import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IClientTransactionsDAO;
import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ISpotrentalsDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.BookingDAOImpl;
import gr.aueb.cf.cfcampingsjax.dao.implementations.ClientTransactionDAOImpl;
import gr.aueb.cf.cfcampingsjax.dao.implementations.SpotrentalDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.ClientTransactionDTO;
import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;
import gr.aueb.cf.cfcampingsjax.service.implementations.ClientTransactionServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IClientTransactionService;
import gr.aueb.cf.cfcampingsjax.service.util.DTOUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/transactions")
public class ClientTransactionRestController {
    private final IClientTransactionService clientTransactionService;
    public ClientTransactionRestController() {
        IClientTransactionsDAO  clientTransactionDAO = new ClientTransactionDAOImpl();
        ISpotrentalsDAO spotrentalsDAO = new SpotrentalDAOImpl();
        IBookingsDAO bookingsDAO = new BookingDAOImpl();
        this.clientTransactionService = new ClientTransactionServiceImpl(bookingsDAO, spotrentalsDAO, clientTransactionDAO );
    }

    @GET
    @Path("/{bookCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientTransactionByBookCode(@PathParam("bookCode") int bookCode) {
        ClientTransactionDTO clientTransaction = clientTransactionService.getClientTransactionByBookCode(bookCode);
        if (clientTransaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(clientTransaction).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClientTransactions() {
        List<ClientTransactionDTO> clientTransactions = clientTransactionService.getAllClientTransactions();
        return Response.ok(clientTransactions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertClientTransaction(ClientTransactionDTO clientTransaction) {
        ClientTransactionDTO createdTransaction = clientTransactionService.insertClientTransaction(clientTransaction);
        if (createdTransaction == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(createdTransaction).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClientTransaction(ClientTransactionDTO clientTransaction) {
        ClientTransactionDTO updatedTransaction = clientTransactionService.updateClientTransaction(clientTransaction);
        if (updatedTransaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedTransaction).build();
    }

    @DELETE
    @Path("/{bookCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteClientTransaction(@PathParam("bookCode") int bookCode) {
        ClientTransactionDTO deletedTransaction = clientTransactionService.deleteClientTransaction(bookCode);
        if (deletedTransaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(deletedTransaction).build();
    }
}
