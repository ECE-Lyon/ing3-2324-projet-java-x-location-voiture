package codes.dao;

import codes.model.Type_voiture;
import java.sql.SQLException;
import java.util.List;

public interface Type_voitureDao {
    List<Type_voiture> searchType(String query) throws SQLException;

}
