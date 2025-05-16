package com.larbi.aitelhadj.my_hexagonal_app.domain.port;

import com.larbi.aitelhadj.my_hexagonal_app.domain.model.User;
import java.util.List;

/**
 * @larbi.aitelhadj
 *
 */
public interface UserRepositoryPort {

    User save(User user);
    User update(User user);
    void delete(User user);
    List<User> findAll();

}
