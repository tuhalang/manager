package dao;

import entities.UserType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserTypeDAOImpl implements UserTypeDAO {


    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public UserType getByName(String type) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from UserType where type=:type";
            Query<UserType> query = session.createQuery(hql);
            query.setParameter("type", type);
            UserType userType = query.getSingleResult();
            return userType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    @Override
    public boolean save(UserType userType) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(userType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
