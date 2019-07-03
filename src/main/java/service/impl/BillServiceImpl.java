package service.impl;

import dao.BillDAO;
import entities.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import service.BillService;

@Controller
public class BillServiceImpl implements BillService {

    @Autowired
    BillDAO billDAO;

    @Override
    public boolean save(Bill bill) {
        return billDAO.save(bill);
    }
}
