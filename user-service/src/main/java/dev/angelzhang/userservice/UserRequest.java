package dev.angelzhang.userservice;

public record UserRequest(
    String username,
    String password,
    String email
) {
}
