package codes.dao;

import codes.model.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDao {
    void addReservation(Reservation reservation) throws SQLException;

    Reservation getReservation(int id) throws SQLException;

    List<Reservation> searchReservationForOneUser(int userId) throws SQLException;
}
