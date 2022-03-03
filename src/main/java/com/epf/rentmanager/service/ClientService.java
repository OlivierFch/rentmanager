package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
	
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
	
	public long delete(int id) throws ServiceException {
		
		try {
			return this.clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
