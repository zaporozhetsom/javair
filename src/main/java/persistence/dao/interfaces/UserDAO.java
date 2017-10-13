package persistence.dao.interfaces;

import domain.entities.User;
import exception.PersistenceException;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zom on 20.09.2017.
 */
public interface UserDAO extends DAO<User> {

    @Override
    void create(User object, String tableName) throws PersistenceException;

    @Override
    void update(User object, String tableName) throws PersistenceException;

    @Override
    void delete(Integer id, String tableName) throws PersistenceException;

    @Override
    User getById(Integer id, String tableName) throws PersistenceException;

    @Override
    List<User> getAll(String tableName) throws PersistenceException;

    @Override
    List<User> getPart(int from, String tableName) throws PersistenceException;

    @Override
    Integer getCount(String tableName) throws PersistenceException;

    Integer getItemsPerPage(String tableName);

    @Override
    void setConnection(Connection connection);

    @Override
    void closeConnection();


    User getUserByLoginAndPassword(String login, String password) throws PersistenceException;

    boolean isUserExists(String login) throws PersistenceException;

    void setAdmin(Integer id) throws PersistenceException;
}
