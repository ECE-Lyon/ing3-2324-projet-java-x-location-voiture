package codes.dao;

import codes.model.Voiture;

import java.sql.SQLException;
import java.util.List;

public interface VoitureDao {
    void addVoiture(Voiture voiture) throws SQLException;

    Voiture getVoiture(int id) throws SQLException;

    void updatePrixVoiture (int idVoiture, float nouveauPrix) throws SQLException;

    int getIdVoiture(Voiture voiture) throws SQLException;

    void modifVoiture(Voiture voiture) throws SQLException;
}
