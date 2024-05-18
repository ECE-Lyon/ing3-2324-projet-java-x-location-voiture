package codes.dao;

import codes.model.Type_voiture;
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

    public int popularityModele(int idModele) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM voiture WHERE id_modele = ? AND statut = 1";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idModele);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int bestModele() throws SQLException {
        int meilleurModele = -1;
        int meilleurePopularite = -1;
        String query = "SELECT DISTINCT id_modele FROM voiture";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idModele = resultSet.getInt("id_modele");
                int popularite = popularityModele(idModele);
                if (popularite > meilleurePopularite) {
                    meilleurePopularite = popularite;
                    meilleurModele = idModele;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meilleurModele;
    }

    public Type_voiture getModeleById(int idModele) throws SQLException {
        Type_voiture modele = null;
        String query = "SELECT * FROM modele WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idModele);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String marque = resultSet.getString("marque");
                    modele = new Type_voiture(id, nom, marque, null, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modele;
    }

}
