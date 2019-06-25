package service.impl;

import dao.LessonDAO;
import entities.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.LessonService;

import java.util.List;

@Controller
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonDAO lessonDAO;

    @Override
    public Lesson getById(int lessonId) {
        return lessonDAO.getById(lessonId);
    }

    @Override
    public boolean update(Lesson lesson) {
        return lessonDAO.update(lesson);
    }

    @Override
    public boolean save(Lesson lesson) {
        return lessonDAO.save(lesson);
    }

    @Override
    public List<Lesson> getLessonInWeek(int userId) {
        return lessonDAO.getLessonInWeek(userId);
    }

    @Override
    public List<Lesson> getLessonInMonth(int userId) {
        return lessonDAO.getLessonInMonth(userId);
    }
}
