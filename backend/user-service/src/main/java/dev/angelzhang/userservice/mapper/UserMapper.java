package dev.angelzhang.userservice.mapper;

import dev.angelzhang.userservice.User;
import dev.angelzhang.userservice.dto.UserLoginResponse;
import dev.angelzhang.userservice.dto.UserRegisterRequest;
import dev.angelzhang.userservice.dto.UserRegisterResponse;
import dev.angelzhang.userservice.dto.UserResponseDTO;
import dev.angelzhang.userservice.enums.Role;

import java.time.Instant;
import java.util.List;

public class UserMapper {

    public static User toEntity(UserRegisterRequest userRegisterRequest, String encodedPassword) {
        return User.builder()
                .username(userRegisterRequest.username())
                .password(encodedPassword)
                .email(userRegisterRequest.email())
                .profilePictureUrl(userRegisterRequest.profilePictureUrl())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .role(List.of(Role.USER))
                .build();
    }

    private static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getUsername(),
                user.getEmail(),
                user.getProfilePictureUrl()
        );
    }

    public static UserRegisterResponse toRegisterDTO(User user) {
        return UserRegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .message("User registered successfully")
                .build();
    }

    public static UserLoginResponse toLoginDTO(User user, String accessToken, String refreshToken, Long accessExpiration) {
        return new UserLoginResponse(
                accessToken,
                refreshToken,
                toDTO(user),
                accessExpiration
        );
    }
}
