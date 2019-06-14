package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "course_detail",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    Set<User> listUsers = new HashSet<User>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = CascadeType.MERGE)
    Set<Lesson> listLessons = new HashSet<Lesson>();
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @Column(name = "num_of_lesson")
    private int numOfLesson;
    @Column(name = "fee")
    private double fee;
    @Column(name = "promotion")
    private double promotion;
    @OneToOne
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;
    @Column(name = "status")
    private int status;


    public Course() {
        super();
    }

    public Course(String courseName, Date startDate, Date endDate, int numOfLesson, double fee, double promotion,
                  String type) {
        super();
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfLesson = numOfLesson;
        this.fee = fee;
        this.promotion = promotion;
        this.courseType = new CourseType(type);
    }


    public Course(int courseId, String courseName, Date startDate, Date endDate, int numOfLesson, double fee,
                  double promotion, CourseType courseType) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfLesson = numOfLesson;
        this.fee = fee;
        this.promotion = promotion;
        this.courseType = courseType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumOfLesson() {
        return numOfLesson;
    }

    public void setNumOfLesson(int numOfLesson) {
        this.numOfLesson = numOfLesson;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Set<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<User> listUsers) {
        this.listUsers = listUsers;
    }

    public Set<Lesson> getListLessons() {
        return listLessons;
    }

    public void setListLessons(Set<Lesson> listLessons) {
        this.listLessons = listLessons;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Course && this.courseId == ((Course) obj).courseId;
    }

    @Override
    public int hashCode() {
        return this.courseId;
    }


    public User getTeacher(){
        for (User user : listUsers){
            if(user.getUserType().getType().equalsIgnoreCase("teacher")){
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String jsonObj = "{\"courseId\":\""+courseId+"\","+
                "\"courseName\":\""+courseName+"\","+
                "\"teacher\":\""+getTeacher().getFullname()+"\","+
                "\"startDate\":\""+startDate+"\","+
                "\"endDate\":\""+endDate+"\","+
                "\"numOfLesson\":"+numOfLesson+","+
                "\"lessons\":"+listLessons+","+
                "\"fee\":"+fee+""+
                "}";
        return jsonObj;
    }


}
