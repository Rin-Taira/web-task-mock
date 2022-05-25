package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

    public List<Product> find() {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Product> findByKeyword(String keyword) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Product> findByKeywordAndId(String keyword, int id) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findByKeywordAndId(keyword, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public Product findById(String id) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    
    public int insert(Product product) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.insert(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}



// 以下元データ
//package service;
//
//import java.sql.Connection;
//import java.util.Collections;
//import java.util.List;
//
//import dao.ProductDao;
//import entity.Product;
//import util.DbUtil;
//
//public class ProductService {
//
////    public User authentication(String id, String pass) {
////        try (Connection conn = DbUtil.getConnection()) {
////            UserDao userDao = new UserDao(conn);
////            User user = userDao.findByIdAndPass(id, pass);
////
////            return user;
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return null;
////    }
//
//    public List<Product> find() {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.findAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }
//    
//    public List<Product> findByKeyword(String keyword) {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.findByKeyword(keyword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }
//    
//    public List<Product> findByKeywordAndId(String keyword, int id) {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.findByKeywordAndId(keyword, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }
//    
//    public Product findById(String id) {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.findById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//    
//    
//    
//    public int insert(Product product) {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.insert(product);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return 0;
//    }
//
//}