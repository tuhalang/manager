package controller;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "account")
    public ModelAndView account(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user != null){
            ModelAndView modelAndView = new ModelAndView("account_"+user.getUserType().getType());
            modelAndView.addObject("account", user);
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @ResponseBody
    @RequestMapping(value = "api/update_user", method = RequestMethod.POST)
    public User updateUser(HttpSession httpSession, @ModelAttribute(name="account") User account, BindingResult result){
        User current_user = (User) httpSession.getAttribute("user");
        if(current_user != null && !result.hasErrors()){
            System.out.println(account.toString());
            current_user.setUsername(account.getUsername());
            current_user.setFullname(account.getFullname());
            current_user.setEmail(account.getEmail());
            current_user.setPhone(account.getPhone());
            current_user.setAddress(account.getAddress());
            if(userService.update(current_user)){
                return current_user;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "api/change_pass_user", method = RequestMethod.POST)
    public String changePass(HttpSession httpSession, HttpServletRequest request){
        User current_user = (User) httpSession.getAttribute("user");
        String old_pass = request.getParameter("old_pass");
        String new_pass = request.getParameter("new_pass");
        System.out.println(old_pass);
        System.out.println(new_pass);
        if(old_pass.equals(current_user.getPassword())){
            current_user.setPassword(new_pass);
            if(userService.update(current_user)){
                return "success";
            }
        }

        return "failed";
    }
}
