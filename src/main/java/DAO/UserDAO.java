package DAO;

import BusinessLogic.Entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zom on 20.09.2017.
 */
public interface UserDAO {

    public User create();

    public User read(Integer key) throws SQLException;

    public void update(User user);

    public void delete(User user);

    public List<User> getAll() throws SQLException;
}
