package service.impl;

import domain.entities.User;
import exception.PersistenceException;
import persistence.dao.impl.factory.DAOFactoryImpl;
import service.UserService;

import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void create(User object, String tableName) throws PersistenceException {
        DAOFactoryImpl.getInstance().getUserDAO().create(object, tableName);
    }

    @Override
    public void update(User object) throws PersistenceException {

    }

    @Override
    public void delete(Long id) throws PersistenceException {

    }

    @Override
    public User getById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public List<User> getAll(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getUserDAO().getAll(tableName);
    }

    @Override
    public List<User> getPart(int page, String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount(String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getItemsPerPage(String tableName) {
        return null;
    }

    @Override
    public User getUserByLoginPassword(String login, String password) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getUserDAO().getUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean isUserExists(String login) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getUserDAO().isUserExists(login);
    }

    @Override
    public void setAdmin(Long id) throws PersistenceException {

    }
}
