package dao;

import entities.Course;
import entities.CourseType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Date;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class CourseDAOTest {

    @Autowired
    CourseDAO courseDAO;

    @Test
    public void saveTest() {
        Course course = new Course();
        course.setCourseName("ABC");
        course.setStartDate(new Date());
        course.setEndDate(new Date());
        course.setPromotion(0);
        course.setNumOfLesson(2);
        course.setFee(1.2);
        CourseType courseType = new CourseType("seminar");
        course.setCourseType(courseType);
        courseDAO.save(course);

        Course course1 = courseDAO.getById(course.getCourseId());

        Assert.assertEquals(course.getCourseName(), course1.getCourseName());
        Assert.assertEquals(course.getStartDate(), course1.getStartDate());
        Assert.assertEquals(course.getEndDate(), course1.getEndDate());
        Assert.assertEquals(course.getPromotion(), course1.getPromotion(), 0.000001);
        Assert.assertEquals(course.getNumOfLesson(), course1.getNumOfLesson());
        Assert.assertEquals(course.getFee(), course1.getFee(), 0.000001);
        Assert.assertEquals(course.getCourseType().getType(), course1.getCourseType().getType());
    }

    @Test
    public void updateTest() {
        Course course = courseDAO.getById(1);
        course.setCourseName("Test");
        courseDAO.update(course);

        Course course1 = courseDAO.getById(1);
        Assert.assertEquals(course.getCourseName(),course1.getCourseName());
    }
}