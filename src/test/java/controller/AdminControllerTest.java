package controller;

import entities.Course;
import entities.User;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.UserService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class AdminControllerTest {


    private MockMvc mockMvc;
    private Logger logger;

    @Autowired
    AdminController adminController;

    @Autowired
    LoginController loginController;

    @Autowired
    UserService userService;

    @Before
    public void setup(){
        logger = LogManager.getLogger(AdminControllerTest.class.getName());
    }

    @Test
    public void createNewAccountTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("password", "1234567")
                    .param("fullname", "Junit Tescher")
                    .param("phone", "234567890")
                    .flashAttr("newUser", new User()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountMissingEmailTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("password", "1234567")
                    .param("fullname", "Junit Tescher")
                    .param("phone", "234567890")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountMissingUsernameTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)

                    .param("password", "1234567")
                    .param("fullname", "Junit Tescher")
                    .param("phone", "234567890")
                    .param("email", "abc@gmail.com")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountMissingPasswordTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("fullname", "Junit Tescher")
                    .param("phone", "234567890")
                    .param("email", "abc@gmail.com")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountMissingFullnameTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("password", "1234567")
                    .param("phone", "234567890")
                    .param("email", "abc@gmail.com")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountMissingPhoneTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("password", "1234567")
                    .param("fullname", "Junit Tescher")
                    .param("email", "abc@gmail.com")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewAccountPassShortTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_account")
                    .session((MockHttpSession) session)
                    .param("username", "junit-teacher")
                    .param("password", "1234")
                    .param("fullname", "Junit Tescher")
                    .param("email", "abc@gmail.com")
                    .param("phone", "1234567890")
                    .flashAttr("newUser", new User()));
            Assert.assertEquals(Level.ERROR, logger.getLevel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewCourseTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_course")
                    .session((MockHttpSession) session)
                    .param("courseName","Junit Test")
                    .param("numOfLesson","10")
                    .param("startDate","2019-06-05")
                    .param("endDate", "2019-06-10")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar")
                    .flashAttr("course", new Course()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewCourseMissNameTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_course")
                    .session((MockHttpSession) session)
                    .param("numOfLesson","10")
                    .param("startDate","2019-06-05")
                    .param("endDate", "2019-06-10")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar"));

            Assert.assertEquals(logger.getLevel(),Level.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewCourseMinusNumTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_course")
                    .session((MockHttpSession) session)
                    .param("courseName","Junit Test")
                    .param("numOfLesson","-4")
                    .param("startDate","2019-06-05")
                    .param("endDate", "2019-06-10")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar"));

            Assert.assertEquals(logger.getLevel(),Level.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void createNewCourseDateTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_course")
                    .session((MockHttpSession) session)
                    .param("courseName","Junit Test")
                    .param("numOfLesson","10")
                    .param("endDate", "2019-06-10")
                    .param("startDate", "2019-06-6")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar")
                    .flashAttr("course", new Course()));

            Assert.assertEquals(logger.getLevel(),Level.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void createNewCourseMissDateTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(post("/api/create_new_course")
                    .session((MockHttpSession) session)
                    .param("courseName","Junit Test")
                    .param("numOfLesson","10")
                    .param("endDate", "2019-06-10")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar")
                    .flashAttr("course", new Course()));

            Assert.assertEquals(logger.getLevel(),Level.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @Test
    public void payment(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.adminController).build();
            mockMvc.perform(get("/api/payment")
                    .session((MockHttpSession) session)
                    .param("paid","100")
                    .param("userId","6"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
