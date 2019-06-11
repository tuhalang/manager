package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AjaxController {

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    LessonService lessonService;






    @RequestMapping(value = "api/load_lessons", method = RequestMethod.GET)
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


    @RequestMapping(value = "api/load_students_of_lesson", method = RequestMethod.GET)
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

    @RequestMapping(value = "api/load_students_of_course", method = RequestMethod.GET)
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

    @RequestMapping(value = "api/roll_up", method = RequestMethod.GET)
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

    @RequestMapping(value = "api/new_lesson", method = RequestMethod.GET)
    @ResponseBody
    public String newLesson(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String lessonName = request.getParameter("lessonName");
        double length = Double.parseDouble(request.getParameter("length"));
        Course course = courseService.getById(courseId);
        Date date = new Date();
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonName);
        lesson.setCourse(course);
        lesson.setDate(date);
        lesson.setLength(length);
        if (lessonService.save(lesson))
            return "success";
        return "fail";
    }

    @RequestMapping(value = "api/load_course_of_student", method = RequestMethod.GET)
    @ResponseBody
    public String loadCourseOfStudent(HttpServletRequest request, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        int type = Integer.parseInt(request.getParameter("type"));
        List<Course> listCourses = new ArrayList<>();
        System.out.println(user.getListCourses().size());
        for (Course course : user.getListCourses()) {
            if (course.getStatus() == type) {
                listCourses.add(course);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listCourses);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "api/load_schedule", method = RequestMethod.GET)
    @ResponseBody
    public String loadSchedule(HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        int type = Integer.parseInt(request.getParameter("type"));
        List<Lesson> listLessons = new ArrayList<Lesson>();
        if (type == 0) {
            listLessons = lessonService.getLessonInWeek(user.getUserId());
        } else {
            listLessons = lessonService.getLessonInMonth(user.getUserId());
        }
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listLessons);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ajaxResponse;
    }


}
 