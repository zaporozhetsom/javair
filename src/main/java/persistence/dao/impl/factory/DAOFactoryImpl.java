package persistence.dao.impl.factory;

import org.apache.log4j.Logger;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.interfaces.factory.DAOFactory;
import persistence.dao.interfaces.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zom on 20.09.2017.
 */
public class DAOFactoryImpl implements DAOFactory {

    private static volatile DAOFactoryImpl instance;
    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class.getName());

    private DAOFactoryImpl() {
    }

    public static DAOFactoryImpl getInstance() {
        DAOFactoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (DAOFactoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOFactoryImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();

    }
}
