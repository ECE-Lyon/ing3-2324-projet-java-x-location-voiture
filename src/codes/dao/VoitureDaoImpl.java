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
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO voiture (prixParJour, id_modele) VALUES (?, ?)")) {
            preparedStatement.setFloat(1, voiture.getPrix_par_jour());
            preparedStatement.setInt(2, voiture.getId_modele());
            preparedStatement.execute();
        }
    }

    @Override
    public Voiture getVoiture(int id_voiture) throws SQLException {
        Voiture voiture = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM voiture WHERE id=?")){
            statement.setInt(1, id_voiture);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    voiture = new Voiture();
                    voiture.setId_voiture(resultSet.getInt("id"));
                    voiture.setPrix_par_jour(resultSet.getFloat("prixParJour"));
                    voiture.setId_modele(resultSet.getInt("id_modele"));
                }
            }
        }
        return voiture;
    }

    @Override
    public int getIdVoiture(Voiture voiture) throws SQLException {
        String query = "SELECT id FROM voiture WHERE statut = 0 AND id_modele = ? ORDER BY id LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, voiture.getId_modele());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    throw new SQLException("No car found with statut = 0 and id_modele = " + voiture.getId_modele());
                }
            }
        }
    }

    @Override
    public void modifVoiture(Voiture voiture) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE voiture SET statut = 1 WHERE id = ?")){
            preparedStatement.setInt(1, voiture.getId_voiture());
        }
    }
}
