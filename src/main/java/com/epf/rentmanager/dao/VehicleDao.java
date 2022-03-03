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
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
			
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNbPlaces());
			
			pstmt.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public long delete(int id) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			conn.close();
			
			return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			Vehicle vehicle = new Vehicle(id, rs.getString("constructor"), rs.getInt("nbPlaces"));
			
			conn.close();
			
			return Optional.of(vehicle);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {
		
		List<Vehicle> vehicles = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int vehicleId = rs.getInt("id");
				Vehicle vehicle = new Vehicle(vehicleId, rs.getString("constructeur"), rs.getInt("nb_places"));
				
				vehicles.add(vehicle);
			}
			
			conn.close();
			
			return vehicles;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
