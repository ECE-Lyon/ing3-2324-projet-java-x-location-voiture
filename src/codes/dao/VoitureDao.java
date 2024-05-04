package codes.dao;

import codes.model.Voiture;

import java.sql.SQLException;
import java.util.List;

public interface VoitureDao {
    void addVoiture(Voiture voiture) throws SQLException;

    Voiture getVoiture(int id) throws SQLException;


}