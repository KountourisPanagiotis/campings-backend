package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;

import java.util.List;

/**
 * IClientTransactionsDAO is an interface for managing client transactions in the database.
 * It provides a method for retrieving all client transaction records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IClientTransactionsDAO {

    /**
     * Retrieves all client transactions.
     *
     * @return List of all ClientTransaction objects from the database.
     */
    List<ClientTransaction> getAllClientTransactions();
}
