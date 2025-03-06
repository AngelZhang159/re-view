package dev.angelzhang.userservice.util;

import dev.angelzhang.userservice.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = JwtUtil.class)
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("Generate token with correct data")
    void shouldGenerateTokenWhenGivenCorrectData() {
        String jwt = jwtUtil.generateToken(1, Role.USER);
        assertNotNull(jwt);
    }

    @Test
    @DisplayName("Get string 'userId' from token")
    void shouldExtractUserIdFromToken() {
        Integer userId = 1;

        String jwt = jwtUtil.generateToken(userId, Role.USER);

        assertEquals(userId, jwtUtil.extractUserId(jwt));
    }

    @Test
    @DisplayName("Validate that a token's userId is correct")
    void shouldValidateToken() {
        Integer userId = 1;

        String jwt = jwtUtil.generateToken(userId, Role.USER);

        boolean check = jwtUtil.validateToken(jwt, userId);

        assertTrue(check);
    }

    @Test
    @DisplayName("Validate that a token's userId is incorrect")
    void shouldNotValidateToken() {
        Integer userId = 1;

        String jwt = jwtUtil.generateToken(userId, Role.USER);

        boolean check = jwtUtil.validateToken(jwt, 2);

        assertFalse(check);
    }
}