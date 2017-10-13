package persistence.dao.impl;

import domain.entities.Airport;
import exception.PersistenceException;
import persistence.dao.interfaces.AirportDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zom on 12.10.2017.
 */
public class AirportDAOImpl extends AbstractDAO<Airport> implements AirportDAO {
    @Override
    protected List<Airport> parseResultSet(ResultSet resultSet) throws PersistenceException {
        List<Airport> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Airport user = new Airport.Builder()
                        .city(resultSet.getString("city"))
                        .IATACode(resultSet.getString("IATACode"))
                        .distanceToBaseAirport(resultSet.getInt("distance_to_base_airport"))
                        .id(resultSet.getLong("id"))
                        .baseAirport(resultSet.getBoolean("base_airport"))
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Retrieving Airport data from DB error ");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void prepareStatementINSERT(PreparedStatement statement, Airport object) throws PersistenceException {

    }
}
