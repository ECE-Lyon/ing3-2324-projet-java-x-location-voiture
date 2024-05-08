package codes.dao;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface EntrepriseDao {

    void addEntreprise(Entreprise entreprise, Utilisateur utilisateur) throws SQLException;

    List<Entreprise> searchEntreprise() throws SQLException;

    void updateEntreprise(Entreprise entreprise, Utilisateur utilisateur, String email, String mdp) throws SQLException;

}
