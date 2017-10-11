package service;

import domain.entities.Flight;
import exception.PersistenceException;
import service.filter.FlightFilter;

import java.util.List;

/**
 * Created by zom on 11.10.2017.
 */
public interface FlightService extends Service<Flight> {
    List<Flight> getPart(int page, FlightFilter filter) throws PersistenceException;

    Integer getCount(FlightFilter filter);
}
