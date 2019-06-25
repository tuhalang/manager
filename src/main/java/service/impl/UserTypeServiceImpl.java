package service.impl;


import dao.UserTypeDAO;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.UserTypeService;

@Controller
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    UserTypeDAO userTypeDAO;

    @Override
    public UserType getByName(String type) {
        return userTypeDAO.getByName(type);
    }

    @Override
    public boolean save(UserType userType) {
        return userTypeDAO.save(userType);
    }

}
