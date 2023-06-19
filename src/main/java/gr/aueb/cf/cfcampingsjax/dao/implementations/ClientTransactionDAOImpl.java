package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IClientTransactionsDAO;
import gr.aueb.cf.cfcampingsjax.model.Booking;
import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;
import gr.aueb.cf.cfcampingsjax.model.Spotrental;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientTransactionDAOImpl implements IClientTransactionsDAO {

    /**
     * Returns all the client transactions from the database.
     * We made use of this class because using the other CRUD operations
     * for each individual table and then collecting the results in service
     * layer had high execution times. Optimized and reduced the times in combination
     * with the implementation of Angular ScrollingModule at the front-end.
     *
     * @author Kountouris Panagiotis.
     * @return A list of client Transactions.
     */
    @Override
    public List<ClientTransaction> getAllClientTransactions() {
        Map<Integer, ClientTransaction> transactionsMap = new HashMap<>();

        try (Connection conn = DBUtil.getConnection()) {
            String query = "SELECT * FROM bookings " +
                    "INNER JOIN spotrentals ON bookings.bookCode = spotrentals.bookcode";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bookCode = rs.getInt("bookCode");

                if (!transactionsMap.containsKey(bookCode)) {
                    Booking booking = new Booking();
                    booking.setBookCode(rs.getInt("bookCode"));
                    booking.setBookDt(rs.getDate("bookDt"));
                    booking.setPayCode(rs.getInt("payCode"));
                    booking.setCustCode(rs.getInt("custCode"));
                    booking.setStaffNo(rs.getInt("staffNo"));

                    ClientTransaction transaction = new ClientTransaction();
                    transaction.setBooking(booking);
                    transaction.setSpotRentals(new ArrayList<>());

                    transactionsMap.put(bookCode, transaction);
                }

                Spotrental spotrental = new Spotrental();
                spotrental.setBookCode(rs.getInt("bookCode"));
                spotrental.setCampCode(rs.getString("campCode"));
                spotrental.setEmpNo(rs.getInt("empNo"));
                spotrental.setStartDt(rs.getDate("startDt"));
                spotrental.setEndDt(rs.getDate("endDt"));
                spotrental.setNoPers(rs.getInt("noPers"));

                transactionsMap.get(bookCode).getSpotRentals().add(spotrental);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(transactionsMap.values());
    }
}
