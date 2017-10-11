package persistence.dao.impl;

import domain.entities.User;
import domain.util.UserRole;
import exception.PersistenceException;
import persistence.dao.interfaces.UserDAO;
import persistence.dao.util.SQLQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zom.
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    public UserDAOImpl() {
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistenceException {
        List<User> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                User user = new User.Builder()
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .role(UserRole.valueOf(resultSet.getString("role_name")))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .id(resultSet.getLong("id"))
                        .approved(resultSet.getInt("approved") == 1)
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            log.error("Retrieving User data from DB error ");
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public User getUserByLoginAndPassword(String login, String password) throws PersistenceException {
        if(!isUserExists(login)){
            log.error("There is no user \'" + login +"\' in DB");
            throw new PersistenceException("There is no such user");
        }
        User neededUser = null;
        List<User> users = getAll(SQLQueries.TABLE_NAME_USER);
        for (User user :
                users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                neededUser = user;
                break;
            }
        }
        if(neededUser == null){
            log.error("Login and password do not match. Login = [" + login + "], password = [" + password + "]");
            throw new PersistenceException("Login and password do not match ");
        }
        return neededUser;
    }

    @Override
    public boolean isUserExists(String login) throws PersistenceException {
        List<User> users = getAll(SQLQueries.TABLE_NAME_USER);
        boolean exists = false;
        for (User user :
                users) {
            if (login.equals(user.getLogin())) {
                exists = true;
                break;
            }

        }
        return exists;
    }

    @Override
    public void setAdmin(Integer id) throws PersistenceException {

    }

    @Override
    protected void prepareStatementINSERT(PreparedStatement statement, User object) throws PersistenceException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getRole().name());
            log.debug(statement);
        } catch (SQLException e) {
            log.error("Prepare statement for insert exception.", e);
            throw new PersistenceException("Insertion exception");
        }
    }


//    public User read(Integer key) throws SQLException {
//        String sql = "SELECT * FROM javair.user WHERE user_id = ?;";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setInt(1,key);
//
//        ResultSet resultSet = statement.executeQuery();
//        resultSet.next();
//
//        User user = new User.Builder() //todo clean this up!!!
//                .id(1)
//                .firstName("John")
//                .lastName("Peterson")
//                .login("Jjjjj")
//                .password("QWERTY")
//                .role(UserRole.ADMIN)
//                .build();
//        user.setId(resultSet.getInt("user_id"));
//        user.setFirstName(resultSet.getString("first_name"));
//        user.setLastName(resultSet.getString("last_name"));
//        user.setRole(resultSet.getString("user_role"));
//        user.setLogin(resultSet.getString("login"));
//        user.setPassword(resultSet.getString("password"));
//        return user;
//    }
}
