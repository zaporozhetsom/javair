package DAO.mysql;

import BusinessLogic.Entities.User;
import DAO.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zom on 20.09.2017.
 */
public class MySqlUserDAO implements UserDAO {

    private final Connection connection;

    public MySqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create() {
        return null;
    }

    @Override
    public User read(Integer key) throws SQLException {
        String sql = "SELECT * FROM javair.user WHERE user_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,key);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setRole(resultSet.getString("user_role"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }
}
