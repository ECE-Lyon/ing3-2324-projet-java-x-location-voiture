package codes.dao;

import codes.model.Type_voiture;
import codes.model.Voiture;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface Type_voitureDao {

    List<Type_voiture> searchType(String type) throws SQLException;

    void addModele(Type_voiture modele) throws SQLException;

    List<Type_voiture> searchModele(String marque) throws SQLException;

    Set<String> searchAllMarques() throws SQLException;

    List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> searchAllModele() throws SQLException;

}
