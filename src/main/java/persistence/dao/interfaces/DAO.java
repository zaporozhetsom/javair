package persistence.dao.interfaces;

import domain.Entity;
import exception.PersistenceException;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public interface DAO<T extends Entity> {
    Integer createAndGetId(T object) throws PersistenceException;

    void create(T object) throws PersistenceException;

    void update(T object) throws PersistenceException;

    void delete(Integer id) throws PersistenceException;

    T getById(Integer id) throws PersistenceException;

    List<T> getAll() throws PersistenceException;

    List<T> getPart(int from) throws PersistenceException;

    Integer getCount() throws PersistenceException;

    Integer getRecordsOnPage();

    void setConnection(Connection connection);

    void closeConnection();

    void deleteAllRecordsAndRestartSequence() throws PersistenceException;
}
