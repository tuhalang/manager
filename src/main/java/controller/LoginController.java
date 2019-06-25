package controller;

import entities.User;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.UserTypeService;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserTypeService userTypeService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String Default() {
        return "login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session, SessionStatus status) {
        status.setComplete();
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(ModelMap map, @RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        User user = userService.findByAccount(username, password);

        if (user != null) {
            if(user.getStatus()==1){
                map.addAttribute("user", user);
                System.out.println(user);
                String page = "redirect:"+user.getUserType().getType();
                return page;
            }else {
                map.addAttribute("error", "Tài khoản của bạn đã bị khóa vui lòng liên hệ quản trị viên để được giải quyết!");
                return "login";
            }

        }
        map.addAttribute("error", "username or password incorrect!");
        return "login";
    }

    @RequestMapping(value = "submit-register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        UserType userType = userTypeService.getByName("student");
        user.setUserType(userType);
        user.setStatus(1);
        if (!result.hasErrors()) {
            if (userService.save(user)) {
                model.addAttribute("info", "register successfully!, please login!");
                return "login";
            } else {
                model.addAttribute("info", "register fail!");
                return "register";
            }
        } else {
            model.addAttribute("info", "register fail!");
            return "register";
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


}
