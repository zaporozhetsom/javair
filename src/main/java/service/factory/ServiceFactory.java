package service.factory;

import service.FlightService;
import service.UserService;

/**
 * Created by zom on 03.10.2017.
 */
public interface ServiceFactory {
    UserService getUserService();

    FlightService getFlightService();
}
