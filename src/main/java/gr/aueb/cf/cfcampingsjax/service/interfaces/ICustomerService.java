package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CustomerDTO;
import java.util.List;

/**
 * ICustomerService is an interface for managing customers in the application.
 * It provides methods for retrieving, inserting, updating, and deleting customer records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ICustomerService {

    /**
     * Retrieves a customer by its code.
     *
     * @param custCode The unique code of the customer to retrieve.
     * @return CustomerDTO object corresponding to the customer with the provided code.
     */
    CustomerDTO getCustomerByCode(int custCode);

    /**
     * Retrieves all customers.
     *
     * @return List of all CustomerDTO objects.
     */
    List<CustomerDTO> getAllCustomers();

    /**
     * Adds a new customer to the database.
     *
     * @param customer The CustomerDTO object representing the new customer to be added.
     * @return The added CustomerDTO object.
     */
    CustomerDTO insertCustomer(CustomerDTO customer);

    /**
     * Updates an existing customer in the database.
     *
     * @param customer The CustomerDTO object representing the customer to be updated.
     * @return The updated CustomerDTO object.
     */
    CustomerDTO updateCustomer(CustomerDTO customer);

    /**
     * Deletes a customer from the database.
     *
     * @param custCode The unique code of the customer to delete.
     * @return The deleted CustomerDTO object.
     */
    CustomerDTO deleteCustomer(int custCode);
}
