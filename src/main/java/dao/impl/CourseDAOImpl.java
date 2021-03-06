package dao.impl;

import dao.CourseDAO;
import dao.CourseTypeDAO;
import entities.Course;
import entities.CourseType;
import entities.User;
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
    public List<Course> getAll(int start, int max) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "select c as co from Course c join c.listUsers lu group by c order by count(lu) DESC";
            Query<Course> query = session.createQuery(hql);
            List<Course> listCourse = query.setFirstResult(start).setMaxResults(max).getResultList();
            return listCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public long getTotal() {
        try{
            Session session = sessionFactory.getCurrentSession();
            String hql = "select count(1) from Course";
            Query<Long> query = session.createQuery(hql);
            long total = query.getSingleResult();
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
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
    public List<Course> getByType(int typeId, int start, int max) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Course where course_type_id= :type_id";
            Query<Course> query = session.createQuery(hql);
            query.setParameter("type_id", typeId);
            List<Course> listCourse = query.setFirstResult(start).setMaxResults(max).getResultList();
            return listCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public long getTotalByType(int typeId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "select count(1) from Course where courseType.id=:type_id";
            Query<Long> query = session.createQuery(hql);
            query.setParameter("type_id", typeId);
            long total = query.getSingleResult();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    @Transactional(rollbackOn = Exception.class)
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

    @Transactional(rollbackOn = Exception.class)
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

    @Transactional
    @Override
    public List<Course> search(String type, String key, int start, int max) {
        try{
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Course where "+type+" like '%"+key+"%'";
            Query<Course> query = session.createQuery(hql);
            List<Course> courses = query.setFirstResult(start).setMaxResults(max).getResultList();
            return courses;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public List<Course> searchInUser(int userId, String key, int start, int max) {
        try{
            Session session = sessionFactory.getCurrentSession();
            String hql = "select c from Course c join c.listUsers u where u.userId="+userId+" and c.courseName like '%"+key+"%'";
            Query<Course> query = session.createQuery(hql);
            List<Course> courses = query.setFirstResult(start).setMaxResults(max).getResultList();
            return courses;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
