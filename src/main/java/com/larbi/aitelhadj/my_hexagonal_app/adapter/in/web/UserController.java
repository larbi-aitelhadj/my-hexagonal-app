package com.larbi.aitelhadj.my_hexagonal_app.adapter.in.web;

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

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
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
