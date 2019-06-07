package service;

import entities.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getById(int lessonId);

    boolean update(Lesson lesson);

    boolean save(Lesson lesson);

    List<Lesson> getLessonInWeek(int userId);

    List<Lesson> getLessonInMonth(int userId);
}
