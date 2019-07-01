package entities;

import javax.persistence.*;

@Entity
@Table(name="bill")
public class Bill {

    @Id
    @Column(name="bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="course_id")
    private Course course;

    @Column(name="paid")
    private float paid;

    @Column(name="lack")
    private float lack;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int feeId) {
        this.billId = feeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public float getLack() {
        return lack;
    }

    public void setLack(float lack) {
        this.lack = lack;
    }
}
