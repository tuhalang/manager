package controller;

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
import org.springframework.test.web.servlet.MvcResult;
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

    @Autowired
    StudentController studentController;
    @Autowired
    LoginController loginController;
    private MockMvc mockMvc;
    private Logger logger;

    @Before
    public void setup() {
        logger = LogManager.getLogger(StudentControllerTest.class.getName());
    }

    @Test
    public void createScheduleTypeNotANumberTest() {
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
            MvcResult result = mockMvc.perform(get("/api/load_schedule")
                    .session((MockHttpSession) session)
                    .param("type", "not a number"))
                    .andExpect(status().isOk())
                    .andReturn();

            Assert.assertEquals(logger.getLevel(), Level.ERROR);

            String response = result.getResponse().getContentAsString();
            Assert.assertEquals(response, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createScheduleTypeANumberTest() {
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
            MvcResult result = mockMvc.perform(get("/api/load_schedule")
                    .session((MockHttpSession) session)
                    .param("type", "1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertNotEquals(response, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void enroll() {
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
            MvcResult result = mockMvc.perform(get("/api/enroll")
                    .session((MockHttpSession) session)
                    .param("courseId", "1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertEquals(response, "Enroll successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void enrollNotHaveCourse() {
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
            MvcResult result = mockMvc.perform(get("/api/enroll")
                    .session((MockHttpSession) session)
                    .param("courseId", "100"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertEquals(response, "Not exists course");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void enrollUserHaveNoMail() {
        try {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
            HttpSession session = mockMvc.perform(post("/login")
                    .param("username", "student2")
                    .param("password", "123456"))
                    .andExpect(redirectedUrl("student"))
                    .andReturn()
                    .getRequest()
                    .getSession();

            Assert.assertNotNull(session);

            this.mockMvc = MockMvcBuilders.standaloneSetup(this.studentController).build();
            MvcResult result = mockMvc.perform(get("/api/enroll")
                    .session((MockHttpSession) session)
                    .param("courseId", "1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertEquals(response, "Enroll successful! but can't send mail for student");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void enrollTeacherHaveNoMail() {
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
            MvcResult result = mockMvc.perform(get("/api/enroll")
                    .session((MockHttpSession) session)
                    .param("courseId", "2"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertEquals("Enroll successful! but can't send mail for teacher", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void enrolled() {
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
            MvcResult result = mockMvc.perform(get("/api/enroll")
                    .session((MockHttpSession) session)
                    .param("courseId", "6"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();


            String response = result.getResponse().getContentAsString();
            Assert.assertEquals("you enrolled", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
