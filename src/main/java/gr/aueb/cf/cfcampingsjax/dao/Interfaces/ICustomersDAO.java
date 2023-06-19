package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Customer;
import java.util.List;

/**
 * ICustomersDAO is an interface for managing customers in the database.
 * It provides methods for creating, reading, updating, and deleting customer records.
 *
 * @author Kountouris Panagiotis
 */
public interface ICustomersDAO {

    /**
     * Retrieves a customer by its code.
     *
     * @param custCode An integer that represents the customer code.
     * @return Customer object corresponding to the provided customer code.
     */
    Customer getCustomerByCode(int custCode);

    /**
     * Retrieves all customers.
     *
     * @return List of all Customer objects from the database.
     */
    List<Customer> getAllCustomers();

    /**
     * Adds a new customer to the database.
     *
     * @param customer A Customer object that needs to be added to the database.
     * @return The Customer object that was added to the database.
     */
    Customer insertCustomer(Customer customer);

    /**
     * Updates an existing customer in the database.
     *
     * @param customer A Customer object that needs to be updated in the database.
     * @return The Customer object that was updated in the database.
     */
    Customer updateCustomer(Customer customer);

    /**
     * Deletes a customer from the database.
     *
     * @param custCode An integer that represents the customer code of the customer to be deleted.
     * @return The Customer object that was deleted from the database.
     */
    Customer deleteCustomer(int custCode);
}
