package com.portfolio.fxexpensetrack.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericCRUD<T> {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(Long id);
    void delete(T entity);
}
