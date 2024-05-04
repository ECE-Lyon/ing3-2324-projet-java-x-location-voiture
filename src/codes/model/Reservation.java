package codes.model;

public class Reservation {

    private int id_reservation;
    private java.sql.Date date_debut;
    private java.sql.Date date_fin;
    private float remise;
    private int idUser;
    private int idVoiture;

    public Reservation(){}

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public java.sql.Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(java.sql.Date date_debut) {
        this.date_debut = date_debut;
    }

    public java.sql.Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(java.sql.Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }



}