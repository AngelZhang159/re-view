package dev.angelzhang.userservice.dto;

public record UserLoginResponse(
        String accessToken,
        String refreshToken,
        UserResponseDTO user,
        Long expiresIn
) {

}
