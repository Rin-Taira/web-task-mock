package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {

	private static final String SQL_SELECT_ID = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE product_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id ORDER BY product_id";
	private static final String SQL_SELECT_BY_KEYWORD = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE ? OR c.name LIKE ? ORDER BY product_id";
	private static final String SQL_SELECT_BY_KEYWORD_AND_ID = "SELECT * FROM products WHERE category_id = ? AND name LIKE ?";
	private static final String SQL_INSERT = "INSERT INTO products (product_id, category_id, name, price, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_UPDATE_BY_ID = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? WHERE product_id = ?";

	private Connection connection;

	public ProductDao(Connection connection) {
		this.connection = connection;
	}

	public Product findById(String id) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
			stmt.setInt(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteById(String id) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			stmt.setInt(1, Integer.parseInt(id));

			return stmt.executeUpdate();

		} catch (SQLException e) {
			return -1;
		}
	}
	
	public int UpdateById(String id, String name, String price, String category, String description) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
			stmt.setInt(1, Integer.parseInt(id));
			stmt.setString(2, name);
			stmt.setInt(3, Integer.parseInt(price));
			stmt.setInt(4, Integer.parseInt(category));
			stmt.setString(5, description);
			stmt.setInt(6, Integer.parseInt(id));

			return stmt.executeUpdate();

		} catch (SQLException e) {
			return -1;
		}
	}

	public List<Product> findAll() {
		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
				list.add(p);
			}	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Product> findByKeyword(String keyword) {
		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD)) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getInt("product_id"), 0, rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
				list.add(p);
			}	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	//    public List<Product> findByKeywordAndId(String keyword, int id) {
	//        List<Product> list = new ArrayList<Product>();
	//
	//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD_AND_ID)) {
	//        	stmt.setInt(1, id);
	//        	stmt.setString(2, "%" + keyword + "%");
	//            ResultSet rs = stmt.executeQuery();
	//
	//            while (rs.next()) {
	//            	try (Connection conn = DbUtil.getConnection()) {
	//            		CategoryDao categoryDao = new CategoryDao(conn);
	//            		String categoryName = categoryDao.findById(rs.getInt("category_id")).getName();
	//            		Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), categoryName, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
	//                    list.add(p);
	//            	} catch (Exception e) {
	//                    e.printStackTrace();
	//                }
	//            }	
	//        } catch (SQLException e) {
	//            throw new RuntimeException(e);
	//        }
	//        return list;
	//    }

	public int insert(Product product) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
			stmt.setInt(1, product.getProductId());
			stmt.setInt(2, product.getCategoryId());
			stmt.setString(3, product.getName());
			stmt.setInt(4, product.getPrice());
			stmt.setString(5, product.getDescription());
			stmt.setTimestamp(6, product.getCreatedDate());
			stmt.setTimestamp(7,  null);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}


//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import entity.Product;
//import util.DbUtil;
//
//public class ProductDao {
//
//    private static final String SQL_SELECT_ID = "SELECT * FROM products WHERE product_id = ?";
//    private static final String SQL_SELECT_ALL = "SELECT * FROM products";
//    private static final String SQL_SELECT_BY_KEYWORD = "SELECT * FROM products WHERE name LIKE ?";
//    private static final String SQL_SELECT_BY_KEYWORD_AND_ID = "SELECT * FROM products WHERE category_id = ? AND name LIKE ?";
//    private static final String SQL_INSERT = "INSERT INTO products (product_id, category_id, name, price, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?);";
//
//    private Connection connection;
//
//    public ProductDao(Connection connection) {
//        this.connection = connection;
//    }
//
//    public Product findById(String id) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
//        	stmt.setInt(1, Integer.parseInt(id));
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return new Product(rs.getInt("product_id"), rs.getInt("category_id"), null, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
//    
//    public List<Product> findAll() {
//        List<Product> list = new ArrayList<Product>();
//
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//            	try (Connection conn = DbUtil.getConnection()) {
//            		CategoryDao categoryDao = new CategoryDao(conn);
//            		String categoryName = categoryDao.findById(rs.getInt("category_id")).getName();
//            		Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), categoryName, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
//                    list.add(p);
//            	} catch (Exception e) {
//                    e.printStackTrace();
//                }
//            	
//            }	
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//    
//    public List<Product> findByKeyword(String keyword) {
//        List<Product> list = new ArrayList<Product>();
//
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD)) {
//        	stmt.setString(1, "%" + keyword + "%");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//            	try (Connection conn = DbUtil.getConnection()) {
//            		CategoryDao categoryDao = new CategoryDao(conn);
//            		String categoryName = categoryDao.findById(rs.getInt("category_id")).getName();
//            		Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), categoryName, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
//                    list.add(p);
//            	} catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }	
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//    
//    public List<Product> findByKeywordAndId(String keyword, int id) {
//        List<Product> list = new ArrayList<Product>();
//
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD_AND_ID)) {
//        	stmt.setInt(1, id);
//        	stmt.setString(2, "%" + keyword + "%");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//            	try (Connection conn = DbUtil.getConnection()) {
//            		CategoryDao categoryDao = new CategoryDao(conn);
//            		String categoryName = categoryDao.findById(rs.getInt("category_id")).getName();
//            		Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), categoryName, rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
//                    list.add(p);
//            	} catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }	
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//    
//    public int insert(Product product) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
//            stmt.setInt(1, product.getProductId());
//            stmt.setInt(2, product.getCategoryId());
//            stmt.setString(3, product.getName());
//            stmt.setInt(4, product.getPrice());
//            stmt.setString(5, product.getDescription());
//            stmt.setTimestamp(6, product.getCreatedDate());
//            stmt.setTimestamp(7,  null);
//
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
//    
//}
