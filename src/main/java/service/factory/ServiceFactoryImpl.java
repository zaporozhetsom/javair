package service.factory;

import org.apache.log4j.Logger;
import service.AircraftService;
import service.AirportService;
import service.FlightService;
import service.UserService;
import service.impl.AircraftServiceImpl;
import service.impl.AirportServiceImpl;
import service.impl.FlightServiceImpl;
import service.impl.UserServiceImpl;

/**
 * Created by zom on 03.10.2017.
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private static volatile ServiceFactoryImpl instance;

    private static final Logger LOGGER = Logger.getLogger(ServiceFactoryImpl.class.getName());
    private final UserService userService;
    private final FlightService flightService;
    private final AirportService airportService;
    private final AircraftService aircraftService;

    public static ServiceFactoryImpl getInstance() {
        ServiceFactoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceFactoryImpl();
                    LOGGER.info("Service factory initialized");
                }
            }
        }
        return localInstance;
    }

    private ServiceFactoryImpl() {
        userService = new UserServiceImpl();
        flightService = new FlightServiceImpl();
        airportService = new AirportServiceImpl();
        aircraftService = new AircraftServiceImpl();
    }

    @Override
    public UserService getUserService() {
        LOGGER.debug("User Service returned");
        return userService;
    }

    @Override
    public FlightService getFlightService() {
        return flightService;
    }

    @Override
    public AirportService getAirportService() {
        return airportService;
    }

    @Override
    public AircraftService getAircraftService() {
        return aircraftService;
    }
}
