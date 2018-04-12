package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.manager.UserManager;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				
				User user = UserManager.getManager().login(username, password); 
				if(user != null) {
				
					HttpSession session = req.getSession();
					
					session.setAttribute("user", user);
					
					req.getRequestDispatcher("UserPage.jsp").forward(req, resp);
				}
				else {
					req.setAttribute("error","Invalid Username or Password");
					resp.sendRedirect("InvalidLogin.html");
				}
	}
	
}
