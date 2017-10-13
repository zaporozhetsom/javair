package persistence.dao.impl;

import domain.Entity;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import persistence.connection.ConnectionPool;
import persistence.dao.interfaces.DAO;
import persistence.dao.util.SQLQueries;

import java.sql.*;
import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
abstract class AbstractDAO<T extends Entity> implements DAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final SQLQueries queries = SQLQueries.getInstance();
    private Connection connectionForTransaction = null;

    AbstractDAO() {
    }


    @Override
    public Integer createAndGetId(T object, String tableName) throws PersistenceException {

        return null;
    }

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistenceException;

    protected abstract void prepareStatementINSERT(PreparedStatement statement, T object) throws PersistenceException;

    protected Connection getConnection() throws PersistenceException {
        Connection connection;
        if (connectionForTransaction == null) {
            connection = ConnectionPool.getInstance().getConnection();
        } else {
            connection = connectionForTransaction;
        }
        return connection;
    }

    @Override
    public void create(T object, String table) throws PersistenceException {
        String sql = SQLQueries.getInstance().getCreateQuery(table);
        Long id = 0L;

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatementINSERT(statement, object);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                    object.setId(id);
                } else {
                    throw new SQLException("No ID received");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("[ADAO]Create object exception", e);
            throw new PersistenceException("Create object exception");
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.info("[ADAO]Close connection fail", e);
            }
        }

    }

    @Override
    public void update(T object, String tableName) throws PersistenceException {

    }

    @Override
    public void delete(Integer id, String tableName) throws PersistenceException {

    }

    @Override
    public T getById(Integer id, String tableName) throws PersistenceException {
        return null;
    }

    @Override
    public List<T> getAll(String tableName) throws PersistenceException {
        List<T> list;
        String sql = queries.getSelectAllQuery(tableName);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            LOGGER.debug("[ADAO]Statement = " + statement);

        } catch (SQLException e) {
            LOGGER.error("[ADAO]Getting all records exception", e);
            throw new PersistenceException("Getting data exception");
        }
        return list;

    }

    @Override
    public List<T> getPart(int page, String tableName) throws PersistenceException {
        List<T> list;
        int itemsPerPage = queries.getItemsPerPage(tableName);
        int from = itemsPerPage * (page - 1);
        String sqlQuery = queries.getPaginationQuery(tableName);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, itemsPerPage);
            statement.setInt(2, from);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error("[ADAO]Cannot get #" + page + " part(" + itemsPerPage + " items) from table [" + tableName + "]", e);
            throw new PersistenceException("Cannot retrieve data");
        }
        return list;
    }

    @Override
    public Integer getCount(String tableName) throws PersistenceException {
        String sql = queries.getSelectCountQuery(tableName);
        Integer count = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            LOGGER.error("[ADAO]Cannot count items in table [" + tableName + "]", e);
            throw new PersistenceException("Cannot count items");
        }
        return count;
    }

    public Integer getItemsPerPage(String tableName) {
        return queries.getItemsPerPage(tableName);
    }

    @Override
    public void setConnection(Connection connection) {
        this.connectionForTransaction = connection;
    }

    @Override
    public void closeConnection() {
        try {
            this.connectionForTransaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
