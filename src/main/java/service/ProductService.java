package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

//    public User authentication(String id, String pass) {
//        try (Connection conn = DbUtil.getConnection()) {
//            UserDao userDao = new UserDao(conn);
//            User user = userDao.findByIdAndPass(id, pass);
//
//            return user;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public List<Product> find() {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}