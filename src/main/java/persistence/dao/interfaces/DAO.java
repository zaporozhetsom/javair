package persistence.dao.interfaces;

import domain.Entity;
import exception.PersistenceException;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public interface DAO<T extends Entity> {
    Integer createAndGetId(T object, String tableName) throws PersistenceException;

    void create(T object, String tableName) throws PersistenceException;

    void update(T object, String tableName) throws PersistenceException;

    void delete(Integer id, String tableName) throws PersistenceException;

    T getById(Integer id, String tableName) throws PersistenceException;

    List<T> getAll(String tableName) throws PersistenceException;

    List<T> getPart(int from, String tableName) throws PersistenceException;

    Integer getCount(String tableName) throws PersistenceException;

    Integer getItemsPerPage(String tableName);

    void setConnection(Connection connection);

    void closeConnection();

}
