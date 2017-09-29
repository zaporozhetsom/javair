package persistence.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Created by zom on 29.09.2017.
 */
public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private BasicDataSource dataSource;

    private ConnectionPool() {
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



}
