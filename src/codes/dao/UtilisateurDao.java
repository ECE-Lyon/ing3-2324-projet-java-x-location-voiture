package codes.dao;
import codes.model.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface UtilisateurDao {

    public List<Utilisateur> getUtilisateur(String email, String mdp) throws SQLException;

}
