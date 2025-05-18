package com.larbi.aitelhadj.my_hexagonal_app.domain.port;

import java.util.List;

public interface RepositoryPort<T, ID> {

    T save(T t);
    T update(T t);
    void delete(T t);
    void deleteById(ID id);
    T findById(ID id);
    List<T> findAll();

}
