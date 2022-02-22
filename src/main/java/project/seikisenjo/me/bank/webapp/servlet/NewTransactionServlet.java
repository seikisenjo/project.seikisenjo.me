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

import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.LOGIN;
import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.NEW_TRANSACTION;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.seikisenjo.me.bank.webapp.commons.ServiceException;
import project.seikisenjo.me.bank.webapp.model.ClientInfo;
import project.seikisenjo.me.bank.webapp.model.ClientTransaction;
import project.seikisenjo.me.bank.webapp.model.User;
import project.seikisenjo.me.bank.webapp.service.ClientTransactionDAO;
import project.seikisenjo.me.bank.webapp.service.ClientTransactionDAOImpl;

@WebServlet(NEW_TRANSACTION)
public class NewTransactionServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	//private CheckSession SessionCheck = new CheckSession();
	private ClientTransactionDAO clientTransactionDAO = new ClientTransactionDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		HttpSession session = req.getSession(false);
		//SessionCheck.checkSession("ClientloggedIn", req, resp, session);
		//HttpSession session = req.getSession(false);
		String uname = (String) session.getAttribute("ClientloggedIn");
		//String userName = (String) session.getAttribute("LoggedName");
		/*
	    if (uname == null) {
			session.setAttribute("errorMsg", "Login Failed ");
		    resp.sendRedirect(LOGIN);
		    return;
	    }*/
	    if (!uname.isEmpty()) {
		    if (!uname.equals("true")) {
		    	session.setAttribute("errorMsg", "Login Failed ");
		    	resp.sendRedirect(LOGIN);
		    	return;
		    }
		}
		forward(req, resp);
		} catch (NullPointerException e){
			resp.sendRedirect(LOGIN);
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			ClientTransaction clientTransaction = new ClientTransaction();
			User user = new User(getUserId(req));
			clientTransaction.setUser(user);
			clientTransaction.setAmount(new BigDecimal(req.getParameter("amount")));
			//clientTransaction.setAmount(Integer.parseInt(req.getParameter("amount")));
			clientTransaction.setTransCode(req.getParameter("transcode"));
			clientTransaction.setToAccountNum(req.getParameter("toAccountNum"));
			clientTransactionDAO.create(clientTransaction);
			redirect(resp, ServletPaths.CLIENT_DASHBOARD_PAGE);
		} catch (ServiceException e) {
			sendError(req, e.getMessage());
			forward(req, resp);
		}
	}
}
