package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
	
	@Autowired
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	
	public int countAll()
	{
		try {
			return this.clientDao.countAllClient();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long create(Client client) throws ServiceException {
		
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;		
	}
	
	public long delete(long id) throws ServiceException {
		
		try {
			return this.clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	public Optional<Client> findById(long id) throws ServiceException {
		try {
			return this.clientDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	
	public List<Client> findAll() throws ServiceException {
		
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public long updateClient(Client client) throws ServiceException {
		
		try {
			
			return this.clientDao.updateClient(client);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public List<Client> findClientByVehicleId(int vehicleId) throws ServiceException {
		
		try {
			
			return this.clientDao.findClientByVehicleId(vehicleId);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
