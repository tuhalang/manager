package service.impl;


import dao.UserDAO;
import entities.User;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.UserService;

import java.util.List;

@Controller
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean save(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public User findByAccount(String username, String password) {
        return userDAO.findByAccount(username, password);
    }

    @Override
    public User getUserById(int userId) {

        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> searchByName(int userTypeId, String name) {
        return userDAO.searchByName(userTypeId,name);
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    @Override
    public boolean saveListUser(List<User> listUser, UserType userType) {
        return userDAO.saveListUser(listUser, userType);
    }

    @Override
    public List<User> getAllUser(UserType userType) {
        return userDAO.getAllUser(userType);
    }


}
