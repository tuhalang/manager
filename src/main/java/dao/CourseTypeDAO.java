package dao;

import entities.CourseType;

import java.util.List;

public interface CourseTypeDAO {
    List<CourseType> getAll();

    CourseType getById(int typeId);

    CourseType getByType(String type);
}
