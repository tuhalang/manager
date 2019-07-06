package dao;

import entities.User;
import entities.UserType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class UserDAOTest {

    private static final Logger logger = LogManager.getLogger(UserDAOTest.class.getName());

    @Autowired
    UserDAO userDAO;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("abcdefg");
        user.setPassword("1234567");
        user.setFullname("abcdefghijk");
        user.setEmail("abc@gmail.com");
        user.setPhone("1234567890");
        user.setAddress("Ninh Binh");
        user.setUserType(new UserType("admin"));
        user.setStatus(1);

        Assert.assertEquals(userDAO.save(user),true);

        User user1 = userDAO.getUserById(user.getUserId());

        Assert.assertEquals(user.getUserType().getType(),user1.getUserType().getType());
        Assert.assertEquals(user.getUsername(),user1.getUsername());
        Assert.assertEquals(user.getPassword(),user1.getPassword());
        Assert.assertEquals(user.getFullname(),user1.getFullname());
        Assert.assertEquals(user.getEmail(),user1.getEmail());
        Assert.assertEquals(user.getPhone(),user1.getPhone());
        Assert.assertEquals(user.getAddress(),user1.getAddress());
        Assert.assertEquals(user.getStatus(),user1.getStatus());

    }

    @Test
    public void saveUserExistTest(){
        User user = new User();
        user.setUsername("abcdef");
        user.setPassword("1234567");
        user.setFullname("abcdefghijk");
        user.setEmail("abc@gmail.com");
        user.setPhone("1234567890");
        user.setAddress("Ninh Binh");
        user.setUserType(new UserType("admin"));
        user.setStatus(1);

        Assert.assertEquals(userDAO.save(user),false);

        Assert.assertEquals(logger.getLevel(), Level.ERROR);

    }

    @Test
    public void updateTest(){
        User user = userDAO.getUserById(6);
        user.setUsername("abcdef");
        user.setPassword("1234567");
        user.setFullname("abcdefghijk");
        user.setEmail("abc@gmail.com");
        user.setPhone("1234567890");
        user.setAddress("Ninh Binh");
        user.setUserType(new UserType("admin"));
        user.setStatus(1);

        Assert.assertEquals(userDAO.update(user),true);

    }
}
