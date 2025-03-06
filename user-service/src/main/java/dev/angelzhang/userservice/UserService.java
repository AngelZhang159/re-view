package dev.angelzhang.userservice;

import dev.angelzhang.userservice.dto.UserLoginResponse;
import dev.angelzhang.userservice.dto.UserRegisterResponse;
import dev.angelzhang.userservice.dto.UserRequest;
import dev.angelzhang.userservice.enums.Role;
import dev.angelzhang.userservice.exception.InvalidPasswordException;
import dev.angelzhang.userservice.exception.UserNotFoundException;
import dev.angelzhang.userservice.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String MESSAGE = "Account created successfully";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<UserRegisterResponse> registerUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());
        user.setRole(Role.USER);

        LocalDate localDate = LocalDate.now();
        user.setCreatedAt(localDate);
        user.setUpdatedAt(localDate);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegisterResponse(user.getId(), user.getUsername(), user.getEmail(), MESSAGE));
    }

    public ResponseEntity<UserLoginResponse> loginUser(@Valid UserRequest userRequest) {
        Optional<User> userByUsername = userRepository.findUserByUsername(userRequest.username());
        Optional<User> userByUser = userRepository.findUserByEmail(userRequest.email());

        if (userByUser.isEmpty() && userByUsername.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User user = userByUser.orElseGet(userByUsername::get);

        if (!passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtUtil.generateToken(user.getId(), user.getRole()));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new UserLoginResponse(user.getUsername(), user.getEmail()));
    }
}
