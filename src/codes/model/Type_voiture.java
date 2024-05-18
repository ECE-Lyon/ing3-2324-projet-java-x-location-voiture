package codes.model;

public class Type_voiture {

    private int id_type_voiture;
    private String nom_type_voiture;
    private String marque_voiture;
    public enum Type{BREAK, BERLINE, SUV, SPORT, LIMOUSINE, PICK_UP};
    private Type type;
    private String description;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;

    public Type_voiture(){}

    public Type_voiture(int id_type_voiture, String nom_type_voiture, String marque_voiture, Type type, String description, byte[] image1, byte[] image2, byte[] image3){
        this.id_type_voiture = id_type_voiture;
        this.nom_type_voiture = nom_type_voiture;
        this.marque_voiture = marque_voiture;
        this.type = type;
        this.description = description;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

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

    public String getMarque_voiture() {
        return marque_voiture;
    }

    public void setMarque_voiture(String marque_voiture) {
        this.marque_voiture = marque_voiture;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

}