package com.vraenchike.Services.DAO;

import com.vraenchike.Model.User;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(Long user_id, User user) throws SQLException;
    public User getUserById(Long user_id) throws SQLException;
    public Collection getAllUsers() throws SQLException;
    public void deleteUser(User user) throws SQLException;
}
