package dev.angelzhang.userservice;

import dev.angelzhang.userservice.dto.UserLoginRequest;
import dev.angelzhang.userservice.dto.UserLoginResponse;
import dev.angelzhang.userservice.dto.UserRegisterResponse;
import dev.angelzhang.userservice.dto.UserRequest;
import dev.angelzhang.userservice.enums.Role;
import dev.angelzhang.userservice.exception.InvalidPasswordException;
import dev.angelzhang.userservice.exception.UserAlreadyExistsException;
import dev.angelzhang.userservice.exception.UserNotFoundException;
import dev.angelzhang.userservice.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        checkUserAlreadyExists(userRequest);

        User user = createUserWithDefaults(userRequest);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegisterResponse(user.getId(), user.getUsername(), user.getEmail(), MESSAGE));
    }

    private User createUserWithDefaults(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());
        user.setRole(Role.USER);

        LocalDate localDate = LocalDate.now();
        user.setCreatedAt(localDate);
        user.setUpdatedAt(localDate);
        return user;
    }

    private void checkUserAlreadyExists(UserRequest userRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(userRequest.email());
        if (userByEmail.isPresent())
            throw new UserAlreadyExistsException("User with email '" + userRequest.email() + "' already exists.");
        Optional<User> userByUsername = userRepository.findUserByUsername(userRequest.username());
        if (userByUsername.isPresent())
            throw new UserAlreadyExistsException("User with username '" + userRequest.username() + "' already exists.");
    }

    public ResponseEntity<UserLoginResponse> loginUser(@Valid UserLoginRequest userLoginRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(userLoginRequest.email());

        User user = checkUserExists(userLoginRequest, userByEmail);

        checkCorrectPassword(userLoginRequest, user);

        UserLoginResponse userLoginResponse = new UserLoginResponse(jwtUtil.generateAccessToken(user.getId(), user.getRole()), jwtUtil.generateRefreshToken(user.getId(), user.getRole()));

        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
    }

    private void checkCorrectPassword(UserLoginRequest userLoginRequest, User user) {
        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    private static User checkUserExists(UserLoginRequest userLoginRequest, Optional<User> userByEmail) {
        if (userByEmail.isEmpty()) {
            String errMsg = "User with email '" + userLoginRequest.email() + "' not found";
            throw new UserNotFoundException(errMsg);
        } else {
            return userByEmail.get();
        }
    }
}
