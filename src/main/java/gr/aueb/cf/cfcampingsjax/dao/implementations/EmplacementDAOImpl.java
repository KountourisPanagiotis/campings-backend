package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IEmplacementsDAO;
import gr.aueb.cf.cfcampingsjax.model.Emplacement;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplacementDAOImpl implements IEmplacementsDAO {

    @Override
    public Emplacement getEmplacementByCampCodeAndEmpNo(String campCode, int empNo) {
        Emplacement emplacement = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM emplacements WHERE campCode = ? AND empNo = ?");
            ps.setString(1, campCode);
            ps.setInt(2, empNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emplacement = new Emplacement();
                emplacement.setCampCode(rs.getString("campCode"));
                emplacement.setEmpNo(rs.getInt("empNo"));
                emplacement.setCatCode(rs.getString("catCode"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return emplacement != null ? new Emplacement(emplacement) : null;
    }

    @Override
    public List<Emplacement> getAllEmplacements() {
        List<Emplacement> emplacements = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emplacements");
            while (rs.next()) {
                Emplacement emplacement = new Emplacement();
                emplacement.setCampCode(rs.getString("campCode"));
                emplacement.setEmpNo(rs.getInt("empNo"));
                emplacement.setCatCode(rs.getString("catCode"));
                emplacements.add(emplacement);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Emplacement> copyEmplacements = new ArrayList<>();
        for (Emplacement emplacement : emplacements) {
            copyEmplacements.add(new Emplacement(emplacement));
        }
        return copyEmplacements;
    }

    @Override
    public Emplacement insertEmplacement(Emplacement emplacement) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO emplacements (campCode, empNo, catCode) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, emplacement.getCampCode());
            ps.setInt(2, emplacement.getEmpNo());
            ps.setString(3, emplacement.getCatCode());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating emplacement failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                String generatedCampCode = rs.getString(1);
                int generatedEmpNo = rs.getInt(2);
                emplacement.setCampCode(generatedCampCode);
                emplacement.setEmpNo(generatedEmpNo);
            } else {
                throw new SQLException("Creating emplacement failed, no ID obtained.");
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
        return new Emplacement(emplacement);
    }

    @Override
    public Emplacement updateEmplacement(Emplacement emplacement) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE emplacements SET catCode = ? WHERE campCode = ? AND empNo = ?");
            ps.setString(1, emplacement.getCatCode());
            ps.setString(2, emplacement.getCampCode());
            ps.setInt(3, emplacement.getEmpNo());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Emplacement(emplacement);
    }

    @Override
    public Emplacement deleteEmplacement(String campCode, int empNo) {
        Emplacement emplacement = getEmplacementByCampCodeAndEmpNo(campCode, empNo);
        if (emplacement == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM emplacements WHERE campCode = ? AND empNo = ?");
            ps.setString(1, campCode);
            ps.setInt(2, empNo);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Emplacement(emplacement);
    }
}
