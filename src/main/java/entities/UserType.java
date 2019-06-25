package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;

    @Column(name = "type")
    private String type;

    public UserType() {
        super();
    }

    public UserType(String type) {
        this.type = type;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        String jsonObj = "{\"userTypeId\":\""+userTypeId+"\","+
                "\"type\":\""+type+"\""+
                "}";
        return jsonObj;
    }
}
