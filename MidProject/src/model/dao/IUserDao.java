package model.dao;

import java.sql.SQLException;

import customExceptions.LoginException;
import model.BoxOfCharcoalCubes;
import model.Hookah.HookahSize;
import model.Product;
import model.Tobacco.TobaccoFlavor;
import model.User;
import model.BoxOfCharcoalCubes.NumberOfPieces;

public interface IUserDao {
	
	User getUserFromLogin(String username, String password) throws SQLException, LoginException;
	User getUserById(int userId) throws SQLException;
	void insertProductByUserId(int userId, Product product, HookahSize size, TobaccoFlavor flavor, NumberOfPieces number) throws SQLException;
}
