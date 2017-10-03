package persistence.dao;

import domain.Entity;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import persistence.dao.interfaces.DAO;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by zom on 03.10.2017.
 */
public abstract class AbstractDAO<T extends Entity> implements DAO<T> {
    protected final Logger log = Logger.getLogger(getClass());

    protected final Properties queries;
    protected final int itemsPerPage;
    protected Connection transactionConnection = null;

    public AbstractDAO(Properties queries, int itemsPerPage) {
        this.queries = queries;
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public Integer createAndGetId(T object) throws PersistenceException {
        String sql = getCreateQuery();
        int id = 0;

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatementForInsert(statement, object);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = (int) resultSet.getLong(1);
                } else {
                    throw new SQLException("No ID obtained");
                }
            }
        } catch (SQLException e) {
            log.error("Creating object exception", e);
            throw new PersistenceException("Creating object exception");
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.info("Closing connection exception", e);
            }
        }
        return id;
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
    public List<T> getAll() throws PersistenceException {
        return null;
    }

    @Override
    public List<T> getPart(int from) throws PersistenceException {
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
    public void setConnection(Connection connection) {
        this.transactionConnection = connection;
    }

    @Override
    public void closeConnection() {
        try {
            this.transactionConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllRecordsAndRestartSequence() throws PersistenceException {

    }
}
