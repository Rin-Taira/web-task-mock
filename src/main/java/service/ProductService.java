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

	public Product findById(String id) {
		try (Connection conn = DbUtil.getConnection()) {
			ProductDao productDao = new ProductDao(conn);
			return productDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int deleteById(String id) {
		try (Connection conn = DbUtil.getConnection()) {
			ProductDao productDao = new ProductDao(conn);
			return productDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int UpdateById(String id, String name, String price, String category, String description, String nowId) {
		try (Connection conn = DbUtil.getConnection()) {
			ProductDao productDao = new ProductDao(conn);
			return productDao.UpdateById(id, name, price, category, description, nowId);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
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