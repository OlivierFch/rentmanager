package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	public int countAll()
	{
		try {
			return this.vehicleDao.countAllVehicle();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long create(Vehicle vehicle) throws ServiceException {
		
		try {
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public long delete(long id) throws ServiceException {
		
		try {
			return this.vehicleDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public Optional<Vehicle> findById(long id) throws ServiceException {
		
		try {
			return this.vehicleDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	public List<Vehicle> findAll() throws ServiceException {
		
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public long updateVehicle(Vehicle vehicle) throws ServiceException {
		
		try {
			return this.vehicleDao.updateVehicle(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public List<Vehicle> findvehicleByClientId (long id) throws ServiceException {
		
		try {
			return this.vehicleDao.findvehicleByClientId(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
