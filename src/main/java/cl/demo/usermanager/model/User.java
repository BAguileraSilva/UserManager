package cl.demo.usermanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private UUID id;

    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "USER_PASSWORD", nullable = false, length = 255)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Phone> phones;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED_AT", nullable = false)
    private LocalDateTime modified;

    @Column(name = "LAST_LOGIN_AT", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

}
