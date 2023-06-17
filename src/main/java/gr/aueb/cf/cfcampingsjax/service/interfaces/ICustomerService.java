package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CustomerDTO;
import java.util.List;

public interface ICustomerService {

    // Retrieves a customer by its code.
    CustomerDTO getCustomerByCode(int custCode);

    // Retrieves all customers.
    List<CustomerDTO> getAllCustomers();

    // Adds a new customer to the database.
    CustomerDTO insertCustomer(CustomerDTO customer);

    // Updates an existing customer in the database.
    CustomerDTO updateCustomer(CustomerDTO customer);

    // Deletes a customer from the database.
    CustomerDTO deleteCustomer(int custCode);
}
