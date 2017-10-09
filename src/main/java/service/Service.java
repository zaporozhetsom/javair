package service;

import domain.Entity;
import exception.PersistenceException;

import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public interface Service<T extends Entity> {
    void create(T object) throws PersistenceException;
    void update(T object) throws PersistenceException;
    void delete(Long id) throws PersistenceException;
    T getById(Long id) throws PersistenceException;
    List<T> getAll(String tableName) throws PersistenceException;
    List<T> getPart(Long from, Long to) throws PersistenceException;
    Integer getCount() throws PersistenceException;
    Integer getItemsPerPage();
}
