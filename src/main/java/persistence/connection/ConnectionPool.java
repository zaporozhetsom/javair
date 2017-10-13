package persistence.connection;

import exception.ConnectionException;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zom on 29.09.2017.
 */
public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javair";
    private static final String DB_USERNAME = "zatsMe";
    private static final String DB_PASSWORD = "ZRoot";

    private DataSource dataSource;

    private ConnectionPool() {
        init();
    }

    private void init() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(DB_URL);
        poolProperties.setDriverClassName(DB_DRIVER);
        poolProperties.setUsername(DB_USERNAME);
        poolProperties.setPassword(DB_PASSWORD);
        poolProperties.setInitialSize(3);
        dataSource = new DataSource();
        dataSource.setPoolProperties(poolProperties);
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        Connection connection;

        try {
            connection = dataSource.getConnection();
            LOGGER.info("Connection created successfully" + connection);
            return connection;
        } catch (SQLException e) {
            LOGGER.error("Cannot create connection", e);
            throw new ConnectionException();
        }

    }

    public void close() {
        dataSource.close();
    }

    public void deregisterDriver() throws SQLException {
        Driver driver = DriverManager.getDriver(dataSource.getUrl());
        DriverManager.deregisterDriver(driver);
    }


}
