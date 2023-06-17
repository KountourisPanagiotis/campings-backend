package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICampingDAO;
import gr.aueb.cf.cfcampingsjax.model.Camping;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampingDAOImpl implements ICampingDAO {

    @Override
    public Camping getCampingByCode(String campCode) {
        Camping camping = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM campings WHERE campCode = ?");
            ps.setString(1, campCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                camping = new Camping();
                camping.setCampCode(rs.getString("campCode"));
                camping.setCampName(rs.getString("campName"));
                camping.setNumOfEmp(rs.getInt("numOfEmp"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Return new with copy constructor we made in the Camping class
        return camping != null ? new Camping(camping) : null;
    }

    @Override
    public List<Camping> getAllCampings() {
        List<Camping> campings = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM campings");
            while (rs.next()) {
                Camping camping = new Camping();
                camping.setCampCode(rs.getString("campCode"));
                camping.setCampName(rs.getString("campName"));
                camping.setNumOfEmp(rs.getInt("numOfEmp"));
                campings.add(camping);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Camping> copyCampings = new ArrayList<>();
        for (Camping camping : campings) {
            copyCampings.add(new Camping(camping));
        }
        return copyCampings;
    }

    @Override
    public Camping insertCamping(Camping camping) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO campings (campCode, campName, numOfEmp) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, camping.getCampCode());
            ps.setString(2, camping.getCampName());
            ps.setInt(3, camping.getNumOfEmp());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating camping failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                String generatedCampCode = rs.getString(1);
                camping.setCampCode(generatedCampCode);
            } else {
                throw new SQLException("Creating camping failed, no ID obtained.");
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
        return new Camping(camping);
    }

    @Override
    public Camping updateCamping(Camping camping) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE campings SET campName = ?, numOfEmp = ? WHERE campCode = ?");
            ps.setString(1, camping.getCampName());
            ps.setInt(2, camping.getNumOfEmp());
            ps.setString(3, camping.getCampCode());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Camping(camping);
    }

    @Override
    public Camping deleteCamping(String campCode) {
        Camping camping = getCampingByCode(campCode);
        if (camping == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM campings WHERE campCode = ?");
            ps.setString(1, campCode);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Camping(camping);
    }
}
