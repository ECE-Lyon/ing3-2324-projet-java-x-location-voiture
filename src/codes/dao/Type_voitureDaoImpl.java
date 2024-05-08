package codes.dao;

import codes.model.Type_voiture;

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


    public List<Type_voiture> searchType(String type_voiture) throws SQLException{

        List<Type_voiture> results = new ArrayList<>();

        String query ="SELECT * FROM modele WHERE type = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, type_voiture);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Type_voiture modele = new Type_voiture(-1, null, null, null);
                    modele.setId_type_voiture(resultSet.getInt("id"));
                    modele.setNom_type_voiture(resultSet.getString("nom"));
                    modele.setMarque_voiture(resultSet.getString("marque"));

                    results.add(modele);
                }
            }
        }

        return results;
    }


    public void addModele(Type_voiture modele) throws SQLException {

        PreparedStatement modeleStatement;

        connection =Mysql.openConnection();

        String addModeleQuery = "INSERT INTO modele (nom, marque, type) VALUES (?, ?, ?)";
        modeleStatement = connection.prepareStatement(addModeleQuery);
        modeleStatement.setString(1, modele.getNom_type_voiture());
        modeleStatement.setString(2, modele.getMarque_voiture());
        modeleStatement.setString(3, modele.getType().toString());
        modeleStatement.executeUpdate();

    }

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

    public List<Type_voiture> searchModele(String marque) throws SQLException {
        List<Type_voiture> modeles = new ArrayList<>();
        String query = "SELECT * FROM modele WHERE marque = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, marque);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Type_voiture modele = new Type_voiture(-1, null, null, null);
                    modele.setId_type_voiture(resultSet.getInt("id"));
                    modele.setNom_type_voiture(resultSet.getString("nom"));
                    modele.setMarque_voiture(resultSet.getString("marque"));
                    modeles.add(modele);
                }
            }
        }
        return modeles;
    }

}
