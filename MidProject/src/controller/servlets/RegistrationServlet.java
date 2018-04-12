package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.UserManager;
import customExceptions.RegistrationException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String address = req.getParameter("address");
		
		try {
			if (!UserManager.getManager().register(username, email, address, password, number, 0)) {
				throw new RegistrationException("Wrong data.");
			}
			
			resp.sendRedirect("LoginForm.html");
		}
		catch (RegistrationException e) {
			req.setAttribute("err", e.getMessage());
			req.getRequestDispatcher("ErrorPage.html").forward(req, resp);
		}
		
	}
}
