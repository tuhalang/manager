package controller;

import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String Default(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            return "admin";
        }
        return "home";
    }
}
