package service;

import java.sql.Connection;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class UserService {

    public User authentication(String id, String pass) {
        try (Connection conn = DbUtil.getConnection()) {
            UserDao userDao = new UserDao(conn);
            User user = userDao.findByIdAndPass(id, pass);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//    public List<User> find() {
//        try (Connection conn = DbUtil.getConnection()) {
//            UserDao userDao = new UserDao(conn);
//            return userDao.findAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }

}

