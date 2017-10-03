package persistence.dao.impl.factory;

import exception.GetPropertiesException;
import org.apache.log4j.Logger;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.interfaces.factory.DAOFactory;
import persistence.dao.interfaces.UserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zom on 20.09.2017.
 */
public class DAOFactoryImpl implements DAOFactory {

    private static volatile DAOFactoryImpl instance;
    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class.getName());
    public static final String RECORDS_ON_PAGE = "recordsOnPage";

    private final UserDAO userDao = initializeUserDAO();

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

    private UserDAO initializeUserDAO(){
        Properties properties = getSqlFromProperties("user");
        return new UserDAOImpl(properties, recordsOnPage(properties));
    }

    @Override
    public UserDAO getUserDAO() {
        return userDao;
    }

    private Integer recordsOnPage(Properties properties) {
        return Integer.valueOf(properties.getProperty(RECORDS_ON_PAGE));
    }

    private Properties getSqlFromProperties(String table) {
        Properties properties;
        try {
            properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("sqlQueries/" + table + ".properties");
            if (inputStream == null) {
                logger.error("There is no property file for table:" + table);
                throw new GetPropertiesException("Cannot find file");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Cannot load properties file for table:" + table, e);
            throw new GetPropertiesException("Cannot load properties file for  table:" + table, e);
        }
        return properties;
    }
}
