package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_type")
public class CourseType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseTypeId;

    @Column(name = "type")
    private String type;

    public CourseType(String type) {
        this.type = type;
    }

    public CourseType(int courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public CourseType() {
        super();
    }

    public int getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(int courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }


}
