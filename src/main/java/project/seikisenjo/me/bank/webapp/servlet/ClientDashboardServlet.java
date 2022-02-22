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

//import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.CLIENT_DASHBOARD_PAGE;
//import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.STAFF_DASHBOARD_PAGE;
import static project.seikisenjo.me.bank.webapp.servlet.ServletPaths.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.seikisenjo.me.bank.webapp.commons.ServiceException;
import project.seikisenjo.me.bank.webapp.model.ClientInfo;
import project.seikisenjo.me.bank.webapp.service.ClientInfoDAO;
import project.seikisenjo.me.bank.webapp.service.ClientInfoDAOImpl;

@WebServlet(CLIENT_DASHBOARD_PAGE)
public class ClientDashboardServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private ClientInfoDAO clientInfoDao = new ClientInfoDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		/*
		String uname = (String) session.getAttribute("ClientloggedIn");
		String userName = (String) session.getAttribute("LoggedName");
	    if (uname == null) {
			session.setAttribute("errorMsg", "Login Failed ");
		    resp.sendRedirect(LOGIN);
		    return;
	    }
	    else if (!uname.isEmpty()) {
		    if (!uname.equals("true")) {
		    	session.setAttribute("errorMsg", "Login Failed ");
		    	resp.sendRedirect(LOGIN);
		    	return;
		    }
		}*/
		try {
			//HttpSession session = req.getSession(false);
			String uname = (String) session.getAttribute("ClientloggedIn");
			String userName = (String) session.getAttribute("LoggedName");
		    if (!uname.isEmpty()) {
			    if (!uname.equals("true")) {
			    	resp.sendRedirect(LOGIN);
			    	session.setAttribute("errorMsg", "Login Failed ");
			    	return;
			    }
			}
			//HttpSession session = req.getSession(false);
			//ClientInfo clientInfo = clientInfoDao.loadAccountInfo(req.getRemoteUser());
			ClientInfo clientInfo = clientInfoDao.loadAccountInfo(userName);
			req.getSession().setAttribute("clientInfo", clientInfo);
		} catch (ServiceException e) {
			sendError(req, e.getMessage());
		} catch (NullPointerException e) {
			resp.sendRedirect(LOGIN);
			return;
		}
		forward(req, resp);
	}
}
	
