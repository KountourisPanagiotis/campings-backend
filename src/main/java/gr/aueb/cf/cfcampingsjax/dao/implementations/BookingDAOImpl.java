package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IBookingsDAO;
import gr.aueb.cf.cfcampingsjax.model.Booking;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements IBookingsDAO {

    @Override
    public Booking getBookingByCode(int bookCode) {
        Booking booking = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM bookings WHERE bookCode = ?");
            ps.setInt(1, bookCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setBookCode(rs.getInt("bookCode"));
                booking.setBookDt(rs.getDate("bookDt"));
                booking.setPayCode(rs.getInt("payCode"));
                booking.setCustCode(rs.getInt("custCode"));
                booking.setStaffNo(rs.getInt("staffNo"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return booking != null ? new Booking(booking) : null;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookings");
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookCode(rs.getInt("bookCode"));
                booking.setBookDt(rs.getDate("bookDt"));
                booking.setPayCode(rs.getInt("payCode"));
                booking.setCustCode(rs.getInt("custCode"));
                booking.setStaffNo(rs.getInt("staffNo"));
                bookings.add(booking);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking insertBooking(Booking booking) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bookings (bookCode, bookDt, payCode, custCode, staffNo) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getBookCode());
            ps.setDate(2, new java.sql.Date(booking.getBookDt().getTime()));
            ps.setInt(3, booking.getPayCode());
            ps.setInt(4, booking.getCustCode());
            ps.setInt(5, booking.getStaffNo());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedBookCode = rs.getInt(1);
                booking.setBookCode(generatedBookCode);
            } else {
                throw new SQLException("Creating booking failed, no ID obtained.");
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
        return new Booking(booking);
    }

/*    @Override
    public Booking updateBooking(Booking booking) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET bookDt = ?, payCode = ?, custCode = ?, staffNo = ? WHERE bookCode = ?");
            ps.setDate(1, new java.sql.Date(booking.getBookDt().getTime()));
            ps.setInt(2, booking.getPayCode());
            ps.setInt(3, booking.getCustCode());
            ps.setInt(4, booking.getStaffNo());
            ps.setInt(5, booking.getBookCode());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Booking(booking);
    }*/
    @Override
    public Booking updateBooking(Booking booking) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET bookDt = ?, payCode = ?, custCode = ?, staffNo = ? WHERE bookCode = ?");
            ps.setDate(1, new java.sql.Date(booking.getBookDt().getTime()));
            ps.setInt(2, booking.getPayCode());
            ps.setInt(3, booking.getCustCode());
            ps.setInt(4, booking.getStaffNo());
            ps.setInt(5, booking.getBookCode());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating booking failed, no rows affected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Booking(booking);
    }

    @Override
    public Booking deleteBooking(int bookCode) {
        Booking booking = getBookingByCode(bookCode);
        if (booking == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM bookings WHERE bookCode = ?");
            ps.setInt(1, bookCode);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Booking(booking);
    }
}
