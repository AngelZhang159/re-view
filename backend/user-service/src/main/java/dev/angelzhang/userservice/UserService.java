package dev.angelzhang.userservice;

import dev.angelzhang.userservice.dto.UserLoginRequest;
import dev.angelzhang.userservice.dto.UserLoginResponse;
import dev.angelzhang.userservice.dto.UserRegisterRequest;
import dev.angelzhang.userservice.dto.UserRegisterResponse;
import dev.angelzhang.userservice.exception.InvalidJWTException;
import dev.angelzhang.userservice.exception.InvalidPasswordException;
import dev.angelzhang.userservice.exception.UserAlreadyExistsException;
import dev.angelzhang.userservice.exception.UserNotFoundException;
import dev.angelzhang.userservice.mapper.UserMapper;
import dev.angelzhang.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${jwt.accessExpiration}")
    private Long ACCESS_EXPIRATION;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<UserRegisterResponse> registerUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByUsernameOrEmail(userRegisterRequest.username(), userRegisterRequest.email()))
            throw new UserAlreadyExistsException("User with email '" + userRegisterRequest.email() + "' or username '" + userRegisterRequest.username() + "' already exists.");
        //TODO Check if the password is valid (?)
        User user = UserMapper.toEntity(userRegisterRequest, passwordEncoder.encode(userRegisterRequest.password()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toRegisterDTO(user));
    }

    public ResponseEntity<UserLoginResponse> loginUser(UserLoginRequest userLoginRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(userLoginRequest.email());

        // Check if user exists
        User user = userByEmail.orElseThrow(() -> new UserNotFoundException("User with email '" + userLoginRequest.email() + "' not found."));
        // Check if the password is correct
        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword()))
            throw new InvalidPasswordException("Invalid password");

        UserLoginResponse userResponse = UserMapper.toLoginDTO(
                user,
                jwtUtil.generateAccessToken(user.getId(), user.getRole()),
                jwtUtil.generateRefreshToken(user.getId(), user.getRole()),
                ACCESS_EXPIRATION
        );

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }


    public ResponseEntity<UserLoginResponse> refreshToken(String refreshToken) {

        String token = refreshToken.startsWith("Bearer ") ? refreshToken.substring(7) : refreshToken;

        if (!jwtUtil.isRefreshToken(token)) throw new InvalidJWTException("Invalid JWT, must be refresh token");

        User user = userRepository.findById(jwtUtil.extractUserId(token))
                .orElseThrow(() ->
                        new UserNotFoundException("User not found (User was probably deleted)")
                );

        UserLoginResponse userResponse = UserMapper.toLoginDTO(
                user,
                jwtUtil.generateAccessToken(user.getId(), user.getRole()),
                jwtUtil.generateRefreshToken(user.getId(), user.getRole()),
                ACCESS_EXPIRATION
        );

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
