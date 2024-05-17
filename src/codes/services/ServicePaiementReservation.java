package codes.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import codes.model.Reservation;
import codes.model.Voiture;

public class ServicePaiementReservation {

    private Connection connection;

    public ServicePaiementReservation(Connection connection){
        this.connection = connection;
    }

    public void effectuerPaiement(Reservation reservation, Voiture voiture){

        PreparedStatement statement;

        float montantTotal = calculerMontantTotal(reservation, voiture);
        int idReservation = reservation.getId_reservation();

        String query = "INSERT INTO paiement (montant, idReservation) VALUES (?, ?)";

        try {

            statement = connection.prepareStatement(query);

            statement.setFloat(1, montantTotal);
            statement.setInt(2, idReservation);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private float calculerMontantTotal(Reservation reservation, Voiture voiture){

        java.sql.Date dateDebut = reservation.getDate_debut();
        java.sql.Date dateFin = reservation.getDate_fin();
        float prixParJour = voiture.getPrix_par_jour();
        float remise = reservation.getRemise() / 100;

        long diffNbJoursEnMillis = dateFin.getTime() - dateDebut.getTime();
        int nbJours = (int) (diffNbJoursEnMillis / (1000 * 60 * 60 * 24));

        float montantTotal = (prixParJour * nbJours) - ((prixParJour * nbJours) * remise);

        return montantTotal;

    }

}
