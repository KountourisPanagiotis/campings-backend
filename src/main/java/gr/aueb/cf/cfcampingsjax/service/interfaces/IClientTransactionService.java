package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.ClientTransactionDTO;

import java.util.List;

public interface IClientTransactionService {

    // Retrieves a client transaction by its booking code.
    ClientTransactionDTO getClientTransactionByBookCode(int bookCode);

    // Retrieves all client transactions.
    List<ClientTransactionDTO> getAllClientTransactions();

    // Adds a new client transaction to the database.
    ClientTransactionDTO insertClientTransaction(ClientTransactionDTO clientTransaction);

    // Updates an existing client transaction in the database.
    ClientTransactionDTO updateClientTransaction(ClientTransactionDTO clientTransaction);

    // Deletes a client transaction from the database.
    ClientTransactionDTO deleteClientTransaction(int bookCode);

}
