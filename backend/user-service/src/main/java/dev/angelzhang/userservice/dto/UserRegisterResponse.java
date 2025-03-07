package dev.angelzhang.userservice.dto;

public record UserRegisterResponse(
    Long id,
    String username,
    String email,
    String message
) {
}
