package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Course;
import entities.Lesson;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CourseService;
import service.LessonService;
import service.UserService;
import utils.Mail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class StudentController {

    private static final Logger logger = LogManager.getLogger(StudentController.class.getName());

    @Autowired
    LessonService lessonService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    Mail mail;

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

    @RequestMapping(value = "api/load_course_of_student", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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

    @RequestMapping(value = "api/search_course", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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


    @RequestMapping(value = "api/load_schedule", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadSchedule(HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        int type=0;
        try{
            type = Integer.parseInt(request.getParameter("type"));
        }catch (NumberFormatException e){
            logger.error(e.getMessage());
            return null;
        }

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

    @RequestMapping(value = "api/load_course_short", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
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

    @RequestMapping(value = "api/enroll", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enroll(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("student")) {
            try{
                int courseId = Integer.parseInt(request.getParameter("courseId"));

                Course course = courseService.getById(courseId);
                if(course != null ){
                    Set<Course> courseSet = user.getListCourses();
                    if(courseSet.contains(course)){
                        return "you enrolled";
                    }
                    courseSet.add(course);
                    user.setListCourses(courseSet);
                    if(userService.update(user)){
                        boolean sendUser = mail.sendMail(user.getEmail(), "Dang ki khoa hoc thanh cong!",
                                "Cam on ban da dang ki khoa hoc " + course.getCourseName());
                        boolean sendTeacher = mail.sendMail(course.getTeacher().getEmail(), "Hoc vien dang ki khoa hoc",
                                "Hoc vien " + user.getFullname() + " da dang ki khoa hoc " +  course.getCourseName());
                        if(sendUser && sendTeacher)
                            return "Enroll successful!";
                        if(sendUser)
                            return "Enroll successful! but can't send mail for teacher";
                        if(sendTeacher)
                            return  "Enroll successful! but can't send mail for student";
                    }
                    else{
                        return "Enroll failed !";
                    }
                }else{
                    return "Not exists course";
                }

            }catch (NumberFormatException e){
                logger.error(e.getMessage());
            }


        }
        return "Enroll failed !";
    }

    @RequestMapping(value = "api/show_course_detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String showDetailCourse(HttpServletRequest request, HttpSession httpSession) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.getById(courseId);
        System.out.println(course);
        return course.toString();
    }
}

