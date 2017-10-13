package persistence.dao.interfaces;

import domain.entities.Aircraft;
import domain.entities.Dummy;
import exception.PersistenceException;

import java.util.List;

/**
 * Created by zom on 12.10.2017.
 */
public interface AircraftDAO extends DAO<Aircraft> {
    List<Dummy> getAllModels(String tableName) throws PersistenceException;

    List<Dummy> getAllManufacturers(String tableName) throws PersistenceException;
}
