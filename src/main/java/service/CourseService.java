package service;

import entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    List<Course> getByType(int typeId);

    Course getById(int id);

    boolean update(Course course);

    boolean save(Course course);
}
