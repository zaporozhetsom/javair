package service.impl;

import domain.entities.Flight;
import exception.PersistenceException;
import persistence.dao.impl.factory.DAOFactoryImpl;
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
        return DAOFactoryImpl.getInstance().getFlightsDAO().getAll(tableName);
    }

    @Override
    public List<Flight> getPart(int page, String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getFlightsDAO().getPart(page, tableName);
    }

    @Override
    public Integer getCount(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getFlightsDAO().getCount(tableName);
    }

    @Override
    public Integer getItemsPerPage(String tableName) {
        return DAOFactoryImpl.getInstance().getFlightsDAO().getItemsPerPage(tableName);
    }

    @Override
    public List<Flight> getPart(int page, FlightFilter filter) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getFlightsDAO().getPart(page, filter);
    }

    @Override
    public Integer getCount(FlightFilter filter) {

        return DAOFactoryImpl.getInstance().getFlightsDAO().getCount(filter);
    }
}
