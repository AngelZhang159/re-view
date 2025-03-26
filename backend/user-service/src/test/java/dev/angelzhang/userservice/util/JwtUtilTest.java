package dev.angelzhang.userservice.util;

import dev.angelzhang.userservice.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = JwtUtil.class)
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("Generate access token with correct data")
    void shouldGenerateAccessTokenWhenGivenCorrectData() {
        String jwt = jwtUtil.generateAccessToken(1L, List.of(Role.USER));
        assertNotNull(jwt);
    }

    @Test
    @DisplayName("Get string 'userId' from access token")
    void shouldExtractUserIdFromAccessToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateAccessToken(userId, List.of(Role.USER));

        assertEquals(userId, jwtUtil.extractUserId(jwt));
    }

    @Test
    @DisplayName("Validate that a access token's userId is correct")
    void shouldValidateAccessToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateAccessToken(userId, List.of(Role.USER));

        boolean check = jwtUtil.isValidToken(jwt, userId);

        assertTrue(check);
    }

    @Test
    @DisplayName("Validate that a access token's userId is incorrect")
    void shouldNotValidateAccessToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateAccessToken(userId, List.of(Role.USER));

        boolean check = jwtUtil.isValidToken(jwt, 2L);

        assertFalse(check);
    }

    @Test
    @DisplayName("Generate refresh token with correct data")
    void shouldGenerateRefreshTokenWhenGivenCorrectData() {
        String jwt = jwtUtil.generateRefreshToken(1L, List.of(Role.USER));
        assertNotNull(jwt);
    }

    @Test
    @DisplayName("Get string 'userId' from refresh token")
    void shouldExtractUserIdFromRefreshToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateRefreshToken(userId, List.of(Role.USER));

        assertEquals(userId, jwtUtil.extractUserId(jwt));
    }

    @Test
    @DisplayName("Validate that a refresh token's userId is correct")
    void shouldValidateRefreshToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateRefreshToken(userId, List.of(Role.USER));

        boolean check = jwtUtil.isValidToken(jwt, userId);

        assertTrue(check);
    }

    @Test
    @DisplayName("Validate that a refresh token's userId is incorrect")
    void shouldNotValidateRefreshToken() {
        Long userId = 1L;

        String jwt = jwtUtil.generateRefreshToken(userId, List.of(Role.USER));

        boolean check = jwtUtil.isValidToken(jwt, 2L);

        assertFalse(check);
    }
}