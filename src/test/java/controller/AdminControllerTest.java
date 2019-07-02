package controller;

import entities.Course;
import org.junit.Assert;
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

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class AdminControllerTest {


    private MockMvc mockMvc;

    @Autowired
    AdminController adminController;

    @Autowired
    LoginController loginController;

    @Autowired
    UserService userService;

    @Test
    public void createNewAccountTest(){

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
}
