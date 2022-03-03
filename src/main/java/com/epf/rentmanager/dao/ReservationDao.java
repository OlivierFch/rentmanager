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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {
		
	}
	
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);
			
			pstmt.setInt(1, reservation.getIdVehicule());
			pstmt.setInt(2, reservation.getIdClient());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getDateEnd()));
			
			pstmt.execute();
			
			conn.close();
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public long delete(int id) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			conn.close();
			
			return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	public Optional<Reservation> findResaByClientId(int clientId) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			
			pstmt.setInt(1, clientId);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int reservationId = rs.getInt("id");
			int vehicleId = rs.getInt("vehicleId");
			
			Reservation reservation = new Reservation(reservationId, vehicleId, rs.getDate("dateStart").toLocalDate(), rs.getDate("dateEnd").toLocalDate());
			
			conn.close();
			
			return Optional.of(reservation);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public Optional<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			
			pstmt.setInt(1, vehicleId);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int reservationId = rs.getInt("id");
			int clientId = rs.getInt("clientId");
			
			Reservation reservation = new Reservation(reservationId, clientId, rs.getDate("dateStart").toLocalDate(), rs.getDate("dateEnd").toLocalDate());
			
			conn.close();
			
			return Optional.of(reservation);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; 
	}

	public List<Reservation> findAll() throws DaoException {
		
		List<Reservation> reservations = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int reservationId = rs.getInt("id");
				
				Reservation reservation = new Reservation(reservationId, rs.getInt("vehiculeId"), rs.getInt("clientId"), rs.getDate("dateStart").toLocalDate(), rs.getDate("dateEnd").toLocalDate());
				
				reservations.add(reservation);
			}
			
			conn.close();
			
			return reservations;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
