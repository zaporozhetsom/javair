package persistence.dao.impl;

import domain.Entity;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import persistence.connection.ConnectionPool;
import persistence.dao.interfaces.DAO;
import persistence.dao.util.SQLQueries;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by zom on 03.10.2017.
 */
abstract class AbstractDAO<T extends Entity> implements DAO<T> {
    final Logger log = Logger.getLogger(getClass());

    final SQLQueries queries = SQLQueries.getInstance();
//    final Properties sqlQuery;
//    final Integer recordsOnPage;
    Connection connectionForTransaction = null;

    AbstractDAO() { //Properties sqlQuery, Integer recordsOnPage
//        this.sqlQuery = sqlQuery;
//        this.recordsOnPage = recordsOnPage;
    }

//    private String getSqlQueryCREATE() {
//        return sqlQuery.getProperty("create");
//    }

//    private String getSqlQuerySELECT() {
//        return sqlQuery.getProperty("select.all");
//    }

//    @Override
    public Integer createAndGetId(T object) throws PersistenceException {
//        String sql = getSqlQueryCREATE();
        Integer id = 0;

/*        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatementINSERT(statement, object);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = (int) resultSet.getLong(1);
                } else {
                    throw new SQLException("No ID received");
                }
            }
        } catch (SQLException e) {
            log.error("[ADAO]Create object exception", e);
            throw new PersistenceException("Create object exception");
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.info("[ADAO]Close connection fail", e);
            }
        }*/
        return id;
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
    public void create(T object) throws PersistenceException {

    }

    @Override
    public void update(T object) throws PersistenceException {

    }

    @Override
    public void delete(Integer id) throws PersistenceException {

    }

    @Override
    public T getById(Integer id) throws PersistenceException {
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

            log.debug("[ADAO]Statement = " + statement);

        } catch (SQLException e) {
            log.error("[ADAO]Getting all records exception", e);
            throw new PersistenceException("Getting data exception");
        }
        return list;

    }

    @Override
    public List<T> getPart(int from) throws PersistenceException {
        return null;
    }

    @Override
    public Integer getCount() throws PersistenceException {
        return null;
    }

    public Integer getRecordsOnPage() {
        return null;
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
