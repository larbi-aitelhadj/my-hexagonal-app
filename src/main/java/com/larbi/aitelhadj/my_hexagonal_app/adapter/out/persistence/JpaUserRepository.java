package com.larbi.aitelhadj.my_hexagonal_app.adapter.out.persistence;

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
