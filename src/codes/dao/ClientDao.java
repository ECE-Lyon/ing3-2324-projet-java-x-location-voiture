package codes.dao;
import codes.model.Client;
import codes.model.Utilisateur;

import java.sql.SQLException;

public interface ClientDao {
    void addClient(Client client, Utilisateur utilisateur) throws SQLException;

}
