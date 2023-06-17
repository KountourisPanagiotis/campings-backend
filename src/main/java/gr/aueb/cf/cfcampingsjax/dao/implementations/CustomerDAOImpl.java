package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICustomersDAO;
import gr.aueb.cf.cfcampingsjax.model.Customer;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomersDAO {

    @Override
    public Customer getCustomerByCode(int custCode) {
        Customer customer = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE custCode = ?");
            ps.setInt(1, custCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustCode(rs.getInt("custCode"));
                customer.setCustName(rs.getString("custName"));
                customer.setCustSurname(rs.getString("custSurname"));
                customer.setCustPhone(rs.getString("custPhone"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customer != null ? new Customer(customer) : null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustCode(rs.getInt("custCode"));
                customer.setCustName(rs.getString("custName"));
                customer.setCustSurname(rs.getString("custSurname"));
                customer.setCustPhone(rs.getString("custPhone"));
                customers.add(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO customers (custCode, custName, custSurname, custPhone) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customer.getCustCode());
            ps.setString(2, customer.getCustName());
            ps.setString(3, customer.getCustSurname());
            ps.setString(4, customer.getCustPhone());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedCustCode = rs.getInt(1);
                customer.setCustCode(generatedCustCode);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return new Customer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE customers SET custName = ?, custSurname = ?, custPhone = ? WHERE custCode = ?");
            ps.setString(1, customer.getCustName());
            ps.setString(2, customer.getCustSurname());
            ps.setString(3, customer.getCustPhone());
            ps.setInt(4, customer.getCustCode());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Customer(customer);
    }

    @Override
    public Customer deleteCustomer(int custCode) {
        Customer customer = getCustomerByCode(custCode);
        if (customer == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM customers WHERE custCode = ?");
            ps.setInt(1, custCode);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Customer(customer);
    }
}
