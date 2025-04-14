package dev.angelzhang.userservice.dto;

import lombok.Builder;

@Builder
public record UserRegisterResponse(
    Long id,
    String username,
    String email,
    String message
) {
}
