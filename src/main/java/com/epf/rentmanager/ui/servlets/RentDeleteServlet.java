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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/rents/delete")
public class RentDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Autowired
    private ReservationService reservationService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        try {
        	
            Reservation reservation = new Reservation(Integer.parseInt(request.getParameter("id")));
            reservationService.delete(reservation);
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/rents"); 
    }
	
}
