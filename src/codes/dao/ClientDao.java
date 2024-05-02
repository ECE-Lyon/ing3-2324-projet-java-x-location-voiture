package codes.dao;
import codes.model.Client;
import codes.model.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
    void addClient(Client client, Utilisateur utilisateur) throws SQLException;

    List<Client> searchClient() throws SQLException;

}
