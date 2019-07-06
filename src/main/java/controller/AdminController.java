package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import utils.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class.getName());

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    UserService userService;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    CourseService courseService;

    @Autowired
    BillService billService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String Default(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            return "admin";
        }
        return "index";
    }

    @RequestMapping(value = "admin/fee", method = RequestMethod.GET)
    public String payment(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            return "payment";
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
            modelMap.addAttribute("newUser", new User());
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
            modelMap.addAttribute("newUser", new User());
            return "admin_student";
        }
        return "index";
    }

    @RequestMapping(value = "admin/create_new", method = RequestMethod.GET)
    public ModelAndView loadPageCreateNew(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {
            ModelAndView modelAndView = new ModelAndView("admin_create_new");
            modelAndView.addObject("course", new Course());
            modelAndView.addObject("newUser", new User());
            List<CourseType> listCourseTypes = courseTypeService.getAll();
            modelAndView.addObject("courseTypes", listCourseTypes);
            UserType userType = userTypeService.getByName("teacher");
            List<User> teachers = userService.getAllUser(userType);
            modelAndView.addObject("teachers", teachers);
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "api/create_new_course", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Course createNewCourse(HttpSession httpSession, @ModelAttribute Course course, BindingResult result) {
        User user = (User) httpSession.getAttribute("user");
        if(user.getUserType().getType().equalsIgnoreCase("admin")){
            if(!result.hasErrors()){
                if (courseService.validate(course) && courseService.save(course)) {
                    return course;
                }
            }
            else {
                logger.error(result.getFieldErrors());
            }
        }
        return null;
    }

    @RequestMapping(value = "api/create_new_account", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public User createNewAccount(HttpSession httpSession, @ModelAttribute User newUser, BindingResult result) {
        User user = (User) httpSession.getAttribute("user");
        if(user.getUserType().getType().equalsIgnoreCase("admin")){
            if(!result.hasErrors()){
                UserType userType = userTypeService.getByName("teacher");
                newUser.setEmail(newUser.getUsername()+"@gmail.com");
                newUser.setPhone("0123456789");
                newUser.setStatus(1);
                newUser.setUserType(userType);
                if(userService.validate(newUser) && userService.save(newUser)){
                    return newUser;
                }
            }else{
                logger.error(result.getFieldErrors());
            }
        }
        return null;
    }

    @RequestMapping(value = "api/add_teacher", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addTeacher(HttpSession httpSession, HttpServletRequest request) {
        User user = (User) httpSession.getAttribute("user");
        if(user.getUserType().getType().equalsIgnoreCase("admin")){
            int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
            int courseId = Integer.parseInt(request.getParameter("course_id"));
            User teacher = userService.getUserById(teacherId);
            Set<User> users = new HashSet<>();
            users.add(teacher);
            Course course = courseService.getById(courseId);
            course.setListUsers(users);
            if(courseService.update(course)){
                return "success";
            }
        }
        return "failed";
    }


    @RequestMapping(value = "api/load_dropdown", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadDropdown() {

        ObjectMapper mapper = new ObjectMapper();
        List<CourseType> listType = courseTypeService.getAll();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listType);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        return ajaxResponse;
    }

    @RequestMapping(value = "api/load_course", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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
            logger.error(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_total_course", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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

    @RequestMapping(value = "api/load_student", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadAllStudent() {
        ObjectMapper mapper = new ObjectMapper();
        UserType userType = userTypeService.getByName("student");
        List<User> listStudents = userService.getAllUser(userType);
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listStudents);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_list_course_by_students", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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
            logger.error(e.getMessage());;
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/get_total_course_by_students", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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


    @RequestMapping(value = "api/get_students", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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

    @RequestMapping(value = "api/block_user", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String blockUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int status = Integer.parseInt(request.getParameter("status"));

        User user = userService.getUserById(userId);
        user.setStatus(status);

        if(userService.update(user))
            return "success";
        return "failed";
    }

    @RequestMapping(value = "api/search_user", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String searchUser(HttpServletRequest request) {
        int userTypeId = Integer.parseInt(request.getParameter("userTypeId"));
        String name = request.getParameter("name");
        List<User> listUsers = null;
        UserType userType = null;
        if(userTypeId == 2){
            userType = userTypeService.getByName("teacher");
        }
        if(userTypeId == 3){
            userType = userTypeService.getByName("student");
        }
        if(name.trim().equalsIgnoreCase("")){
            listUsers = userService.getAllUser(userType);
        }else{
            listUsers = userService.searchByName(userTypeId, name);
        }
        return listUsers.toString();
    }


    @RequestMapping(value = "api/payment/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadPaymentList(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        return user.getListCourses().toString();
    }

    @RequestMapping(value = "api/payment/history", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadPaymentHistory(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        return user.getListBills().toString();
    }

    @RequestMapping(value = "api/payment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String payment(HttpSession httpSession,HttpServletRequest request){
        User admin = (User) httpSession.getAttribute("user");
        if(admin.getUserType().getType().equalsIgnoreCase("admin")){
            String strUserId = request.getParameter("userId");
            String strPaid = request.getParameter("paid");

            if(strPaid.equalsIgnoreCase("") || strUserId.equalsIgnoreCase("")){
                logger.error("field null");
                return "field null";
            }
            int userId=-1;
            int paid=-1;
            try{
                userId = Integer.parseInt(strUserId);
                paid = Integer.parseInt(strPaid);
            }catch (NumberFormatException e){
                logger.error(e.getMessage());
            }


            if(paid <= 0){
                logger.error("negative paid");
                return "negative paid";
            }

            User user = userService.getUserById(userId);
            if(user != null){
                Bill bill = new Bill();
                bill.setDate(new Date());
                bill.setUser(user);
                bill.setPaid(paid);
                if(billService.save(bill)){
                    return "success";
                }
            }else{
                logger.error("user null");
                return "user null";
            }

        }
        return "false";
    }


}
