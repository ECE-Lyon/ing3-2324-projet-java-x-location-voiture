package codes.dao;
import codes.model.Employe;
import codes.model.Utilisateur;

import java.sql.SQLException;

public interface EmployeDao {

    void addEmploye(Employe employe, Utilisateur utilisateur) throws SQLException;

}
