package persistence.dao.interfaces.factory;

import persistence.dao.interfaces.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zom on 20.09.2017.
 */
public interface DAOFactory {

    UserDAO getUserDAO ();
}
