package model.dao;

import java.sql.SQLException;

import customExceptions.LoginException;
import model.User;

public interface IUserDao {
	
	User getUserFromLogin(String username, String password) throws SQLException, LoginException;
	User getUserById(int userId) throws SQLException;
	
}
