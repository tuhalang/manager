package service.impl;

import dao.CourseDAO;
import entities.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.CourseService;

import java.util.List;

@Controller
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class.getName());

    @Autowired
    CourseDAO courseDAO;

    @Override
    public boolean validate(Course course) {
        if(course.getCourseName().equalsIgnoreCase("")){
            logger.error("invalid name");
            return false;
        }

        if(course.getFee() < 0){
            logger.error("invalid fee");
            return false;
        }

        if(course.getStartDate() == null || course.getEndDate() == null){
            logger.error("date null");
        }

        if(course.getStartDate().getTime() > course.getEndDate().getTime()){
            logger.error("invalid date");
            return false;
        }

        if (course.getCourseType() == null){
            logger.error("CourseType null");
            return false;
        }

        return true;
    }

    @Override
    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    @Override
    public List<Course> getAll(int start, int max) {
        return courseDAO.getAll(start,max);
    }

    @Override
    public long getTotal() {
        return courseDAO.getTotal();
    }

    @Override
    public List<Course> getByType(int typeId) {
        return courseDAO.getByType(typeId);
    }

    @Override
    public List<Course> getByType(int typeId, int start, int max) {
        return courseDAO.getByType(typeId, start, max);
    }

    @Override
    public long getTotalByType(int typeId) {
        return courseDAO.getTotalByType(typeId);
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

    @Override
    public List<Course> search(String type, String key, int start, int max) {
        return courseDAO.search(type, key, start, max);
    }

    @Override
    public List<Course> searchInUser(int userId, String key, int start, int max) {
        return courseDAO.searchInUser(userId,key,start,max);
    }


}
