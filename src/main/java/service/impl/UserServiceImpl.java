package service.impl;


import dao.UserDAO;
import entities.User;
import entities.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.UserService;

import java.util.List;

@Controller
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());


    @Autowired
    UserDAO userDAO;

    @Override
    public boolean validate(User user) {
        if(user.getUserType() == null){
            logger.error("UserType null");
            return false;
        }
        if(user.getUsername().equalsIgnoreCase("")){
            logger.error("username null");
            return false;
        }
        if(user.getPassword().length()<6){
            logger.error("password too short");
            return false;
        }
        if(user.getEmail() == null || !user.getEmail().matches("^(.+)@(.+)$")){
            logger.error("invalid email");
            logger.error(user.getEmail());
            return false;
        }
        if (user.getPhone() == null  || !user.getPhone().matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")){
            logger.error(user.getPhone());
            logger.error("invalid phone");
            return false;
        }
        return true;
    }

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
