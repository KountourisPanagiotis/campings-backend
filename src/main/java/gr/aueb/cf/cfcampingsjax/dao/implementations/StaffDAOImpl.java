package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IStaffDAO;
import gr.aueb.cf.cfcampingsjax.model.Staff;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements IStaffDAO {

    @Override
    public Staff getStaffByNumber(int staffNo) {
        Staff staff = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM staff WHERE staffNo = ?");
            ps.setInt(1, staffNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffNo(rs.getInt("staffNo"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setStaffSurname(rs.getString("staffSurname"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Return new with copy constructor we made in the Staff class
        return staff != null ? new Staff(staff) : null;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM staff");
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffNo(rs.getInt("staffNo"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setStaffSurname(rs.getString("staffSurname"));
                staffList.add(staff);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Making copy of list for return
        List<Staff> copyStaffList = new ArrayList<>();
        for (Staff staff : staffList) {
            copyStaffList.add(new Staff(staff));
        }
        return copyStaffList;
    }

    @Override
    public Staff insertStaff(Staff staff) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO staff (staffNo, staffName, staffSurname) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, staff.getStaffNo());
            ps.setString(2, staff.getStaffName());
            ps.setString(3, staff.getStaffSurname());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating staff failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedStaffNo = rs.getInt(1);
                staff.setStaffNo(generatedStaffNo);
            } else {
                throw new SQLException("Creating staff failed, no ID obtained.");
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
        return new Staff(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE staff SET staffName = ?, staffSurname = ? WHERE staffNo = ?");
            ps.setString(1, staff.getStaffName());
            ps.setString(2, staff.getStaffSurname());
            ps.setInt(3, staff.getStaffNo());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Staff(staff);
    }

    @Override
    public Staff deleteStaff(int staffNo) {
        Staff staff = getStaffByNumber(staffNo);
        if (staff == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM staff WHERE staffNo = ?");
            ps.setInt(1, staffNo);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Staff(staff);
    }
}
