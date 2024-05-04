package codes.dao;

import codes.model.Reservation;
import codes.model.Voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao{
    private Connection connection;

    public ReservationDaoImpl(Connection connection){this.connection = connection;}

    @Override
    public void addReservation(Reservation reservation) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservation (dateDebut, dateFin, remise, idUser, idVoiture) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setDate(1, reservation.getDate_debut());
            preparedStatement.setDate(2, reservation.getDate_fin());
            preparedStatement.setFloat(3, reservation.getRemise());
            preparedStatement.setInt(4, reservation.getIdUser());
            preparedStatement.setInt(5, reservation.getIdVoiture());
            preparedStatement.execute();
        }
    }

    @Override
    public Reservation getReservation(int id) throws SQLException {
        Reservation reservation = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE id=?")){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    reservation = new Reservation();
                    reservation.setId_reservation(resultSet.getInt("id"));
                    reservation.setDate_debut(resultSet.getDate("dateDebut"));
                    reservation.setDate_fin(resultSet.getDate("dateFin"));
                    reservation.setRemise(resultSet.getFloat("remise"));
                    reservation.setIdUser(resultSet.getInt("idUser"));
                    reservation.setIdVoiture((resultSet.getInt("idVoiture")));
                }
            }
        }
        return reservation;
    }
}