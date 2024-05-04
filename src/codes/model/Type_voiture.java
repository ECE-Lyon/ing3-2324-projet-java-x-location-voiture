package codes.model;

public class Type_voiture {

    private int id_type_voiture;
    private String nom_type_voiture;
    private String marque;

    public Type_voiture(){}

    public int getId_type_voiture() {
        return id_type_voiture;
    }

    public void setId_type_voiture(int id_type_voiture) {
        this.id_type_voiture = id_type_voiture;
    }

    public String getNom_type_voiture() {
        return nom_type_voiture;
    }

    public void setNom_type_voiture(String nom_type_voiture) {
        this.nom_type_voiture = nom_type_voiture;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}