package service.factory;

import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Created by zom on 03.10.2017.
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private static volatile ServiceFactoryImpl instance;

    private final Logger log = Logger.getLogger(getClass());
    private final UserService userService;

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
    }

    @Override
    public UserService getUserService() {
        log.info("User Service returned");
        return userService;
    }
}
