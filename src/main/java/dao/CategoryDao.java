package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;

public class CategoryDao {

    private static final String SQL_SELECT_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String SQL_SELECT_BY_KEYWORD = "SELECT * FROM categories WHERE name LIKE ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM categories";

    private Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;
    }

    public Category findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
        	stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Category findByName(String name) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
        	stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<Category> findByKeyword(String keyword) {
        List<Category> list = new ArrayList<Category>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD)) {
        	stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Category c = new Category(rs.getInt("id"), rs.getString("name"));
                list.add(c);
            }	
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
