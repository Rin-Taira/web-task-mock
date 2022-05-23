package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {

    private static final String SQL_SELECT_ID_AND_PASS = "SELECT * FROM users WHERE login_id = ? AND password = ?";
    private static final String SQL_SELECT_ALL = "SELECT user_id, user_name, password FROM users";

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findByIdAndPass(String id, String pass) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_AND_PASS)) {
        	stmt.setString(1, id);
            stmt.setString(2, pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
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
