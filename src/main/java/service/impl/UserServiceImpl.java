package service.impl;

import domain.entities.User;
import exception.PersistenceException;
import service.UserService;

import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void create(User object) throws PersistenceException {

    }

    @Override
    public void update(User object) throws PersistenceException {

    }

    @Override
    public void delete(Integer id) throws PersistenceException {

    }

    @Override
    public User getById(Integer id) throws PersistenceException {
        return null;
    }

    @Override
    public List<User> getAll() throws PersistenceException {
        return null;
    }

    @Override
    public List<User> getPart(Integer from, Integer to) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount() throws PersistenceException {
        return null;
    }

    @Override
    public Integer getItemsPerPage() {
        return null;
    }

    @Override
    public User getUserByLoginPassword(String login, String password) throws PersistenceException {
        return null;
    }

    @Override
    public boolean isUserExists(String login, String email) throws PersistenceException {
        return false;
    }

    @Override
    public void setAdmin(Integer id) throws PersistenceException {

    }
}
