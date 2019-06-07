package service;

import dao.CourseDAO;
import entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Override
    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    @Override
    public List<Course> getByType(int typeId) {
        return courseDAO.getByType(typeId);
    }

    @Override
    public Course getById(int id) {
        return courseDAO.getById(id);
    }

    @Override
    public boolean update(Course course) {
        return courseDAO.update(course);
    }

    @Override
    public boolean save(Course course) {
        return courseDAO.save(course);
    }

}
