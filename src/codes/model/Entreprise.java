package codes.model;

public class Entreprise extends Utilisateur{

    private String nom_entreprise;
    private long siret;

    public Entreprise(int id_utilisateur, String mdp, String email, String nom_entreprise, long siret){
        super(id_utilisateur, mdp, email);
        this.nom_entreprise = nom_entreprise;
        this.siret = siret;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public long getSiret() {
        return siret;
    }

    public void setSiret(long siret) {
        this.siret = siret;
    }
}
