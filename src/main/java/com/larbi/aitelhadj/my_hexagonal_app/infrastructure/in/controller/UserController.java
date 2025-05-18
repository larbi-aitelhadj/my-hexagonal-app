package com.larbi.aitelhadj.my_hexagonal_app.infrastructure.in.controller;

import com.larbi.aitelhadj.my_hexagonal_app.application.service.UserService;
import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @larbi.aitelhadj
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestParam Long id, @RequestParam String email) {
        try {
            return ResponseEntity.ok(userService.updateEmailUser(id, email));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().body("Suppression avec succès !!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
