package codes.dao;

import codes.model.Type_voiture;
import codes.model.Voiture;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;

public class Type_voitureDaoImpl implements Type_voitureDao {

    private Connection connection;

    public Type_voitureDaoImpl(Connection connection) {
        this.connection = connection;
    }


    // CHERCHE UN MODELE EN FONCTION DU TYPE (SUV, SPORT, BERLINE...)
    @Override
    public List<Type_voiture> searchType(String type_voiture) throws SQLException{

        List<Type_voiture> results = new ArrayList<>();

        String query ="SELECT * FROM modele WHERE type = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, type_voiture);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Type_voiture modele = new Type_voiture(-1, null, null, null, null);
                    modele.setId_type_voiture(resultSet.getInt("id"));
                    modele.setNom_type_voiture(resultSet.getString("nom"));
                    modele.setMarque_voiture(resultSet.getString("marque"));

                    results.add(modele);
                }
            }
        }

        return results;
    }


    // PERMET D'AJOUTER UN MODELE
    @Override
    public void addModele(Type_voiture modele) throws SQLException {

        PreparedStatement modeleStatement;

        //connection =Mysql.openConnection();

        try {

            String addModeleQuery = "INSERT INTO modele (nom, marque, type) VALUES (?, ?, ?)";
            modeleStatement = connection.prepareStatement(addModeleQuery);
            modeleStatement.setString(1, modele.getNom_type_voiture());
            modeleStatement.setString(2, modele.getMarque_voiture());
            modeleStatement.setString(3, modele.getType().toString());
            modeleStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    // PERMET DE RECHERCHE TOUTES LES MARQUES DISTINCTES
    @Override
    public Set<String> searchAllMarques() throws SQLException {
        Set<String> marques = new HashSet<>();

        String query = "SELECT DISTINCT marque FROM modele";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                marques.add(resultSet.getString("marque"));
            }
        }

        return marques;
    }

    // PERMET DE RECHERCHER UN MODELE EN FONCTION DE LA MARQUE (AUDI, BMW..)
    @Override
    public List<Type_voiture> searchModele(String marque) throws SQLException {
        List<Type_voiture> modeles = new ArrayList<>();
        String query = "SELECT * FROM modele WHERE marque = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, marque);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Type_voiture modele = new Type_voiture(-1, null, null, null, null);
                    modele.setId_type_voiture(resultSet.getInt("id"));
                    modele.setNom_type_voiture(resultSet.getString("nom"));
                    modele.setMarque_voiture(resultSet.getString("marque"));
                    modeles.add(modele);
                }
            }
        }
        return modeles;
    }

    // PERMET DE RECHERCHER TOUS LES MODELES DANS LA BDD
    @Override
    public List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> searchAllModele() throws SQLException {
        List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> modeleVoiturePairs = new ArrayList<>();
        String queryTypeVoiture = "SELECT * FROM modele";
        String queryVoiture = "SELECT * FROM voiture WHERE id_modele = ? ORDER BY id ASC LIMIT 1";
        PreparedStatement statement1;
        PreparedStatement statement2;

        try {
            statement1 = connection.prepareStatement(queryTypeVoiture);
            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()) {
                Type_voiture.Type type = Type_voiture.Type.valueOf(resultSet1.getString("type"));

                Type_voiture type_voiture = new Type_voiture(
                        resultSet1.getInt("id"),
                        resultSet1.getString("nom"),
                        resultSet1.getString("marque"),
                        type,
                        resultSet1.getString("description")
                );

                statement2 = connection.prepareStatement(queryVoiture);
                statement2.setInt(1, resultSet1.getInt("id")); // Set id_modele parameter
                ResultSet resultSet2 = statement2.executeQuery();

                Voiture voiture = null;
                if (resultSet2.next()) {
                    voiture = new Voiture(
                            resultSet2.getInt("id"),
                            resultSet2.getFloat("prixParJour"),
                            resultSet2.getInt("id_modele")
                    );
                }

                modeleVoiturePairs.add(new ConnectionUrlParser.Pair<>(type_voiture, voiture));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeleVoiturePairs;
    }

    // PERMET DE SUPPRIMER UN MODELE GRACE A UN ID
    public void deleteModele(int modeleId) throws SQLException {
        PreparedStatement deleteStatement = null;
        try {
            String deleteModeleQuery = "DELETE FROM modele WHERE id = ?";
            deleteStatement = connection.prepareStatement(deleteModeleQuery);
            deleteStatement.setInt(1, modeleId);
            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun modèle trouvé avec l'ID spécifié.");
            } else {
                System.out.println("Modèle supprimé avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (deleteStatement != null) {
                deleteStatement.close();
            }
        }
    }


    // PERMET D'AJOUTER UNE DESCRIPTION A UN MODELE
    public void addDescriptionToModele(int modeleId, String description) throws SQLException {
        PreparedStatement updateStatement = null;
        try {
            String updateQuery = "UPDATE modele SET description = ? WHERE id = ?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, description);
            updateStatement.setInt(2, modeleId);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun modèle trouvé avec l'ID spécifié.");
            } else {
                System.out.println("Description ajoutée avec succès au modèle.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
        }
    }

    // PERMET DE RECUPERER LA DESCRIPTION GRACE A L'ID
    public String getDescriptionById(int modeleId) throws SQLException {
        PreparedStatement statement;
        try {
            String query = "SELECT description FROM modele WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, modeleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("description");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
