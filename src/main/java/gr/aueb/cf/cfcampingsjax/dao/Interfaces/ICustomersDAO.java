package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Customer;

import java.util.List;

public interface ICustomersDAO {
    // Retrieves a customer by its code.
    Customer getCustomerByCode(int custCode);

    // Retrieves all customers.
    List<Customer> getAllCustomers();

    // Adds a new customer to the database.
    Customer insertCustomer(Customer customer);

    // Updates an existing customer in the database.
    Customer updateCustomer(Customer customer);

    // Deletes a customer from the database.
    Customer deleteCustomer(int custCode);
}
