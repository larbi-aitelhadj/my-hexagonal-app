package com.larbi.aitelhadj.my_hexagonal_app.application.service;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import com.larbi.aitelhadj.my_hexagonal_app.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @larbi.aitelhadj
 *
 */
@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    public User updateUser(User user) {
        return userRepositoryPort.update(user);
    }

    public void deleteUser(User user) {
        userRepositoryPort.delete(user);
    }
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }
}
