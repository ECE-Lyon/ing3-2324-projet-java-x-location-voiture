package codes.dao;

import codes.model.Voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VoitureDaoImpl implements VoitureDao {
    private Connection connection;

    public VoitureDaoImpl(Connection connection){this.connection = connection;}

    @Override
    public void addVoiture(Voiture voiture) throws SQLException {

        PreparedStatement preparedStatement;

        try {

            String query = "INSERT INTO voiture (prixParJour, id_modele) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setFloat(1, voiture.getPrix_par_jour());
            preparedStatement.setInt(2, voiture.getId_modele());
            preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Voiture getVoiture(int id_voiture) throws SQLException {
        Voiture voiture = null;

        PreparedStatement statement;

        try {

            String query = "SELECT * FROM voiture WHERE id=?";
            statement = connection.prepareStatement(query);

            statement.setInt(1, id_voiture);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    voiture = new Voiture();
                    voiture.setId_voiture(resultSet.getInt("id"));
                    voiture.setPrix_par_jour(resultSet.getFloat("prixParJour"));
                    voiture.setId_modele(resultSet.getInt("id_modele"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return voiture;
    }

    @Override
    public int getIdVoiture(Voiture voiture) throws SQLException {

        int idVoiture = -1;
        PreparedStatement preparedStatement;

        try {

            String query = "SELECT id FROM voiture WHERE statut = 0 AND id_modele = ? ORDER BY id LIMIT 1";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, voiture.getId_modele());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idVoiture = resultSet.getInt("id");
                } else {
                    throw new SQLException("No car found with statut = 0 and id_modele = " + voiture.getId_modele());
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return idVoiture;
    }

    @Override
    public void modifVoiture(Voiture voiture) throws SQLException{

        PreparedStatement preparedStatement;

        try {

            String query = "UPDATE voiture SET statut = 1 WHERE id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, voiture.getId_voiture());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrixVoiture (int idVoiture, float nouveauPrix) throws SQLException {

        PreparedStatement statement;

        try {

            String query = "UPDATE voiture SET prixParJour = ? WHERE id = ?";

            statement = connection.prepareStatement(query);
            statement.setFloat(1, nouveauPrix);
            statement.setInt(2, idVoiture);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
