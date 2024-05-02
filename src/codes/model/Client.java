package codes.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client extends Utilisateur{

    private String nom_client;
    private String prenom_client;
    public enum Statut{NOUVEAU, ADHERENT, VIP};
    private Statut statut;

    public Client(int id_utilisateur, String mdp, String email, String nom_client, String prenom_client, Statut statut){
        super(id_utilisateur, mdp, email);
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.statut = statut;
    }

    public Client(ResultSet resultSet) throws SQLException {
        super(resultSet.getInt("idUser"), resultSet.getString("mdp"), resultSet.getString("email"));
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}