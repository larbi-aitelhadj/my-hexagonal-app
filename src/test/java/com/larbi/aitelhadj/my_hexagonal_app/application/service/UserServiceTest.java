package com.larbi.aitelhadj.my_hexagonal_app.application.service;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import com.larbi.aitelhadj.my_hexagonal_app.domain.port.UserRepositoryPort;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @larbi.aitelhadj
 *
 */
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        User user = new User();
        user.setName("user1");
        user.setEmail("user1@example.com");
        when(userRepositoryPort.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("user1", createdUser.getName());
        assertEquals("user1@example.com", createdUser.getEmail());
    }

    @Test
    void testUpdateEmailUser() {
        User user = new User();
        user.setId(1L);
        user.setName("user1");
        user.setEmail("user1@example.com");
        when(userRepositoryPort.findById(1L)).thenReturn(user);
        when(userRepositoryPort.save(user)).thenReturn(user);
        User userUpdated = userService.updateEmailUser(1L, "email11@mail.com");
        assertNotNull(userUpdated);
        assertEquals(1L, userUpdated.getId());
        assertEquals("user1", userUpdated.getName());
        assertEquals("email11@mail.com", userUpdated.getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("user1");
        user.setEmail("user1@example.com");
        userService.deleteUser(user);
        verify(userRepositoryPort).delete(user);
    }

    @Test
    void getAllUsers() {
        //return userRepositoryPort.findAll();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("user1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(1L);
        user2.setName("user1");
        user2.setEmail("user1@example.com");

        when(userRepositoryPort.findAll()).thenReturn(List.of(user1, user2));
        List<User> userList = userService.getAllUsers();
        assertNotNull(userList);
        assertThat(userList).size().isEqualTo(2);
    }

}