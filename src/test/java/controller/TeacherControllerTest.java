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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class TeacherControllerTest {

    @Autowired
    LoginController loginController;

    @Autowired
    TeacherController teacherController;

    MockMvc mockMvc;

    @Test
    public void updateCourseTest(){
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "teacher1")
                    .param("password", "123456"))
                    .andExpect(redirectedUrl("teacher"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.teacherController).build();
            mockMvc.perform(post("/update_course")
                    .session((MockHttpSession) session)
                    .param("courseId","1")
                    .param("courseName","Junit Test")
                    .param("numOfLesson","10")
                    .param("startDate","2019-06-05")
                    .param("endDate", "2019-06-10")
                    .param("fee","0")
                    .param("promotion","0")
                    .param("courseType","seminar")
                    .param("status","1")
                    .flashAttr("course", new Course()))
                    .andExpect(model().attribute("success", equalTo("Update successfully !!!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
