package codes.model;

public class Entreprise extends Utilisateur{


    private String nom_entreprise;
    private int siret;

    public Entreprise(int id_utilisateur, String mdp, String email, String nom_entreprise, int siret){
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

    public int getSiret() {
        return siret;
    }

    public void setSiret(int siret) {
        this.siret = siret;
    }
}
