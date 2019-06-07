package dao;

import entities.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LessonDAOImpl implements LessonDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public Lesson getById(int lessonId) {
        try {
            String hql = "from Lesson where lesson_id=:lessonId";
            Session session = sessionFactory.getCurrentSession();
            Query<Lesson> query = session.createQuery(hql);
            query.setParameter("lessonId", lessonId);
            Lesson lesson = query.getSingleResult();
            return lesson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public boolean update(Lesson lesson) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    @Override
    public boolean save(Lesson lesson) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    @Override
    public List<Lesson> getLessonInWeek(int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "SELECT l FROM Lesson l JOIN l.listUsers u where u.userId = :userId " +
                    "and yearweek(l.date) = yearweek(curdate()) order by l.date";
            Query<Lesson> query = session.createQuery(hql);
            query.setParameter("userId", userId);
            List<Lesson> listLessons = query.getResultList();
            return listLessons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public List<Lesson> getLessonInMonth(int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "SELECT l FROM Lesson l JOIN l.listUsers u where u.userId = :userId " +
                    "and month(l.date) = month(current_date()) and year(l.date) = year(current_date()) order by l.date";
            Query<Lesson> query = session.createQuery(hql);
            query.setParameter("userId", userId);
            List<Lesson> listLessons = query.getResultList();
            return listLessons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
