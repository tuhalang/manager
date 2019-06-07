package dao;

import entities.Course;

import java.util.List;

public interface CourseDAO {
    List<Course> getAll();

    List<Course> getByType(int typeId);

    Course getById(int id);

    boolean update(Course course);

    boolean save(Course course);
}
