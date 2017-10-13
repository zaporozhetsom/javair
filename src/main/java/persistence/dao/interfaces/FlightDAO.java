package persistence.dao.interfaces;

import domain.entities.Flight;
import exception.PersistenceException;
import service.filter.FlightFilter;

import java.util.List;

/**
 * Created by zom on 11.10.2017.
 */
public interface FlightDAO extends DAO<Flight> {
    Integer getCount(FlightFilter filter);

    List<Flight> getPart(int page, FlightFilter filter) throws PersistenceException;
}
