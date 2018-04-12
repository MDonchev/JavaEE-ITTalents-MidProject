package controller.manager;

import java.sql.SQLException;

import customExceptions.LoginException;
import model.Product;

public interface IUserManager {

	boolean register(String name, String email, String address, String password, String number);
}
