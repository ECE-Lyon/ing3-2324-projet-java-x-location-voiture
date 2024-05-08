package codes.dao;
import codes.model.Employe;
import codes.model.Utilisateur;

import java.sql.SQLException;

public interface EmployeDao {

    void addEmploye(Employe employe, Utilisateur utilisateur) throws SQLException;

    void updateEmploye(Employe employe, Utilisateur utilisateur, String email, String mdp) throws SQLException;

}
