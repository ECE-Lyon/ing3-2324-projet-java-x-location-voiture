package codes.model;

public class Utilisateur {
    private int id_utilisateur;
    private String mdp;
    private String email;

    public Utilisateur(int id_utilisateur, String mdp, String email){
        this.id_utilisateur = id_utilisateur;
        this.mdp = mdp;
        this.email = email;
    }
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
