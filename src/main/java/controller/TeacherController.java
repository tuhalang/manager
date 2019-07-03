package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Course;
import entities.CourseType;
import entities.Lesson;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.CourseTypeService;
import service.LessonService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TeacherController {

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    LessonService lessonService;

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

    @PostMapping(value = "api/save_course", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Course submitNewCourse(HttpSession httpSession, @ModelAttribute Course course, BindingResult result){

        if(!result.hasErrors()){
            User user = (User) httpSession.getAttribute("user");
            Set<User> users = new HashSet<>();
            users.add(user);
            course.setListUsers(users);
            if(courseService.save(course)){
                System.out.println("ok");
                return course;
            }
        }
        return null;
    }

    @RequestMapping(value = "api/save_lesson", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Lesson submitNewLesson(HttpServletRequest request){

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String nameLesson = request.getParameter("nameLesson");
        String contentLesson = request.getParameter("contentLesson");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateLesson"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int lengthLesson = Integer.parseInt(request.getParameter("lengthLesson"));

        Course course = courseService.getById(courseId);
        Set<Lesson> listLessons = course.getListLessons();
        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setLessonName(nameLesson);
        lesson.setDate(date);
        lesson.setLength(lengthLesson);
        lesson.setContent(contentLesson);

        lessonService.save(lesson);
        listLessons.add(lesson);
        course.setListLessons(listLessons);
        courseService.update(course);
        return lesson;
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
                course1.setCourseName(course.getCourseName());
                course1.setStatus(course.getStatus());
                course1.setCourseType(course.getCourseType());
                course1.setEndDate(course.getEndDate());
                course1.setFee(course.getFee());
                course1.setNumOfLesson(course.getNumOfLesson());
                course1.setStartDate(course.getStartDate());
                course1.setPromotion(course.getPromotion());
                if (courseService.update(course1)) {
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

    @RequestMapping(value = "api/load_lessons", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadLessons(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.getById(courseId);
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(course.getListLessons());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }


    @RequestMapping(value = "api/load_students_of_lesson", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadStudentOfLesson(HttpServletRequest request) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getById(lessonId);
        Course course = lesson.getCourse();
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(lesson.getListUsers());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/load_students_of_course", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadStudentOfCourse(HttpServletRequest request) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getById(lessonId);
        Course course = lesson.getCourse();
        ObjectMapper mapper = new ObjectMapper();
        List<User> listUsres = new ArrayList<>();
        for (User user : course.getListUsers()) {
            if (user.getUserType().getType().equalsIgnoreCase("student")) {
                listUsres.add(user);
            }
        }
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listUsres);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/roll_up", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String roll_up(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getById(lessonId);
        User user = userService.getUserById(userId);
        Set<User> listUser = lesson.getListUsers();
        listUser.add(user);
        lesson.setListUsers(listUser);
        lessonService.update(lesson);
        return "success";
    }

    @RequestMapping(value = "api/load_info_lessons", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadInfoLesson(HttpServletRequest request) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getById(lessonId);
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(lesson);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }


}
