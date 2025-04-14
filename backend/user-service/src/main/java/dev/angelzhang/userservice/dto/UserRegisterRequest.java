package dev.angelzhang.userservice.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRegisterRequest(
    @NotEmpty(message = "Username can not be empty") String username,
    @NotEmpty(message = "Password can not be empty") String password,
    @NotEmpty(message = "Email can not be empty") String email,
    String profilePictureUrl
) {
}
