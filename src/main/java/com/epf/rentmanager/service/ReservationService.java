package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

@Service
public class ReservationService {
	
	private ReservationDao reservationDao;
	
	@Autowired
	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	
	public long create(Reservation reservation) throws ServiceException {
		
		try {
			return this.reservationDao.create(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public long delete(Reservation reservation) throws ServiceException {
		
		try {
			return this.reservationDao.delete(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Reservation> findReservationByClientId(long id) throws ServiceException {
		
		try {
			return this.reservationDao.findResaByClientId(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Reservation> findReservationByVehiculeId(int id) throws ServiceException {
		
		try {
			return this.reservationDao.findResaByVehicleId(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Reservation> findAll() throws ServiceException {
		
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public int countAll() throws ServiceException {
		
		try {
			
			return this.reservationDao.countAllReservation();
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
