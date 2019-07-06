package service;

import entities.User;
import entities.UserType;

import java.util.List;

public interface UserService {
    boolean validate(User user);

    boolean save(User user);

    boolean update(User user);

    boolean delete(User user);

    User findByAccount(String username, String password);

    boolean saveListUser(List<User> listUser, UserType userType);

    List<User> getAllUser(UserType userType);

    User getUserById(int userId);

    List<User> searchByName(int userTypeId, String name);

    User getByUsername(String username);

}
