package service.factory;

import org.apache.log4j.Logger;
import service.FlightService;
import service.UserService;
import service.impl.FlightServiceImpl;
import service.impl.UserServiceImpl;

/**
 * Created by zom on 03.10.2017.
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private static volatile ServiceFactoryImpl instance;

    private final Logger log = Logger.getLogger(getClass());
    private final UserService userService;
    private final FlightService flightService;

    public static ServiceFactoryImpl getInstance() {
        ServiceFactoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceFactoryImpl();
                    instance.log.info("Service factory initialized");
                }
            }
        }
        return localInstance;
    }

    private ServiceFactoryImpl() {
        userService = new UserServiceImpl();
        flightService = new FlightServiceImpl();
    }

    @Override
    public UserService getUserService() {
        log.debug("User Service returned");
        return userService;
    }

    @Override
    public FlightService getFlightService() {
        return flightService;
    }
}
