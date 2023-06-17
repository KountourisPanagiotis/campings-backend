package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICustomersDAO;
import gr.aueb.cf.cfcampingsjax.dto.CustomerDTO;
import gr.aueb.cf.cfcampingsjax.model.Customer;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private final ICustomersDAO customersDAO;

    // Dependency Injection
    public CustomerServiceImpl(ICustomersDAO customersDAO) {
        this.customersDAO = customersDAO;
    }

    @Override
    public CustomerDTO getCustomerByCode(int custCode) {
        Customer customer = customersDAO.getCustomerByCode(custCode);
        return convertToDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customersDAO.getAllCustomers();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(convertToDTO(customer));
        }
        return customerDTOs;
    }

    @Override
    public CustomerDTO insertCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToModel(customerDTO);
        if (customersDAO.getCustomerByCode(customer.getCustCode()) != null) {
            throw new IllegalArgumentException("Customer with this code already exists.");
        }
        customer = customersDAO.insertCustomer(customer);
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToModel(customerDTO);
        if (customersDAO.getCustomerByCode(customer.getCustCode()) == null) {
            throw new IllegalArgumentException("No such customer exists to update.");
        }
        customer = customersDAO.updateCustomer(customer);
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO deleteCustomer(int custCode) {
        Customer customer = customersDAO.deleteCustomer(custCode);
        return convertToDTO(customer);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer.getCustCode(), customer.getCustName(), customer.getCustSurname(), customer.getCustPhone());
    }

    private Customer convertToModel(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        return new Customer(customerDTO.getCustCode(), customerDTO.getCustName(), customerDTO.getCustSurname(), customerDTO.getCustPhone());
    }
}
