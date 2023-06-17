package gr.aueb.cf.cfcampingsjax.dao.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICategoriesDAO;
import gr.aueb.cf.cfcampingsjax.model.Category;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOImpl implements ICategoriesDAO {
    @Override
    public Category getCategoryByCode(String catCode) {
        Category category = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM categories WHERE catCode = ?");
            ps.setString(1, catCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCatCode(rs.getString("catCode"));
                category.setAreaM2(rs.getInt("areaM2"));
                category.setUnitCost(rs.getFloat("unitCost"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Return new with copy constructor we made in the Category class
        return category != null ? new Category(category) : null;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
            while (rs.next()) {
                Category category = new Category();
                category.setCatCode(rs.getString("catCode"));
                category.setAreaM2(rs.getInt("areaM2"));
                category.setUnitCost(rs.getFloat("unitCost"));
                categories.add(category);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Category> copyCategories = new ArrayList<>();
        for (Category category : categories) {
            copyCategories.add(new Category(category));
        }
        return copyCategories;
    }

    @Override
    public Category insertCategory(Category category) {
        ResultSet rs = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO categories (catCode, areaM2, unitCost) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getCatCode());
            ps.setInt(2, category.getAreaM2());
            ps.setFloat(3, category.getUnitCost());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating category failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                String generatedCatCode = rs.getString(1);
                category.setCatCode(generatedCatCode);
            } else {
                throw new SQLException("Creating category failed, no ID obtained.");
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
        return new Category(category);
    }

    @Override
    public Category updateCategory(Category category) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE categories SET areaM2 = ?, unitCost = ? WHERE catCode = ?");
            ps.setInt(1, category.getAreaM2());
            ps.setFloat(2, category.getUnitCost());
            ps.setString(3, category.getCatCode());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Category(category);
    }

    @Override
    public Category deleteCategory(String catCode) {
        Category category = getCategoryByCode(catCode);
        if (category == null) {
            return null;
        }
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM categories WHERE catCode = ?");
            ps.setString(1, catCode);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Category(category);
    }
}
