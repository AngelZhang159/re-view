package dev.angelzhang.userservice;

import dev.angelzhang.userservice.dto.UserLoginRequest;
import dev.angelzhang.userservice.dto.UserLoginResponse;
import dev.angelzhang.userservice.dto.UserRegisterResponse;
import dev.angelzhang.userservice.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("Registering user: {}", userRequest);
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        log.info("Logging in user: {}", userLoginRequest);
        return userService.loginUser(userLoginRequest);
    }
}
