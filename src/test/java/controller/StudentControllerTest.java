package controller;

import entities.User;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    StudentController studentController;

    @Autowired
    LoginController loginController;

    @Test
    public void createScheduleTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "student1")
                    .param("password", "123456"))
                    .andExpect(redirectedUrl("student"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.studentController).build();
            mockMvc.perform(get("/api/load_schedule")
                    .session((MockHttpSession) session)
                    .param("type","0"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
