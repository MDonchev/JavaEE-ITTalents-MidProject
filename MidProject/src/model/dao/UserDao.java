package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.User;

public class UserDao {

	List<User> allUsersInDb; //It is as if this is our database for Users
	
	public UserDao() {
		this.allUsersInDb = new ArrayList();
	}
	
	public List<User> getAllUsers() {
		return Collections.unmodifiableList(this.allUsersInDb);
	}

	public void insertUser(User user) {
		if(!this.allUsersInDb.contains(user)) {
			this.allUsersInDb.add(user);
		}
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(User user) {
		this.allUsersInDb.remove(user);
	}
	
}
