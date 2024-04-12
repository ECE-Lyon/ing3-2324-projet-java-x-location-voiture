package codes.model;

public class Voiture {
    private int id_voiture;
    private float prix_par_jour;

    public Voiture(int id_voiture, float prix_par_jour){
        this.id_voiture = id_voiture;
        this.prix_par_jour = prix_par_jour;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public float getPrix_par_jour() {
        return prix_par_jour;
    }

    public void setPrix_par_jour(float prix_par_jour) {
        this.prix_par_jour = prix_par_jour;
    }

}
