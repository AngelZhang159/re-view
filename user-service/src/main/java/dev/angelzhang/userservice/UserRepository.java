package dev.angelzhang.userservice;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(@NotBlank(message = "Username is mandatory") String username);
    Optional<User> findUserByEmail(@NotBlank(message = "Email is mandatory") String email);
}
