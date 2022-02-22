package project.seikisenjo.me.bank.webapp.servlet;

import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import project.seikisenjo.me.bank.webapp.commons.ServiceException;

@WebServlet(LOGOUT)
public class LogoutServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.logout();
		HttpSession session=req.getSession(false);
		session.removeAttribute("authenticatedUser");
		session.removeAttribute("UserloggedIn");
		session.removeAttribute("ClientloggedIn");
		session.invalidate();
		redirect(resp, WELCOME);
		//forward(req, resp);
	}

}
