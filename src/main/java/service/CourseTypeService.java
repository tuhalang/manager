package service;

import entities.CourseType;

import java.util.List;

public interface CourseTypeService {
    List<CourseType> getAll();

    CourseType getById(int typeId);

    CourseType getByType(String type);
}
