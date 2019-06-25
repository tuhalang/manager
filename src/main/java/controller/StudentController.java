package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Course;
import entities.Lesson;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CourseService;
import service.LessonService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class StudentController {

    @Autowired
    LessonService lessonService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "student", method = RequestMethod.GET)
    public String Default(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("student")) {
            return "student";
        }
        return "index";
    }

    @RequestMapping(value = "student/schedule", method = RequestMethod.GET)
    public String loadPageSchedule(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("student")) {
            return "student_schedule";
        }
        return "index";
    }

    @RequestMapping(value = "student/course", method = RequestMethod.GET)
    public String loadPageCourse(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("student")) {
            return "student_course";
        }
        return "index";
    }

    @RequestMapping(value = "api/load_course_of_student", method = RequestMethod.GET)
    @ResponseBody
    public String loadCourseOfStudent(HttpServletRequest request, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        int type = Integer.parseInt(request.getParameter("type"));
        List<Course> listCourses = new ArrayList<>();
        for (Course course : user.getListCourses()) {
            if (course.getStatus() == type) {
                listCourses.add(course);
            }
        }
        return listCourses.toString();
    }

    @RequestMapping(value = "api/search_course", method = RequestMethod.GET)
    @ResponseBody
    public String searchCourse(HttpServletRequest request, HttpSession httpSession) {
        String type = request.getParameter("type");
        String key = request.getParameter("key");
        List<Course> courses = courseService.search(type, key, 0, 6);
        return courses.toString();
    }

    @RequestMapping(value = "api/searchInUser", method = RequestMethod.GET)
    @ResponseBody
    public String searchInuser(HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        String key = request.getParameter("key");
        List<Course> courses = courseService.searchInUser(user.getUserId(),key, 0, 6);
        return courses.toString();
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

    @RequestMapping(value = "api/load_course_short", method = RequestMethod.GET)
    @ResponseBody
    public String loadCourseShort(HttpServletRequest request, HttpSession httpSession) {

        int start = Integer.parseInt(request.getParameter("page"))-1;
        List<Course> courses = courseService.getAll(start*6, 6);
        String response = "[";
        for(Course course : courses){
            String courseJson = "{\"courseId\": "+ course.getCourseId() +"," +
                                "\"courseName\": \""+course.getCourseName()+"\" , " +
                                "\"teacher\": \""+course.getTeacher().getFullname()+"\"," +
                                "\"fee\":"+course.getFee()+"},";
            response+=courseJson;
        }
        response= response.substring(0,response.length()-1) + "]";
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "api/enroll", method = RequestMethod.GET)
    @ResponseBody
    public String enroll(HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("student")) {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Set<Course> courseSet = user.getListCourses();
            courseSet.add(courseService.getById(courseId));
            user.setListCourses(courseSet);
            if(userService.update(user)){
                return "Đăng kí khóa học thành công!";
            }
            else{
                return "Đăng kí khóa học thất bại!";
            }
        }
        return "Đăng kí khóa học thất bại!";
    }

    @RequestMapping(value = "api/show_course_detail", method = RequestMethod.GET)
    @ResponseBody
    public String showDetailCourse(HttpServletRequest request, HttpSession httpSession) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.getById(courseId);
        System.out.println(course);
        return course.toString();
    }
}

