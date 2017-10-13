package service.impl;

import domain.entities.Airport;
import exception.PersistenceException;
import persistence.dao.impl.factory.DAOFactoryImpl;
import service.AirportService;

import java.util.List;

/**
 * Created by zom on 12.10.2017.
 */
public class AirportServiceImpl implements AirportService {
    @Override
    public void create(Airport object, String tableName) throws PersistenceException {

    }

    @Override
    public void update(Airport object) throws PersistenceException {

    }

    @Override
    public void delete(Long id) throws PersistenceException {

    }

    @Override
    public Airport getById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public List<Airport> getAll(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getAirportDAO().getAll(tableName);
    }

    @Override
    public List<Airport> getPart(int page, String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount(String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getItemsPerPage(String tableName) {
        return null;
    }
}
