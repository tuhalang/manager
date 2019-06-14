package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "roll_up",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    Set<User> listUsers = new HashSet<User>();
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "length")
    private double length;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lesson && this.lessonId == ((Lesson) obj).lessonId;
    }

    @Override
    public int hashCode() {
        return this.lessonId;
    }


    @Override
    public String toString(){
        return "{\"lessonName\":\""+lessonName+"\",\"content\":\""+content+"\"}";
    }
}
