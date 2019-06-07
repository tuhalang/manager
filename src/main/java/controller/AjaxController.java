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
        List<Course> listCourse = new ArrayList<Course>();

        if (typeId == 0) {
            listCourse = courseService.getAll();

        } else {
            listCourse = courseService.getByType(typeId);
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
        Set<Course> listCourses = new HashSet<Course>();
        for (String id : listId) {
            int userId = Integer.parseInt(id);
            listCourses.addAll(userService.getUserById(userId).getListCourses());
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
 