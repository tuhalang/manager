package dao.impl;

import dao.CourseTypeDAO;
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
public class CourseTypeDAOImpl implements CourseTypeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<CourseType> getAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<CourseType> query = session.createQuery("from CourseType");
            List<CourseType> listType = query.getResultList();
            return listType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public CourseType getById(int typeId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from CourseType where course_type_id=:typeId";
            Query<CourseType> query = session.createQuery(hql);
            query.setParameter("typeId", typeId);
            CourseType courseType = query.getSingleResult();
            return courseType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public CourseType getByType(String type) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from CourseType where type=:type";
            Query<CourseType> query = session.createQuery(hql);
            query.setParameter("type", type);
            CourseType courseType = query.getSingleResult();
            return courseType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
