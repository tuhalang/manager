package service;

import entities.UserType;

public interface UserTypeService {
    UserType getByName(String type);

    boolean save(UserType userType);
}
