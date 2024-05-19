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

        PreparedStatement preparedStatement;

        try {

            String query = "INSERT INTO reservation (dateDebut, dateFin, remise, idUser, idVoiture) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, reservation.getDate_debut());
            preparedStatement.setDate(2, reservation.getDate_fin());
            preparedStatement.setFloat(3, reservation.getRemise());
            preparedStatement.setInt(4, reservation.getIdUser()+1);
            preparedStatement.setInt(5, reservation.getIdVoiture());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getReservation(int id) throws SQLException {
        Reservation reservation = null;

        PreparedStatement statement;

        try{

            String query = "SELECT * FROM reservation WHERE id=?";

            statement = connection.prepareStatement(query);

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
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public int getLastReservationId() throws SQLException {
        int lastReservationId = -1;
        PreparedStatement preparedStatement;
        try {
            String query = "SELECT id FROM reservation ORDER BY id DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lastReservationId = resultSet.getInt("id");
                } else {
                    throw new SQLException("No reservations found in the database.");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return lastReservationId;
    }



    @Override
    public List<Reservation> searchReservationForOneUser(int userId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE idUser = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation(
                            resultSet.getInt("id"),
                            resultSet.getDate("dateDebut"),
                            resultSet.getDate("dateFin"),
                            resultSet.getFloat("remise"),
                            resultSet.getInt("idUser"),
                            resultSet.getInt("idVoiture")
                    );
                    reservations.add(reservation);
                }
            }
        }

        return reservations;
    }
}