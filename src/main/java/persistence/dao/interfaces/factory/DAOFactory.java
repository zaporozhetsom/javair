package persistence.dao.interfaces.factory;

import persistence.dao.interfaces.*;


public interface DAOFactory {

    UserDAO getUserDAO ();

    FlightDAO getFlightsDAO();

    AirportDAO getAirportDAO();

    AircraftDAO getAircraftDAO();
}
