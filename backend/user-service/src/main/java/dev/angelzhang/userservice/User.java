package dev.angelzhang.userservice;

import dev.angelzhang.userservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String oauthProvider;

    private String googleId;

    private String facebookId;

    private String githubId;

    private String profilePictureUrl;

    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class)
    @Column(nullable = false)
    private List<Role> role;

}