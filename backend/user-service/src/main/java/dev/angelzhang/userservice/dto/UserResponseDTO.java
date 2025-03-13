package dev.angelzhang.userservice.dto;

public record UserResponseDTO(
        String username,
        String email,
        String profilePicture
) {
}
