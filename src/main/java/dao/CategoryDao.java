package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Category;

public class CategoryDao {

    private static final String SQL_SELECT_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT user_id, user_name, password FROM users";

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
    
//    public List<User> findAll() {
//        List<User> list = new ArrayList<User>();
//
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                User u = new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("password"));
//                list.add(u);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
}
