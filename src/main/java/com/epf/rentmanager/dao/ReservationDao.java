package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
				int vehicleId = rs.getInt("vehicle_id");

				Reservation reservation = new Reservation(reservationId, (int) clientId, vehicleId,
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());

				resList.add(reservation);
			}

			conn.close();
			return resList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {

		List<Reservation> resList = new ArrayList<>();

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			pstmt.setLong(1, vehicleId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int reservationId = rs.getInt("id");
				int clientId = rs.getInt("client_id");

				Reservation reservation = new Reservation(reservationId, clientId, (int) vehicleId,
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());

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

				Reservation reservation = new Reservation(reservationId, rs.getInt("client_id"), rs.getInt("vehicle_id"),
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());

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
