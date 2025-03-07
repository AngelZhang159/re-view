package dev.angelzhang.userservice.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
    String username,
    @NotEmpty String password,
    String email
) {
}
