package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String VUE_CREATE_RENTS = "/WEB-INF/views/rents/create.jsp";

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ClientService clientService;
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

			List<Vehicle> vehicles = vehicleService.findAll();
			List<Client> clients = clientService.findAll();
			request.setAttribute("vehicules", vehicles);
			request.setAttribute("clients", clients);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher(VUE_CREATE_RENTS).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idClient = Integer.parseInt(request.getParameter("client"));
		int idVehicle = Integer.parseInt(request.getParameter("vehicle"));
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));
		LocalDate end = LocalDate.parse(request.getParameter("fin"));
		
		Reservation reservation = new Reservation(idClient, idVehicle, debut, end);
		
		try {
			
			reservationService.create(reservation);
			response.sendRedirect("/rentmanager/rents");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher(VUE_CREATE_RENTS).forward(request, response);
		}
		
		
	}

}
