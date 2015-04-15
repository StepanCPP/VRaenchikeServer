package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserBanned;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface UserBannedDAO {
    public void addUserBanned(UserBanned user_banned) throws SQLException;
    public void updateUserBanned(Long user_banned_id, UserBanned user_banned) throws SQLException;
    public UserBanned getUserBannedById(Long user_banned_id) throws SQLException;
    public Collection getAllUserBanneds() throws SQLException;
    public void deleteUserBanned(UserBanned user_banned) throws SQLException;
}
