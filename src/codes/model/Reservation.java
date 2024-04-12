package codes.model;

import java.time.LocalDate;

public class Reservation {

    private int id_reservation;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private float remise;

    public Reservation(int id_reservation, LocalDate date_debut, LocalDate date_fin, float remise){
        this.id_reservation=id_reservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.remise = remise;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }
}