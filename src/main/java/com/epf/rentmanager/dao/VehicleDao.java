package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {
	
	private VehicleDao() {
		
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(*) FROM Vehicle";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id = ?;";
	private static final String FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY = "SELECT * FROM Reservation INNER JOIN Vehicle ON Reservation.vehicle_id = Vehicle.id WHERE client_id = ?;";
	
public int countAllVehicle() throws DaoException {
		
		int count = 0;
		
		Connection conn;
		try {
			
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				count = rs.getInt(1);
			}
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	public long create(Vehicle vehicle) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setInt(2, vehicle.getNbPlaces());
			
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
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setLong(1, id);
			long status = pstmt.executeUpdate();
			
			conn.close();
			
			return status;
			
		} catch (SQLException e) {
			throw new DaoException();
		}		
	}

	
	public Optional<Vehicle> findById(long id) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			
			pstmt.setLong(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			Vehicle vehicle = new Vehicle(id, rs.getString("constructeur"), rs.getByte("nb_places"));
			
			conn.close();
			
			return Optional.of(vehicle);
			
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	
	public List<Vehicle> findAll() throws DaoException {
		
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				long vehicleId = rs.getLong("id");
				String carContructor = rs.getString("constructeur");
				int carSeats = rs.getByte("nb_places");
				
				Vehicle vehicle = new Vehicle(vehicleId, carContructor, carSeats);
				
				vehicles.add(vehicle);
			}
			
			conn.close();
			return vehicles;
			
		} catch (SQLException e) {
			throw new DaoException();
		}		
	}
	
	
	public long updateVehicle(Vehicle vehicle) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);) {
			
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setInt(2, vehicle.getNbPlaces());
			pstmt.setLong(3, vehicle.getId());
			
			long status = pstmt.executeUpdate();
			conn.close();
			
			return status;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	
	public List<Vehicle> findvehicleByClientId(long clientId) throws DaoException {
		
		List<Vehicle> carList = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY);
			pstmt.setLong(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			
			 while (rs.next()) {
				 
				 Vehicle vehicle = new Vehicle(rs.getInt("id"), rs.getString("constructeur"), rs.getByte("nb_places"));
				 carList.add(vehicle);
			 }
			 
			 conn.close();
			 return carList;
			
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
