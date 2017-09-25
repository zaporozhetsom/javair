package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zom on 20.09.2017.
 */
public interface DAOFactory {
    public Connection getConnection () throws SQLException;

    public UserDAO getUserDAO (Connection connection);
}
