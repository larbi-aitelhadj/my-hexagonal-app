package com.larbi.aitelhadj.my_hexagonal_app.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @larbi.aitelhadj
 *
 */
class UserTest {

    @Test
    public void testUpdateEmail_validEmail() {
        User user = new User(1L, "old.email@example.com", "user1");
        user.updateEmail("new.email@example.com");
        assertEquals("new.email@example.com", user.getEmail());
    }

    @Test
    public void testUpdateEmail_invalidEmail_empty() {
        User user = new User(1L, "old.email@example.com", "user1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.updateEmail("");
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    public void testUpdateEmail_invalidEmail_invalidFormat() {
        User user = new User(1L, "old.email@example.com", "user1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.updateEmail("invalid-email");
        });
        assertEquals("Invalid email format", exception.getMessage());
    }
}