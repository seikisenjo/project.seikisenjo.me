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

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.seikisenjo.me.bank.webapp.commons.ServiceException;
import project.seikisenjo.me.bank.webapp.model.ClientInfo;
import project.seikisenjo.me.bank.webapp.model.Role;
import project.seikisenjo.me.bank.webapp.model.User;
import project.seikisenjo.me.bank.webapp.model.UserRole;
import project.seikisenjo.me.bank.webapp.service.ClientInfoDAO;
import project.seikisenjo.me.bank.webapp.service.ClientInfoDAOImpl;
//import project.seikisenjo.me.bank.webapp.service.EmailService;
//import project.seikisenjo.me.bank.webapp.service.EmailServiceImp;
import project.seikisenjo.me.bank.webapp.service.UserDAO;
import project.seikisenjo.me.bank.webapp.service.UserDAOImpl;
import project.seikisenjo.me.bank.webapp.service.UserRoleDAO;
import project.seikisenjo.me.bank.webapp.service.UserRoleDAOImpl;

/**
 * @author SUTD
 */
@WebServlet("/register")
public class RegisterServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private ClientInfoDAO clientAccountDAO = new ClientInfoDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
	//private EmailService emailService = new EmailServiceImp();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		User user = new User();
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		ClientInfo clientAccount = new ClientInfo();
		clientAccount.setFullName(request.getParameter("fullName"));
		clientAccount.setFin(request.getParameter("fin"));
		clientAccount.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
		clientAccount.setOccupation(request.getParameter("occupation"));
		clientAccount.setMobileNumber(request.getParameter("mobileNumber"));
		clientAccount.setAddress(request.getParameter("address"));
		clientAccount.setEmail(request.getParameter("email"));
		clientAccount.setUser(user);
		
		try {
			userDAO.create(user);
			clientAccountDAO.create(clientAccount);
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(Role.client);
			userRoleDAO.create(userRole );
			//emailService.sendMail(clientAccount.getEmail(), "Registration", "Thank you for the registration!");
			sendMsg(request, "Registration success");
			redirect(response, ServletPaths.LOGIN);
		} catch (ServiceException e) {
			sendError(request, e.getMessage());
			forward(request, response);
		}
	}
}
