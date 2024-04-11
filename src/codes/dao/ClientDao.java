package codes.dao;
import codes.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
    void add(Client client) throws SQLException;
    List<Client> search(String query) throws SQLException;
    Client get(int id_Utilisateur) throws SQLException;
}
