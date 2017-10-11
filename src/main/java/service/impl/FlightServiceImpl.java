package service.impl;

import domain.entities.Flight;
import exception.PersistenceException;
import service.FlightService;
import service.filter.FlightFilter;

import java.util.List;

/**
 * Created by zom on 11.10.2017.
 */
public class FlightServiceImpl implements FlightService {
    @Override
    public void create(Flight object, String tableName) throws PersistenceException {

    }

    @Override
    public void update(Flight object) throws PersistenceException {

    }

    @Override
    public void delete(Long id) throws PersistenceException {

    }

    @Override
    public Flight getById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public List<Flight> getAll(String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public List<Flight> getPart(int from) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount() throws PersistenceException {
        return null;
    }

    @Override
    public Integer getItemsPerPage() {
        return null;
    }

    @Override
    public List<Flight> getPart(int page, FlightFilter filter) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount(FlightFilter filter) {
        return null;
    }
}
