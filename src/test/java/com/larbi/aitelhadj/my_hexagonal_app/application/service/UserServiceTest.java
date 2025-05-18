package com.larbi.aitelhadj.my_hexagonal_app.application.service;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import com.larbi.aitelhadj.my_hexagonal_app.domain.port.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @larbi.aitelhadj
 *
 */
class UserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        // Initialise les mocks
        MockitoAnnotations.openMocks(this);

        // Crée un utilisateur pour les tests
        user = new User();
        user.setName("user1");
        user.setEmail("user1@example.com");
    }

    @Test
    void createUser() {
        when(userRepositoryPort.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("user1", createdUser.getName());
        assertEquals("user1@example.com", createdUser.getEmail());
    }

}