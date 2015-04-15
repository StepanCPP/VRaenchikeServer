package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserLoginInfo;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface UserLoginInfoDAO {
    public void addUserLoginInfo(UserLoginInfo user_login_info_login_info) throws SQLException;
    public void updateUserLoginInfo(Long user_login_info_id, UserLoginInfo user_login_info) throws SQLException;
    public UserLoginInfo getUserLoginInfoById(Long user_login_info_id) throws SQLException;
    public Collection getAllUserLoginInfos() throws SQLException;
    public void deleteUserLoginInfo(UserLoginInfo user_login_info) throws SQLException;
}
