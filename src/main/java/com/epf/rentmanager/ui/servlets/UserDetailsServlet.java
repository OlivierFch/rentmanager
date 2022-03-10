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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DETAILS_USERS = "/WEB-INF/views/users/details.jsp";
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private ReservationService reservationService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("user", clientService.findById(id).get());
			request.setAttribute("reservations", reservationService.findReservationByClientId(id));
            request.setAttribute("vehicules", vehicleService.findvehicleByClientId(id));
            request.setAttribute("cars", vehicleService.findAll());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
			this.getServletContext().getRequestDispatcher(VUE_DETAILS_USERS).forward(request, response);
	}
	
}
