package codes.dao;
import codes.model.Type_voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Type_voitureDaoImpl implements Type_voitureDao {

    private Connection connection;

    public Type_voitureDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Type_voiture> searchType(String query) throws SQLException{
        List<Type_voiture> results = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM modele WHERE nom LIKE ?")){
            statement.setString(1, "%"+ query +"%");
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Type_voiture type_voiture = new Type_voiture();
                    type_voiture.setId_type_voiture(resultSet.getInt("id"));
                    type_voiture.setNom_type_voiture(resultSet.getString("nom"));
                    type_voiture.setMarque(resultSet.getString("marque"));

                    results.add(type_voiture);
                }
            }
        }

        return results;
    }
}
