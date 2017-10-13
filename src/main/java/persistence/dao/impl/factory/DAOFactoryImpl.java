package persistence.dao.impl.factory;

import org.apache.log4j.Logger;
import persistence.dao.impl.AircraftDAOImpl;
import persistence.dao.impl.AirportDAOImpl;
import persistence.dao.impl.FlightDAOImpl;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.interfaces.AircraftDAO;
import persistence.dao.interfaces.AirportDAO;
import persistence.dao.interfaces.FlightDAO;
import persistence.dao.interfaces.factory.DAOFactory;
import persistence.dao.interfaces.UserDAO;


/**
 * DAO Factory. Singleton
 */
public class DAOFactoryImpl implements DAOFactory {

    private static volatile DAOFactoryImpl instance;

    private static final Logger LOGGER = Logger.getLogger(DAOFactory.class.getName());

    private final UserDAO userDao = initializeUserDAO();
    private final FlightDAO flightDao = initializeFlightDAO();
    private final AirportDAO airportDao = initializeAirportDAO();
    private final AircraftDAO aircraftDao = initializeAircraftDAO();

    private DAOFactoryImpl() {
    }


    public static DAOFactoryImpl getInstance() {
        DAOFactoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (DAOFactoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOFactoryImpl();
                    LOGGER.info("DAO factory initialized");
                }
            }
        }
        return localInstance;
    }

    private UserDAO initializeUserDAO(){
        return new UserDAOImpl();
    }

    private FlightDAO initializeFlightDAO() {
        return new FlightDAOImpl();
    }

    private AirportDAO initializeAirportDAO() {
        return new AirportDAOImpl();
    }

    private AircraftDAO initializeAircraftDAO() {
        return new AircraftDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDao;
    }

    @Override
    public FlightDAO getFlightsDAO() {
        return flightDao;
    }

    @Override
    public AirportDAO getAirportDAO() {
        return airportDao;
    }

    @Override
    public AircraftDAO getAircraftDAO() {
        return aircraftDao;
    }


}
