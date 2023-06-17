package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IPaymentsDAO;
import gr.aueb.cf.cfcampingsjax.model.Payment;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements IPaymentsDAO {

    @Override
    public Payment getPaymentByCode(int payCode) {
        Payment payment = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM payments WHERE payCode = ?");
            ps.setInt(1, payCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payment = new Payment();
                payment.setPayCode(rs.getInt("payCode"));
                payment.setPayMethod(rs.getString("payMethod"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return payment != null ? new Payment(payment) : null;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM payments");
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPayCode(rs.getInt("payCode"));
                payment.setPayMethod(rs.getString("payMethod"));
                payments.add(payment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Payment> copyPayments = new ArrayList<>();
        for (Payment payment : payments) {
            copyPayments.add(new Payment(payment));
        }
        return copyPayments;
    }

    @Override
    public Payment insertPayment(Payment payment) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO payments (payCode, payMethod) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, payment.getPayCode());
            ps.setString(2, payment.getPayMethod());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedPayCode = rs.getInt(1);
                payment.setPayCode(generatedPayCode);
            } else {
                throw new SQLException("Creating payment failed, no ID obtained.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return new Payment(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE payments SET payMethod = ? WHERE payCode = ?");
            ps.setString(1, payment.getPayMethod());
            ps.setInt(2, payment.getPayCode());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Payment(payment);
    }

    @Override
    public Payment deletePayment(int payCode) {
        Payment payment = getPaymentByCode(payCode);
        if (payment == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM payments WHERE payCode = ?");
            ps.setInt(1, payCode);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Payment(payment);
    }
}
