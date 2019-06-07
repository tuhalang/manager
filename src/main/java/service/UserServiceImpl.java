package service;


import dao.UserDAO;
import entities.User;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    public boolean saveListUser(List<User> listUser, UserType userType) {
        return userDAO.saveListUser(listUser, userType);
    }

    @Override
    public List<User> getAllUser(UserType userType) {
        return userDAO.getAllUser(userType);
    }


}
