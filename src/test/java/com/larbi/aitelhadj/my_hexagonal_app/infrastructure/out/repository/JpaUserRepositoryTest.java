package com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.repository;

import com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.entity.UserEntity;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
class JpaUserRepositoryTest {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Test
    void testFindAll() {
        List<UserEntity> userEntityList = jpaUserRepository.findAll();
        assertThat(userEntityList).size().isEqualTo(2);
        assertEquals("user1", userEntityList.getFirst().getName());
        assertEquals("email1@mail.com", userEntityList.getFirst().getEmail());
    }

    @Test
    void testFindById() {
        UserEntity userEntity = jpaUserRepository.findById(1L).orElse(null);
        assertNotNull(userEntity);
        assertEquals("user1", userEntity.getName());
        assertEquals("email1@mail.com", userEntity.getEmail());
    }

    @Test
    void testSave() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("user3");
        userEntity.setEmail("email3@mail.com");
        UserEntity userEntitySaved = jpaUserRepository.save(userEntity);
        assertEquals("user3", userEntitySaved.getName());
        assertEquals("email3@mail.com", userEntitySaved.getEmail());
    }

    @Test
    void testDelete() {
        jpaUserRepository.deleteById(1L);
        UserEntity userEntityDeleted = jpaUserRepository.findById(1L).orElse(null);
        assertNull(userEntityDeleted);
    }

    @Test
    void findByName() {
        UserEntity userEntityByName = jpaUserRepository.findByName("user1");
        assertNotNull(userEntityByName);
        assertEquals(1L, userEntityByName.getId());
        assertEquals("email1@mail.com", userEntityByName.getEmail());

    }
}