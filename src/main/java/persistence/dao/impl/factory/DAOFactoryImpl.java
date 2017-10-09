package persistence.dao.impl.factory;

import org.apache.log4j.Logger;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.interfaces.factory.DAOFactory;
import persistence.dao.interfaces.UserDAO;


/**
 * DAO Factory. Singleton
 */
public class DAOFactoryImpl implements DAOFactory {

    private static volatile DAOFactoryImpl instance;

    private final Logger log = Logger.getLogger(getClass());

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
                    instance.log.info("DAO factory initialized");
                }
            }
        }
        return localInstance;
    }

    private UserDAO initializeUserDAO(){
        return new UserDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDao;
    }



}
