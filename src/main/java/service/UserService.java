package service;

import domain.entities.User;
import exception.PersistenceException;


/**
 * Created by zom on 03.10.2017.
 */
public interface UserService extends Service<User> {

    User getUserByLoginPassword(String login, String password) throws PersistenceException;

    boolean isUserExists(String login) throws PersistenceException;

    void setAdmin(Long id) throws PersistenceException;
}
