package service;

import domain.entities.User;
import exception.PersistenceException;

import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public interface UserService extends Service<User> {
    @Override
    void create(User object) throws PersistenceException;

    @Override
    void update(User object) throws PersistenceException;

    @Override
    void delete(Integer id) throws PersistenceException;

    @Override
    User getById(Integer id) throws PersistenceException;

    @Override
    List<User> getAll() throws PersistenceException;

    @Override
    List<User> getPart(Integer from, Integer to) throws PersistenceException;

    @Override
    Integer getCount() throws PersistenceException;

    @Override
    Integer getItemsPerPage();

    User getUserByLoginPassword(String login, String password) throws PersistenceException;

    boolean isUserExists(String login, String email) throws PersistenceException;

    void setAdmin(Integer id) throws PersistenceException;
}
