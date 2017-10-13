package persistence.dao.impl;

import domain.entities.Aircraft;
import domain.entities.Dummy;
import exception.PersistenceException;
import persistence.connection.ConnectionPool;
import persistence.dao.interfaces.AircraftDAO;
import persistence.dao.util.SQLQueries;

import java.sql.*;
import java.util.*;

/**
 * Created by zom on 12.10.2017.
 */
public class AircraftDAOImpl extends AbstractDAO<Aircraft> implements AircraftDAO {
    @Override
    protected List<Aircraft> parseResultSet(ResultSet resultSet) throws PersistenceException {
        List<Aircraft> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Aircraft user = new Aircraft.Builder()
                        .id(resultSet.getLong("ac.id"))
                        .registrationIdentifier(resultSet.getString("ac.reg_id"))
                        .aircraftManufacturerId(resultSet.getLong("am.manufacturer_id"))
                        .model(resultSet.getString("am.model"))
                        .capacity(resultSet.getInt("am.capacity"))
                        .aircraftLocationAirportId(resultSet.getLong("al.airport_id"))
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Retrieving Aircraft data from DB error ");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void prepareStatementINSERT(PreparedStatement statement, Aircraft object) throws PersistenceException {

    }

    @Override
    public List<Dummy> getAllModels(String tableName) throws PersistenceException {
        Set<Dummy> set = new HashSet<>();
        ResultSet resultSet;
        String sql = SQLQueries.getInstance().getSelectAllQuery(tableName);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Dummy model = new Dummy.Builder()
                        .id(resultSet.getLong("ac.model"))
                        .thirdPartyId(resultSet.getLong("am.manufacturer_id"))
                        .name(resultSet.getString("am.model"))
                        .build();
                set.add(model);
            }
        } catch (SQLException e) {
            LOGGER.error("Retrieving Aircraft data from DB error ");
            e.printStackTrace();
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<Dummy> getAllManufacturers(String tableName) throws PersistenceException {
        Set<Dummy> set = new HashSet<>();
        ResultSet resultSet;
        String sql = SQLQueries.getInstance().getSelectAllQuery(tableName);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {

            resultSet = statement.executeQuery(sql);

            LOGGER.debug("St rs" + resultSet.toString());

            while (resultSet.next()) {
                Dummy model = new Dummy.Builder()
                        .id(resultSet.getLong("amf.id"))
                        .thirdPartyId(-1L)
                        .name(resultSet.getString("amf.name"))
                        .build();
                set.add(model);
            }
        } catch (SQLException e) {
            LOGGER.error("Retrieving Aircraft data from DB error ");
            e.printStackTrace();
        }
        return new ArrayList<>(set);
    }


}
