package com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.repository;

import com.larbi.aitelhadj.my_hexagonal_app.infrastructure.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @larbi.aitelhadj
 *
 */
@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);

}
