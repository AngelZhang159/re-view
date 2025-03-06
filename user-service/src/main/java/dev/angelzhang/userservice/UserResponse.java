package dev.angelzhang.userservice;

public record UserResponse(
    Long id,
    String username,
    String email,
    String message
) {
}
