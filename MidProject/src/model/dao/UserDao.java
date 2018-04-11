package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.manager.DBManager;
import customExceptions.LoginException;
import model.User;

public final class UserDao implements IUserDao{
	
	private static UserDao instance;
	private Connection connection = DBManager.getInstance().getConnection();
	
	
	public static synchronized UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}


	@Override
	public User getUserFromLogin(String username, String password) throws SQLException, LoginException{
		//TODO
		
		
		return null;
	}
}
