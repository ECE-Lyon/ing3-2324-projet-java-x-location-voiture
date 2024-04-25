package codes.dao;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import java.sql.SQLException;

public interface EntrepriseDao {

    void addEntreprise(Entreprise entreprise, Utilisateur utilisateur) throws SQLException;

}
