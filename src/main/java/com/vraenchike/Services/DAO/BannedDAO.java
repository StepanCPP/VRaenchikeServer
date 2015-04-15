package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Banned;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface BannedDAO {
    public void addBanned(Banned banned) throws SQLException;
    public void updateBanned(Long banned_id, Banned banned) throws SQLException;
    public Banned getBannedById(Long banned_id) throws SQLException;
    public Collection getAllBanneds() throws SQLException;
    public void deleteBanned(Banned banned) throws SQLException;
}
