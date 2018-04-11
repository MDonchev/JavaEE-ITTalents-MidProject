package controller.manager;

import java.sql.SQLException;

import customExceptions.LoginException;
import model.Product;
import model.User;
import model.dao.UserDao;

public class UserManager implements IUserManager{
	
	
	private volatile static UserManager instance;
	private UserDao userDao = UserDao.getInstance();

	private UserManager() {}
	
	public synchronized static UserManager getManager() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	
	public User login(String username, String password) {
		try {
			return this.userDao.getUserFromLogin(username, password);
		}
		catch (SQLException | LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addProductToCatalog(Product product, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewProfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tryLoginUser(String email, String password) throws LoginException {
		// TODO Auto-generated method stub
		
	}
}
