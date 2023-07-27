package seohyun.app.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String username;
    @Column(name = "birth_date")
    private Date birthDate;
    private String phone;
    private String email;
    @Column(name = "join_date")
    private Date joinDate;
}
