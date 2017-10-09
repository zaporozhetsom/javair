package persistence.dao.interfaces;

import domain.entities.User;
import exception.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zom on 20.09.2017.
 */
public interface UserDAO extends DAO<User> {

    @Override
    void create(User object) throws PersistenceException;

    @Override
    void update(User object) throws PersistenceException;

    @Override
    void delete(Integer id) throws PersistenceException;

    @Override
    User getById(Integer id) throws PersistenceException;

    @Override
    List<User> getAll(String tableName) throws PersistenceException;

    @Override
    List<User> getPart(int from) throws PersistenceException;

    @Override
    Integer getCount() throws PersistenceException;

    Integer getRecordsOnPage();

    @Override
    void setConnection(Connection connection);

    @Override
    void closeConnection();


    User getUserByLoginAndPassword(String login, String password) throws PersistenceException;

    boolean isUserExists(String login, String email) throws PersistenceException;

    void setAdmin(Integer id) throws PersistenceException;
}
