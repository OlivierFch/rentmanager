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

@WebServlet("/cars/details")
public class VehicleDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String VUE_DETAILS_VEHICLES = "/WEB-INF/views/vehicles/details.jsp";
	
	@Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
        	int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("res", reservationService.findReservationByVehiculeId(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("vehicule", vehicleService.findById(id).get());
            request.setAttribute("listUsers", clientService.findAll());
            request.setAttribute("users", clientService.findClientByVehicleId(id));
            
        } catch (NumberFormatException | ServiceException e) {
            e.printStackTrace();
        }

        request.getServletContext().getRequestDispatcher(VUE_DETAILS_VEHICLES).forward(request, response);
    }
	
}
