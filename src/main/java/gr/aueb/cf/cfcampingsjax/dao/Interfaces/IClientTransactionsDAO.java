package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;

import java.util.List;

/**
 * Extra functionality
 */
public interface IClientTransactionsDAO {
    // Returns all client transactions
    List<ClientTransaction> getAllClientTransactions();
}
