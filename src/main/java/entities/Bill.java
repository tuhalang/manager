package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="bill")
public class Bill implements Serializable {

    @Id
    @Column(name="bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="paid")
    private float paid;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return "{\"billId\":\"" + billId + "\"," +
                "\"paid\":" + paid + "," +
                "\"date\":\"" + date + "\"" +
                "}";
    }

}
