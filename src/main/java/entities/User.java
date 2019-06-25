package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "roll_up",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    Set<Lesson> listLessons = new HashSet<Lesson>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "course_detail",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    Set<Course> listCourses = new HashSet<Course>();
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullname;
    @Column(name="status")
    private int status;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;


    public User() {
        super();
    }

    public User(String username, String password, String fullname, String type) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.userType = new UserType(type);
    }

    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public User(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Lesson> getListLessons() {
        return listLessons;
    }

    public void setListLessons(Set<Lesson> listLessons) {
        this.listLessons = listLessons;
    }

    public Set<Course> getListCourses() {
        return listCourses;
    }

    public void setListCourses(Set<Course> listCourses) {
        this.listCourses = listCourses;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.userId == ((User) obj).userId;
    }

    @Override
    public int hashCode() {
        return this.userId;
    }

    public double totalTime() {
        double total = 0;
        for (Lesson lesson : this.listLessons) {
            total += lesson.getLength();
        }
        return total;
    }

    @Override
    public String toString(){
        String jsonObj = "{\"userId\":\""+userId+"\","+
                "\"username\":\""+username+"\","+
                "\"fullname\":\""+fullname+"\","+
                "\"status\":\""+status+"\","+
                "\"courses\":"+listCourses+","+
                "\"userType\":"+userType+","+
                "\"lessons\":"+listLessons+","+
                "\"totalTime\":"+totalTime()+
                "}";
        return jsonObj;
    }


}
