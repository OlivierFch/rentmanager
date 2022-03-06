package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int id;

	private static final String VUE_USERS = "/WEB-INF/views/users/list.jsp";
	private static final String VUE_EDIT_USERS = "/WEB-INF/views/users/edit.jsp";

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_EDIT_USERS).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		id = Integer.valueOf(request.getQueryString().substring(3));

		try {

			Client existingUser = clientService.findById(id);
			clientService.updateClient(existingUser);
			response.sendRedirect("/rentmanager/users");

		} catch (ServiceException e) {
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher(VUE_USERS).forward(request, response);
		}
	}
}
