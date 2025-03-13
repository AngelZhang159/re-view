package dev.angelzhang.userservice.dto;

public record UserLoginRequest(
        String email,
        String password
) {
}
