package dev.angelzhang.userservice.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRegisterRequest(
    @NotEmpty String username,
    @NotEmpty String password,
    @NotEmpty String email
) {
}
