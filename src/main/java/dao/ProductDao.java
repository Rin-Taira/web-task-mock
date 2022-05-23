package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import util.DbUtil;

public class ProductDao {

    private static final String SQL_SELECT_ID_AND_PASS = "SELECT * FROM users WHERE login_id = ? AND password = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM products";

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

//    public User findByIdAndPass(String id, String pass) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_AND_PASS)) {
//        	stmt.setString(1, id);
//            stmt.setString(2, pass);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    
    public List<Product> findAll() {
        List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	try (Connection conn = DbUtil.getConnection()) {
            		CategoryDao categoryDao = new CategoryDao(conn);
            		String category = categoryDao.findById(rs.getInt("category_id")).getName();
            		Product u = new Product(rs.getInt("product_id"), category, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                    list.add(u);
            	} catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
