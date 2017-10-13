package persistence.dao.impl;

import domain.entities.Flight;
import domain.util.FlightType;
import exception.PersistenceException;
import persistence.connection.ConnectionPool;
import persistence.dao.interfaces.FlightDAO;
import persistence.dao.util.SQLQueries;
import service.filter.FlightFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zom on 11.10.2017.
 */
public class FlightDAOImpl extends AbstractDAO<Flight> implements FlightDAO {
    private final String tableName = SQLQueries.TABLE_NAME_FLIGHT;

    @Override
    protected List<Flight> parseResultSet(ResultSet resultSet) throws PersistenceException {
        List<Flight> flights = new ArrayList<>();
        try {
            while (resultSet.next()) {

                Flight flight = new Flight.Builder()
                        .id(resultSet.getLong("f.id"))
                        .aircraftId(resultSet.getString("ac.reg_id"))
                        .aircrew(resultSet.getLong("acr.id"))
                        .backupAircrew(resultSet.getLong("acr2.id"))
                        .destinationAirport(resultSet.getLong("ap.id"))
                        .flightType(FlightType.valueOf(resultSet.getString("ft.name")))
                        .forwardDepartureDateTime(resultSet.getTimestamp("f.fw_depart_date"))
                        .forwardArrivalDateTime(resultSet.getTimestamp("f.fw_arriv_date"))
                        .backwardDepartureDateTime(resultSet.getTimestamp("f.bw_depart_date"))
                        .backwardArrivalDateTime(resultSet.getTimestamp("f.bw_arriv_date"))
                        .build();

                flights.add(flight);
            }
        } catch (SQLException e) {
            LOGGER.error("Parsing result set exception.", e);
            throw new PersistenceException("Getting data exception");
        }
        return flights;
    }

    @Override
    protected void prepareStatementINSERT(PreparedStatement statement, Flight object) throws PersistenceException {

    }

    @Override
    public Integer getCount(FlightFilter filter) {
        int count = 1;
        String sql = SQLQueries.getInstance().getSelectCountFilterQuery(tableName) + filter.getQuery();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting count", e);
        }
        return count;
    }

    @Override
    public List<Flight> getPart(int page, FlightFilter filter) throws PersistenceException {
        List<Flight> list;
        int itemsPerPage = SQLQueries.getInstance().getItemsPerPage(tableName);
        int from = itemsPerPage * (page - 1);

        StringBuilder sql = new StringBuilder(SQLQueries.getInstance().getSelectAllQuery(tableName));

        sql.append(filter.getQuery()).append(" LIMIT ").append(itemsPerPage).append(" OFFSET ").append(from);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Retrieving data with filter exception", e);
            throw new PersistenceException("Retrieving flights exception");
        }
        return list;
    }
}
