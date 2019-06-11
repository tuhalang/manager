package controller;

import entities.Course;
import entities.CourseType;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.CourseTypeService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class TeacherController {

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    public String Default(HttpSession httpSession, ModelMap map) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("teacher")) {
            map.addAttribute("courses", user.getListCourses());
            return "teacher";
        }
        return "index";
    }

    @RequestMapping(value = "new_course", method = RequestMethod.GET)
    public ModelAndView newCourse(HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("teacher")) {
            ModelAndView modelAndView = new ModelAndView("new_course");
            modelAndView.addObject("course", new Course());
            List<CourseType> listCourseTypes = courseTypeService.getAll();
            modelAndView.addObject("courseTypes", listCourseTypes);
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "add_course", method = RequestMethod.POST)
    public String submit(@ModelAttribute("course") Course course, BindingResult result, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("teacher")) {
            courseService.save(course);
            Set<Course> courses = user.getListCourses();
            courses.add(course);
            user.setListCourses(courses);
            userService.update(user);
            return "redirect:teacher";
        } else {
            return "index";
        }

    }


    @RequestMapping(value = "edit_course", method = RequestMethod.GET)
    public ModelAndView editCourse(HttpSession httpSession, @RequestParam("id") int id, ModelMap map) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("teacher")) {
            Course course = courseService.getById(id);
            ModelAndView modelAndView = new ModelAndView("edit_course");
            modelAndView.addObject("course", course);

            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "update_course", method = RequestMethod.POST)
    public String updateCourse(HttpSession httpSession, @ModelAttribute("course") Course course,
                               BindingResult result, ModelMap model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("teacher")) {
            if (!result.hasErrors()) {
                Course course1 = courseService.getById(course.getCourseId());
                course.setListUsers(course1.getListUsers());
                course.setListLessons(course1.getListLessons());
                if (courseService.update(course)) {
                    model.addAttribute("success", "Update successfully !!!");
                    return "redirect:edit_course?id=" + course.getCourseId();
                }

                model.addAttribute("error", "Can't update, please try again !!!");
                return "redirect:edit_course?id=" + course.getCourseId();
            } else {
                return "index";
            }
        }
        return "index";
    }


}
