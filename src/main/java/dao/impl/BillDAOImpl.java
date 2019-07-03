package dao.impl;

import dao.BillDAO;
import entities.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDAOImpl implements BillDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public boolean save(Bill bill) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.save(bill);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
