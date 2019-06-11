package dao;

import entities.Course;

import java.util.List;

public interface CourseDAO {

    List<Course> getAll();
    List<Course> getAll(int start, int max);
    long getTotal();

    List<Course> getByType(int typeId);
    List<Course> getByType(int typeId, int start, int max);
    long getTotalByType(int typeId);

    Course getById(int id);

    boolean update(Course course);

    boolean save(Course course);
}
