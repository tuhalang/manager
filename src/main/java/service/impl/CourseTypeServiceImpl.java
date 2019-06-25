package service.impl;

import dao.CourseTypeDAO;
import entities.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.CourseTypeService;

import java.util.List;

@Controller
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    CourseTypeDAO courseTypeDAO;

    @Override
    public List<CourseType> getAll() {
        return courseTypeDAO.getAll();
    }

    @Override
    public CourseType getById(int typeId) {
        return courseTypeDAO.getById(typeId);
    }

    @Override
    public CourseType getByType(String type) {
        return courseTypeDAO.getByType(type);
    }

}
