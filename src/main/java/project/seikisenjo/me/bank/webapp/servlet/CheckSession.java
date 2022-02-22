package project.seikisenjo.me.bank.webapp.servlet;

import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.LOGIN;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckSession extends DefaultServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void checkSession(String identity, HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		
		try {
			String uname = (String) session.getAttribute(identity);
			if (!uname.isEmpty()) {
				if (!uname.equals("true")) {
					session.setAttribute("errorMsg", "Login Failed ");
					resp.sendRedirect(LOGIN);
					//return uname;
				}
			}
		}
		catch (NullPointerException e) {
			resp.sendRedirect(LOGIN);
			//return(req);
		}
	}
}
