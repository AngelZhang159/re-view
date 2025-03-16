package dev.angelzhang.userservice;

import dev.angelzhang.userservice.dto.*;
import dev.angelzhang.userservice.enums.Role;
import dev.angelzhang.userservice.exception.InvalidPasswordException;
import dev.angelzhang.userservice.exception.UserAlreadyExistsException;
import dev.angelzhang.userservice.exception.UserNotFoundException;
import dev.angelzhang.userservice.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${jwt.accessExpiration}")
    private Long ACCESS_EXPIRATION;

    private final String MESSAGE = "Account created successfully";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<UserRegisterResponse> registerUser(UserRegisterRequest userRegisterRequest) {
        checkUserAlreadyExists(userRegisterRequest);

        User user = createUserWithDefaults(userRegisterRequest);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegisterResponse(user.getId(), user.getUsername(), user.getEmail(), MESSAGE));
    }

    private User createUserWithDefaults(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.username());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.password()));
        user.setEmail(userRegisterRequest.email());
        user.setRole(Role.USER);

        LocalDate localDate = LocalDate.now();
        user.setCreatedAt(localDate);
        user.setUpdatedAt(localDate);
        return user;
    }

    private void checkUserAlreadyExists(UserRegisterRequest userRegisterRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(userRegisterRequest.email());
        if (userByEmail.isPresent())
            throw new UserAlreadyExistsException("User with email '" + userRegisterRequest.email() + "' already exists.");
        Optional<User> userByUsername = userRepository.findUserByUsername(userRegisterRequest.username());
        if (userByUsername.isPresent())
            throw new UserAlreadyExistsException("User with username '" + userRegisterRequest.username() + "' already exists.");
    }

    public ResponseEntity<UserLoginResponse> loginUser(@Valid UserLoginRequest userLoginRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(userLoginRequest.email());

        User user = checkUserExists(userLoginRequest, userByEmail);
        checkCorrectPassword(userLoginRequest, user);

        return generateUserLogin(user);
    }

    private ResponseEntity<UserLoginResponse> generateUserLogin(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getUsername(),
                user.getEmail(),
                user.getProfilePictureUrl()
        );

        UserLoginResponse userLoginResponse = new UserLoginResponse(
                jwtUtil.generateAccessToken(user.getId(), user.getRole()),
                jwtUtil.generateRefreshToken(user.getId(), user.getRole()),
                userResponseDTO,
                ACCESS_EXPIRATION
        );

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

    public ResponseEntity<UserLoginResponse> refreshToken(String refreshToken) {
        Long userId = jwtUtil.extractUserId(refreshToken);
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID: " + userId + " not found (User was deleted)");
        }

        return generateUserLogin(user.get());

    }
}
