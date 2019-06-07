package dao;

import entities.User;
import entities.UserType;
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
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserTypeDAO userTypeDAO;

    @Transactional
    @Override
    public boolean save(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            if (!isExist(user.getUsername())) {
                UserType userType = userTypeDAO.getByName(user.getUserType().getType());
                if (userType != null) {
                    user.setUserType(userType);
                    session.save(user);
                    return true;
                } else {
                    session.save(user.getUserType());
                    session.save(user);
                    return true;
                }

            } else {
                System.out.println("Username is exist!");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Transactional
    @Override
    public boolean update(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Transactional
    @Override
    public boolean delete(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Transactional
    @Override
    public User findByAccount(String username, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User where username= :username and password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User where user_id= :userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", id);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Transactional
    @Override
    public boolean saveListUser(List<User> listUser, UserType userType) {
        try {
            Session session = sessionFactory.getCurrentSession();
            for (User user : listUser) {
                user.setUserType(userType);
                save(user);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Transactional
    @Override
    public List<User> getAllUser(UserType userType) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User where user_type_id=:userTypeId";
            Query<User> query = session.createQuery(hql);
            query.setParameter("userTypeId", userType.getUserTypeId());
            List<User> listUser = query.getResultList();
            return listUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Transactional
    @Override
    public boolean isExist(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "select count(1) from User where username=:username";
            Query<Long> query = session.createQuery(hql);
            query.setParameter("username", username);
            return query.getSingleResult() != 0l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}
