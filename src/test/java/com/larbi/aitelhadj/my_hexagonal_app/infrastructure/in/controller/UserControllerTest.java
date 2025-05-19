package com.larbi.aitelhadj.my_hexagonal_app.infrastructure.in.controller;

import com.larbi.aitelhadj.my_hexagonal_app.application.service.UserService;
import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testCreate() throws Exception {
        String json = """
                {
                "id": "1",
                "name": "user1",
                "email": "email1@mail.com"
                }
                """;

        User user = new User(1L, "user1", "email1@mail.com");
        when(userService.createUser(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("user1"))
                .andExpect(jsonPath("$.email").value("email1@mail.com"));
    }

    @Test
    void testGetAll() throws Exception {
        User user1 = new User(1L, "user1", "email1@mail.com");
        User user2 = new User(2L, "user2", "email2@mail.com");
        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("user1"))
                .andExpect(jsonPath("$[0].email").value("email1@mail.com"));
    }

    @Test
    void testDelete() throws Exception {
        String json = """
                {
                "id" : "1",
                "name": "user1",
                "email": "email1@mail.com"
                }
                """;
        doNothing().when(userService).deleteUser(any(User.class));
        mockMvc.perform(delete("/users/delete").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Suppression avec succès !!"));
    }

    @Test
    void testUpdateEmail() throws Exception {
        User userUpdated = new User(1L, "user1", "email11@mail.com");
        when(userService.updateEmailUser(1L, "email11@mail.com")).thenReturn(userUpdated);
        mockMvc.perform(post("/users/updateEmail")
                .param("id", "1").param("email", "email11@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("user1"))
                .andExpect(jsonPath("email").value("email11@mail.com"));
    }
}