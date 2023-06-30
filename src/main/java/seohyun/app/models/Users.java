package seohyun.app.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    private String id;
    private String username;
    private String password;
}
