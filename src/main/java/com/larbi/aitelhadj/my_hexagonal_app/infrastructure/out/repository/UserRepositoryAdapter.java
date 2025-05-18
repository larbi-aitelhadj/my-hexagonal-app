package com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.repository;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import com.larbi.aitelhadj.my_hexagonal_app.domain.port.UserRepositoryPort;
import com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.entity.UserEntity;
import org.modelmapper.ModelMapper;
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

    private User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
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
    public void deleteById(Long id) {
        jpaUserRepository.findById(id).ifPresent(jpaUserRepository::delete);
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        UserEntity userEntity = jpaUserRepository.findById(id).orElse(null);
        if (userEntity != null) {
            user = toDomain(userEntity);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return jpaUserRepository.findAll().stream().map(userEntity ->
            modelMapper.map(userEntity, User.class)
        ).toList();
    }

}
