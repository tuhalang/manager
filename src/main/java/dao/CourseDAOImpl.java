package dao;

import entities.Course;
import entities.CourseType;
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
public class CourseDAOImpl implements CourseDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CourseTypeDAO courseTypeDAO;

    @Transactional
    @Override
    public List<Course> getAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Course";
            Query<Course> query = session.createQuery(hql);
            List<Course> listCourse = query.getResultList();
            return listCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public List<Course> getByType(int typeId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Course where course_type_id= :type_id";
            Query<Course> query = session.createQuery(hql);
            query.setParameter("type_id", typeId);
            List<Course> listCourse = query.getResultList();
            return listCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public Course getById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Course where course_id= :id";
            Query<Course> query = session.createQuery(hql);
            query.setParameter("id", id);
            Course course = query.getSingleResult();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public boolean update(Course course) {
        try {
            Session session = sessionFactory.getCurrentSession();
            CourseType courseType = courseTypeDAO.getByType(course.getCourseType().getType());
            if (courseType != null) {
                course.setCourseType(courseType);
                session.update(course);
                return true;
            } else {
                session.save(course.getCourseType());
                session.update(course);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    @Override
    public boolean save(Course course) {
        try {
            Session session = sessionFactory.getCurrentSession();
            CourseType courseType = courseTypeDAO.getByType(course.getCourseType().getType());
            course.setStatus(1);
            if (courseType != null) {
                course.setCourseType(courseType);
                session.save(course);
                return true;
            } else {
                session.save(course.getCourseType());
                session.save(course);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
