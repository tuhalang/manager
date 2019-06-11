package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Course;
import entities.CourseType;
import entities.User;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CourseService;
import service.CourseTypeService;
import service.UserService;
import service.UserTypeService;
import utils.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    UserService userService;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String Default(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            return "admin";
        }
        return "index";
    }

    @RequestMapping(value = "admin/teacher", method = RequestMethod.GET)
    public String loadPageTeacher(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            UserType userType = userTypeService.getByName("teacher");
            List<User> listUsers = userService.getAllUser(userType);
            modelMap.addAttribute("teachers", listUsers);
            return "admin_teacher";
        }
        return "index";
    }

    @RequestMapping(value = "admin/student", method = RequestMethod.GET)
    public String loadPageStudent(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            UserType userType = userTypeService.getByName("student");
            List<User> listUsers = userService.getAllUser(userType);
            modelMap.addAttribute("students", listUsers);
            return "admin_student";
        }
        return "index";
    }

    @RequestMapping(value = "api/load_dropdown", method = RequestMethod.GET)
    @ResponseBody
    public String loadDropdown() {

        ObjectMapper mapper = new ObjectMapper();
        List<CourseType> listType = courseTypeService.getAll();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listType);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return ajaxResponse;
    }

    @RequestMapping(value = "api/load_course", method = RequestMethod.GET)
    @ResponseBody
    public String loadAllCourse(HttpServletRequest request) {
        int typeId = Integer.parseInt(request.getParameter("type_id"));
        int page = Integer.parseInt(request.getParameter("page"))-1;

        List<Course> listCourse = new ArrayList<Course>();

        if (typeId == 0) {
            listCourse = courseService.getAll(page*8,8);

        } else {
            listCourse = courseService.getByType(typeId,page*8,8);
        }
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listCourse);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_total_course", method = RequestMethod.GET)
    @ResponseBody
    public String getTotalCourse(HttpServletRequest request) {
        int typeId = Integer.parseInt(request.getParameter("type_id"));

        long total=0;

        if (typeId == 0) {
            total = courseService.getTotal();

        } else {
            total = courseService.getTotalByType(typeId);
        }

        return total +"";
    }

    @RequestMapping(value = "api/load_student", method = RequestMethod.GET)
    @ResponseBody
    public String loadAllStudent() {
        ObjectMapper mapper = new ObjectMapper();
        UserType userType = userTypeService.getByName("student");
        List<User> listStudents = userService.getAllUser(userType);
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listStudents);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_list_course_by_students", method = RequestMethod.GET)
    @ResponseBody
    public String getListCourseByStudents(HttpServletRequest request) {
        String[] listId = request.getParameter("list_id").split(",");
        int page = Integer.parseInt(request.getParameter("page")) - 1;
        Set<Course> listCourses = new HashSet<Course>();
        for (String id : listId) {
            int userId = Integer.parseInt(id);
            listCourses.addAll(userService.getUserById(userId).getListCourses());
        }
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(new Collection<Course>().subCollection(
                    new ArrayList<>(listCourses),page*8, 8));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_total_course_by_students", method = RequestMethod.GET)
    @ResponseBody
    public String getTotalCourseByStudents(HttpServletRequest request) {
        String[] listId = request.getParameter("list_id").split(",");
        Set<Course> listCourses = new HashSet<Course>();
        for (String id : listId) {
            int userId = Integer.parseInt(id);
            listCourses.addAll(userService.getUserById(userId).getListCourses());
        }
        return listCourses.size()+"";
    }


    @RequestMapping(value = "api/get_students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(HttpServletRequest request) {
        String[] listId = request.getParameter("list_id").split(",");
        String data = "[";
        for (int i = 0; i < listId.length; i++) {
            int userId = Integer.parseInt(listId[i]);
            User user = userService.getUserById(userId);
            String item = "{"
                    + "\"id\":" + user.getUserId() + ","
                    + "\"fullname\":\"" + user.getFullname() + "\","
                    + "\"totalTime\":" + user.totalTime()
                    + "}";
            data += item;
            if (i != listId.length - 1) data += ",";
        }
        data += "]";
        return data;
    }
}
