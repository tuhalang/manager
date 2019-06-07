package controller;

import entities.Course;
import entities.User;
import entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.CourseService;
import service.UserService;
import service.UserTypeService;
import utils.ReadCSV;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CourseDetailController {

    @Autowired
    UserService userService;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "course_detail", method = RequestMethod.GET)
    public String Default(HttpSession httpSession, @RequestParam("id") int id, ModelMap map) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null && user.getUserType().getType().equalsIgnoreCase("admin")) {

            Course course = courseService.getById(id);

            List<User> teachers = new ArrayList<User>();
            List<User> students = new ArrayList<User>();
            for (User x : course.getListUsers()) {
                if (x.getUserType().getType().equalsIgnoreCase("teacher")) {
                    teachers.add(x);
                } else {
                    students.add(x);
                }
            }

            map.addAttribute("course", course);
            map.addAttribute("teachers", teachers);
            map.addAttribute("students", students);

            return "course_detail";
        }
        return "home";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getById(id);
        InputStream is = null;
        BufferedReader br = null;

        try {
            is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            List<User> listStudents = ReadCSV.readFileStudent(br);
            System.out.println(listStudents.size());
            UserType userType = userTypeService.getByName("student");
            userService.saveListUser(listStudents, userType);

            Set<User> listUsers = course.getListUsers();
            listUsers.addAll(listStudents);
            course.setListUsers(listUsers);
            courseService.update(course);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // close the streams using close method
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }

        }

        return "redirect:course_detail?id=" + id;
    }
}
