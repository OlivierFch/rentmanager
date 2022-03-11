package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

	private ClientDao() {
	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(*) FROM Client";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id = ?;";
	private static final String FIND_RESERVATIONS_CLIENT_BY_VEHICLE_QUERY = "SELECT * FROM Reservation INNER JOIN Client ON Reservation.client_id = Client.id WHERE vehicle_id=?;";
	
	
	public int countAllClient() throws DaoException {
		
		int count = 0;
		
		Connection conn;
		try {
			
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				count = rs.getInt(1);
			}
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new DaoException();
		}
		
		
		return count;
	}
	
	public long create(Client client) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));
			
			long status = pstmt.executeUpdate();
			
			conn.close();
			return status;
			
		} catch (SQLException e) {
			throw new DaoException();
		}
		
	}

	public long delete(long id) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setLong(1, id);
			
			long key = pstmt.executeUpdate();
			conn.close();
			
			return key;
			
		} catch (SQLException e) {
			throw new DaoException();
		}
		
	}

	public Optional<Client> findById(long id) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			
			pstmt.setLong(1, id);
		
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
			Client client = new Client(id, clientLastname, clientFirstname, clientEmail, clientBirthdate);
			
			conn.close();
			
			return Optional.of(client);
			
			
		} catch (SQLException e) {
			throw new DaoException();
		}
		
	}

	
	public List<Client> findAll() throws DaoException {
		 
		List<Client> clients = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				long clientId = rs.getLong("id");
				String clientLastname = rs.getString("nom");
				String clientFirstname = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
				
				Client client = new Client(clientId, clientLastname, clientFirstname, clientEmail, clientBirthdate);
				
				clients.add(client);
			}
			
			conn.close();
			return clients;
			
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public long updateClient(Client client) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);) {
			
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));
			pstmt.setLong(5, client.getId());
			
			long status = pstmt.executeUpdate();
			
			conn.close();
			
			return status;
		
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	
	public List<Client> findClientByVehicleId(int vehicleId) throws DaoException {
		
		List<Client> clientList = new ArrayList<Client>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_CLIENT_BY_VEHICLE_QUERY);
			pstmt.setLong(1, vehicleId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Client client = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getDate("naissance").toLocalDate());

				clientList.add(client);
			}
			
			conn.close();
			return clientList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
