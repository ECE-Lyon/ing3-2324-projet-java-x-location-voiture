package codes.model;

public class Paiement {

    private int id_paiement;
    private float montant;

    public Paiement(int id_paiement, float montant){
        this.id_paiement = id_paiement;
        this.montant = montant;
    }

    public int getId_paiement() {
        return id_paiement;
    }

    public void setId_paiement(int id_paiement) {
        this.id_paiement = id_paiement;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
