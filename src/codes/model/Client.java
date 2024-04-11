package codes.model;

public class Client extends Utilisateur{

    private String nom_client;
    private String prenom_client;
    private Enum statut;

    public Client(int id_utilisateur, String mdp, String email, String nom_client, String prenom_client, Enum statut){
        super(id_utilisateur, mdp, email);
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.statut = statut;
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

    public Enum getStatut() {
        return statut;
    }

    public void setStatut(Enum statut) {
        this.statut = statut;
    }
}