package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars_create")
public class VehicleCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_CREATE_CARS ="/WEB-INF/views/vehicles/create.jsp";
	
	@Autowired
	VehicleService vehicleService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	this.getServletContext().getRequestDispatcher(VUE_CREATE_CARS).forward(request, response);
	
	}
}
