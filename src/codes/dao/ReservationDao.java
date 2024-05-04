package codes.dao;

import codes.model.Reservation;

import java.sql.SQLException;

public interface ReservationDao {
    void addReservation(Reservation reservation) throws SQLException;

    Reservation getReservation(int id) throws SQLException;
}
