package controller;

import entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
@Transactional
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
    }

    @Test
    public void loginSuccessTest(){
        try {
            mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "1234567"))
                    .andExpect(redirectedUrl("admin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginFailedTest(){
        try {
            mockMvc.perform(post("/login")
                    .param("username", "admin")
                    .param("password", "123456"))
                    .andExpect(redirectedUrl("login"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerTest(){
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
        try {
            mockMvc.perform(post("/submit-register")
                    .param("username", "junit")
                    .param("password", "1234567")
                    .param("fullname", "Junit Test")
                    .flashAttr("user", new User()))
                    .andExpect(forwardedUrl("login"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
