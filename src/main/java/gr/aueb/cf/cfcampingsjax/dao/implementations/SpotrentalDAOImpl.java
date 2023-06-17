package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ISpotrentalsDAO;
import gr.aueb.cf.cfcampingsjax.model.Spotrental;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SpotrentalDAOImpl implements ISpotrentalsDAO {

    @Override
    public Spotrental getSpotrentalByPrimaryKeys(int bookCode, String campCode, int empNo, java.util.Date startDt) {
        Spotrental spotrental = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM spotrentals WHERE bookCode = ? AND campCode = ? AND empNo = ? AND startDt = ?");
            ps.setInt(1, bookCode);
            ps.setString(2, campCode);
            ps.setInt(3, empNo);
            ps.setDate(4, new java.sql.Date(startDt.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                spotrental = new Spotrental();

                spotrental.setBookCode(rs.getInt("bookCode"));
                spotrental.setCampCode(rs.getString("campCode"));
                spotrental.setEmpNo(rs.getInt("empNo"));
                spotrental.setStartDt(rs.getDate("startDt"));
                spotrental.setEndDt(rs.getDate("endDt"));
                spotrental.setNoPers(rs.getInt("noPers"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spotrental;
    }

    @Override
    public List<Spotrental> getAllSpotrentals() {
        List<Spotrental> spotrentals = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM spotrentals");
            while (rs.next()) {
                Spotrental spotrental = new Spotrental();
                spotrental.setBookCode(rs.getInt("bookCode"));
                spotrental.setCampCode(rs.getString("campCode"));
                spotrental.setEmpNo(rs.getInt("empNo"));
                spotrental.setStartDt(rs.getDate("startDt"));
                spotrental.setEndDt(rs.getDate("endDt"));
                spotrental.setNoPers(rs.getInt("noPers"));
                spotrentals.add(spotrental);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spotrentals;
    }

    @Override
    public List<Spotrental> getAllSpotrentalsWithBookCode(int bookCode) {
        List<Spotrental> spotrentals = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM spotrentals WHERE bookCode = ?");
            ps.setInt(1, bookCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spotrental spotrental = new Spotrental();
                spotrental.setBookCode(rs.getInt("bookCode"));
                spotrental.setCampCode(rs.getString("campCode"));
                spotrental.setEmpNo(rs.getInt("empNo"));
                spotrental.setStartDt(rs.getDate("startDt"));
                spotrental.setEndDt(rs.getDate("endDt"));
                spotrental.setNoPers(rs.getInt("noPers"));
                spotrentals.add(spotrental);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spotrentals;
    }

    @Override
    public Spotrental insertSpotrental(Spotrental spotrental) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO spotrentals (bookCode, campCode, empNo, startDt, endDt, noPers) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, spotrental.getBookCode());
            ps.setString(2, spotrental.getCampCode());
            ps.setInt(3, spotrental.getEmpNo());
            ps.setDate(4, new java.sql.Date(spotrental.getStartDt().getTime()));
            ps.setDate(5, new java.sql.Date(spotrental.getEndDt().getTime()));
            ps.setInt(6, spotrental.getNoPers());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating spotrental failed, no rows affected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spotrental;
    }

    @Override
    public Spotrental updateSpotrental(Spotrental spotrental) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE spotrentals SET endDt = ?, noPers = ? WHERE bookCode = ? AND campCode = ? AND empNo = ? AND startDt = ?");
            ps.setDate(1, new java.sql.Date(spotrental.getEndDt().getTime()));
            ps.setInt(2, spotrental.getNoPers());
            ps.setInt(3, spotrental.getBookCode());
            ps.setString(4, spotrental.getCampCode());
            ps.setInt(5, spotrental.getEmpNo());
            ps.setDate(6, new java.sql.Date(spotrental.getStartDt().getTime()));
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated == 1) {
                // The update was successful, fetch the updated object from the database
                return getSpotrentalByPrimaryKeys(spotrental.getBookCode(), spotrental.getCampCode(), spotrental.getEmpNo(), spotrental.getStartDt());
            } else {
                // The update was not successful, throw an exception or return null
                throw new SQLException("Update failed, no rows were affected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Spotrental deleteSpotrental(int bookCode, String campCode, int empNo, Date startDt) {
        Spotrental spotrental = null;

        // First, fetch the Spotrental that we want to delete
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM spotrentals WHERE bookCode = ? AND campCode = ? AND empNo = ? AND startDt = ?");
            ps.setInt(1, bookCode);
            ps.setString(2, campCode);
            ps.setInt(3, empNo);
            ps.setDate(4, new java.sql.Date(startDt.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                spotrental = new Spotrental();
                spotrental.setBookCode(rs.getInt("bookCode"));
                spotrental.setCampCode(rs.getString("campCode"));
                spotrental.setEmpNo(rs.getInt("empNo"));
                spotrental.setStartDt(rs.getDate("startDt"));
                spotrental.setEndDt(rs.getDate("endDt"));
                spotrental.setNoPers(rs.getInt("noPers"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM spotrentals WHERE bookCode = ? AND campCode = ? AND empNo = ? AND startDt = ?");
            ps.setInt(1, bookCode);
            ps.setString(2, campCode);
            ps.setInt(3, empNo);
            ps.setDate(4, new java.sql.Date(startDt.getTime()));
            int affectedRows = ps.executeUpdate();

            // If deletion is unsuccessful, we create a new empty Spotrental to return
            if (affectedRows == 0) {
                spotrental = new Spotrental();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return spotrental;
    }
}
