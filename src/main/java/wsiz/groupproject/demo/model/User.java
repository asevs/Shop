package wsiz.groupproject.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String fullName;
    private String email;
    private String principalId;
    private String photo;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    @Enumerated(EnumType.STRING)
    private UserLoginType loginType;


}
