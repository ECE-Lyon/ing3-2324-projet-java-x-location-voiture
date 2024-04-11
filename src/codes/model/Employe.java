package codes.model;

public class Employe extends Utilisateur{


    private String nom_employe;
    private String prenom_employe;
    private String poste;

    public Employe(int id_utilisateur, String mdp, String email, String nom_employe, String prenom_employe, String poste){
        super(id_utilisateur, mdp, email);
        this.nom_employe = nom_employe;
        this.prenom_employe = prenom_employe;
        this.poste = poste;
    }
    public String getNom_employe() {
        return nom_employe;
    }

    public void setNom_employe(String nom_employe) {
        this.nom_employe = nom_employe;
    }

    public String getPrenom_employe() {
        return prenom_employe;
    }

    public void setPrenom_employe(String prenom_employe) {
        this.prenom_employe = prenom_employe;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
