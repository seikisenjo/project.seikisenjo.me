/*
 * Copyright 2017 SUTD Licensed under the
	Educational Community License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may
	obtain a copy of the License at

https://opensource.org/licenses/ECL-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on an "AS IS"
	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
	or implied. See the License for the specific language governing
	permissions and limitations under the License.
 */

package project.seikisenjo.me.bank.webapp.servlet;

import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.seikisenjo.me.bank.webapp.commons.ServiceException;
//import project.seikisenjo.me.bank.webapp.model.ClientAccount;
//import project.seikisenjo.me.bank.webapp.model.ClientInfo;
import project.seikisenjo.me.bank.webapp.model.User;
import project.seikisenjo.me.bank.webapp.model.UserStatus;
import project.seikisenjo.me.bank.webapp.service.UserDAO;
import project.seikisenjo.me.bank.webapp.service.UserDAOImpl;
//import project.seikisenjo.me.bank.webapp.service.ClientAccountDAO;
//import project.seikisenjo.me.bank.webapp.service.ClientAccountDAOImpl;
//import project.seikisenjo.me.bank.webapp.service.ClientInfoDAO;
//import project.seikisenjo.me.bank.webapp.service.ClientInfoDAOImpl;

@WebServlet(LOGIN)
public class LoginServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAOImpl();
	//private ClientAccountDAO clientDAO = new ClientAccountDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String userName = req.getParameter("username");
			String Password = req.getParameter("password");
			//if (req.getParameter("radio").equals("staff"))	{
			User user = userDAO.loadUser(userName, Password);
			//ClientAccount client = clientDAO.loadClient(userName, Password);
			String id = req.getParameter("identity");
			//setUserId(req, user.getId());
			//System.out.println(id);
			if (id.equals("staff") && user != null && (user.getStatus() == UserStatus.APPROVED)) {
				HttpSession session = req.getSession();
				//req.login(userName, Password);
				session.setAttribute("UserloggedIn", "true");
				session.setAttribute("authenticatedUser", req.getRemoteUser());
				session.setAttribute("LoggedName", userName);
				setUserId(req, user.getId());
				redirect(resp, STAFF_DASHBOARD_PAGE);
				/*
				if (req.isUserInRole("client")) {
					redirect(resp, CLIENT_DASHBOARD_PAGE);
				} 
				else if (req.isUserInRole("staff")) {
					redirect(resp, STAFF_DASHBOARD_PAGE);
				}
				*/
				return;
			}
			else if (id.equals("client") && user != null) {
				HttpSession session = req.getSession();
				//req.login(userName, Password);
				session.setAttribute("ClientloggedIn", "true");
				session.setAttribute("authenticatedUser", req.getRemoteUser());
				session.setAttribute("LoggedName", userName);
				setUserId(req, user.getId());
				redirect(resp, CLIENT_DASHBOARD_PAGE);
				return;
			}
			//}
			//String nextJSP = "login.jsp";
			//RequestDispatcher dispatcher = req.getRequestDispatcher(nextJSP);;
			//dispatcher.forward(req,resp);
			//System.out.println(userName);
			else {
				sendError(req, "Invalid username/password!");
			}
		} catch(ServiceException ex) {
			sendError(req, ex.getMessage());
		}
		forward(req, resp);
	}

}
