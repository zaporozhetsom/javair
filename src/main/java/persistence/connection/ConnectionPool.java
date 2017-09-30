package persistence.connection;

import exception.ConnectionException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zom on 29.09.2017.
 */
public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static final String DB_DRIVER = "com.dao.jdbc.Driver";
    private static final String DB_URL = "jdbc:dao://localhost:3306/javair";
    private static final String DB_USERNAME = "zatsMe";
    private static final String DB_PASSWORD = "ZRoot";

    private BasicDataSource dataSource;

    private ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
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
            logger.info("Connection created successfully");
            return connection;
        } catch (SQLException e) {
            logger.error("Cannot create connection", e);
            throw new ConnectionException();
        }
    }

}
