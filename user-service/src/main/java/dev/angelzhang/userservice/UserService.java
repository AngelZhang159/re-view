package dev.angelzhang.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String MESSAGE = "Account created successfully";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());
        user.setRole(Role.USER);

        LocalDate localDate = LocalDate.now();
        user.setCreatedAt(localDate);
        user.setUpdatedAt(localDate);

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), MESSAGE);
    }

}
