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
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/rents")
public class RentListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_RENTS = "/WEB-INF/views/rents/list.jsp";
	
	@Autowired
	ReservationService reservationService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			request.setAttribute("listRents", this.reservationService.findAll());
			this.getServletContext().getRequestDispatcher(VUE_RENTS).forward(request, response);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
