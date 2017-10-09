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
    public void create(User object) throws PersistenceException {
        DAOFactoryImpl.getInstance().getUserDAO().create(object);
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
    public List<User> getPart(Long from, Long to) throws PersistenceException {
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
    public void setAdmin(Long id) throws PersistenceException {

    }
}
