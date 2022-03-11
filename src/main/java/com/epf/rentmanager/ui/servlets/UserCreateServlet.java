package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.fmt.ParseDateSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VUE_CREATE_USERS ="/WEB-INF/views/users/create.jsp";
	
	@Autowired
	ClientService clientService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			request.setAttribute("users", this.clientService.findAll());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher(VUE_CREATE_USERS).forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
		
		Client client = new Client(firstname, lastname, email, birthdate);
		
		try {
			
			clientService.create(client);
			response.sendRedirect("/rentmanager/users");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher(VUE_CREATE_USERS).forward(request, response);
		}
		
	}
	
}
