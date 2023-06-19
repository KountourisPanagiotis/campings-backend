package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.ClientTransactionDTO;

import java.util.List;

/**
 * IClientTransactionService is an interface for managing client transactions in the application.
 * It provides methods for retrieving, inserting, updating, and deleting client transaction records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IClientTransactionService {

    /**
     * Retrieves a client transaction by its booking code.
     *
     * @param bookCode The code of the booking associated with the client transaction.
     * @return ClientTransactionDTO object corresponding to the transaction with the provided booking code.
     */
    ClientTransactionDTO getClientTransactionByBookCode(int bookCode);

    /**
     * Retrieves all client transactions.
     *
     * @return List of all ClientTransactionDTO objects.
     */
    List<ClientTransactionDTO> getAllClientTransactions();

    /**
     * Adds a new client transaction to the database.
     *
     * @param clientTransaction The ClientTransactionDTO object representing the new transaction to be added.
     * @return The added ClientTransactionDTO object.
     */
    ClientTransactionDTO insertClientTransaction(ClientTransactionDTO clientTransaction);

    /**
     * Updates an existing client transaction in the database.
     *
     * @param clientTransaction The ClientTransactionDTO object representing the transaction to be updated.
     * @return The updated ClientTransactionDTO object.
     */
    ClientTransactionDTO updateClientTransaction(ClientTransactionDTO clientTransaction);

    /**
     * Deletes a client transaction from the database.
     *
     * @param bookCode The code of the booking associated with the transaction to be deleted.
     * @return The deleted ClientTransactionDTO object.
     */
    ClientTransactionDTO deleteClientTransaction(int bookCode);
}
