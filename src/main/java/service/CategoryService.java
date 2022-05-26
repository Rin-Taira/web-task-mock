package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.CategoryDao;
import entity.Category;
import util.DbUtil;

public class CategoryService {

	public List<Category> find() {
        try (Connection conn = DbUtil.getConnection()) {
            CategoryDao categoryDao = new CategoryDao(conn);
            return categoryDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}

