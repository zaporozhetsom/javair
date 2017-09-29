package persistence.mysql;

import persistence.DAOFactory;
import persistence.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zom on 20.09.2017.
 */
public class MySqlDAOFactory implements DAOFactory {

    private String user = "zatsMe";
    private String password = "ZRoot";
    private String url = "jdbc:mysql://localhost:3306/javair";
    private String driver = "com.mysql.jdbc.Driver";


    public MySqlDAOFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new MySqlUserDAO(connection);

    }
}
