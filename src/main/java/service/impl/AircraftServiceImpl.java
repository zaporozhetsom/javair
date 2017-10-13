package service.impl;

import domain.entities.Aircraft;
import domain.entities.Dummy;
import exception.PersistenceException;
import persistence.dao.impl.factory.DAOFactoryImpl;
import service.AircraftService;

import java.util.List;

/**
 * Created by zom on 12.10.2017.
 */
public class AircraftServiceImpl implements AircraftService {
    @Override
    public void create(Aircraft object, String tableName) throws PersistenceException {

    }

    @Override
    public void update(Aircraft object) throws PersistenceException {

    }

    @Override
    public void delete(Long id) throws PersistenceException {

    }

    @Override
    public Aircraft getById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public List<Aircraft> getAll(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getAircraftDAO().getAll(tableName);
    }

    @Override
    public List<Aircraft> getPart(int page, String tableName) throws PersistenceException {
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

    @Override
    public List<Dummy> getAllModels(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getAircraftDAO().getAllModels(tableName);
    }

    @Override
    public List<Dummy> getAllManufacturers(String tableName) throws PersistenceException {
        return DAOFactoryImpl.getInstance().getAircraftDAO().getAllManufacturers(tableName);
    }
}
