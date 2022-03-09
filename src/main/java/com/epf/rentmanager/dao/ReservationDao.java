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

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(idClient, idVehicle, debut, end) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, idVehicle, debut, end FROM Reservation WHERE idClient=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, idClient, debut, end FROM Reservation WHERE idVehicle=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, idClient, idVehicle, debut, end FROM Reservation;";
	private static final String COUNT_RESERVATION_QUERY = "SELECT COUNT(id) AS count FROM Reservation";

	public long create(Reservation reservation) throws DaoException {

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicle());
			pstmt.setDate(3, Date.valueOf(reservation.getDebut()));
			pstmt.setDate(4, Date.valueOf(reservation.getEnd()));

			long status = pstmt.executeUpdate();

			conn.close();

			return status;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long delete(Reservation reservation) throws DaoException {

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, reservation.getId());
			long status = pstmt.executeUpdate();

			conn.close();

			return status;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {

		List<Reservation> resList = new ArrayList<>();

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setLong(1, clientId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int reservationId = rs.getInt("id");
				int vehicleId = rs.getInt("idVehicle");

				Reservation reservation = new Reservation(reservationId, (int) clientId, vehicleId,
						rs.getDate("debut").toLocalDate(), rs.getDate("end").toLocalDate());

				resList.add(reservation);
			}

			conn.close();
			return resList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByVehicleId(long clientId) throws DaoException {

		List<Reservation> resList = new ArrayList<>();

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			pstmt.setLong(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int reservationId = rs.getInt("id");
				int vehicleId = rs.getInt("idVehicle");

				Reservation reservation = new Reservation(reservationId, (int) clientId, vehicleId,
						rs.getDate("debut").toLocalDate(), rs.getDate("end").toLocalDate());

				resList.add(reservation);
			}

			conn.close();
			return resList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {

		List<Reservation> reservations = new ArrayList<>();

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int reservationId = rs.getInt("id");

				Reservation reservation = new Reservation(reservationId, rs.getInt("idClient"), rs.getInt("idVehicle"),
						rs.getDate("debut").toLocalDate(), rs.getDate("end").toLocalDate());

				reservations.add(reservation);
			}

			conn.close();

			return reservations;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int countAllReservation() throws DaoException {

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATION_QUERY);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int count = rs.getInt("count");

			conn.close();
			return count;

		} catch (SQLException e) {
			throw new DaoException();
		}

	}

}
