package com.larbi.aitelhadj.my_hexagonal_app.adapter.out.persistence;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import com.larbi.aitelhadj.my_hexagonal_app.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @larbi.aitelhadj
 *
 */
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserEntity saved = jpaUserRepository.save(entity);
        user.setId(saved.getId());
        return user;
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = jpaUserRepository.findByName(user.getName());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        UserEntity updated = jpaUserRepository.save(userEntity);
        user.setId(updated.getId());
        return user;
    }

    @Override
    public void delete(User user) {
        UserEntity userEntity = jpaUserRepository.findByName(user.getName());
        jpaUserRepository.deleteById(userEntity.getId());
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(e -> {
            User u = new User();
            u.setId(e.getId());
            u.setName(e.getName());
            u.setEmail(e.getEmail());
            return u;
        }).toList();
    }
}
