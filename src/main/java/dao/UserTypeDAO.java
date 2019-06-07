package dao;

import entities.UserType;

public interface UserTypeDAO {
    UserType getByName(String type);

    boolean save(UserType userType);
}
