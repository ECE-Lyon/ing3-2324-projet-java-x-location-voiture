package codes.dao;
import codes.model.Client;
import codes.model.Employe;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class UtilisateurDaoImpl implements ClientDao, EmployeDao, EntrepriseDao, UtilisateurDao{

    private Connection c;

    public UtilisateurDaoImpl(Connection c){
        this.c = c;
    }

    //CLIENT

    @Override
    public void addClient(Client client, Utilisateur utilisateur) throws SQLException{

        PreparedStatement utilisateurStatement;
        PreparedStatement clientStatement;

        try{

            if (verifEmailInscription(utilisateur.getEmail())){

                System.out.println("email déjà existant, vous avez déjà un compte.");
                return;

            }

            String addUserQuery = "INSERT INTO utilisateur (email, mdp) VALUES (?, ?)";
            utilisateurStatement = c.prepareStatement(addUserQuery);
            utilisateurStatement.setString(1, utilisateur.getEmail());
            utilisateurStatement.setString(2, utilisateur.getMdp());
            utilisateurStatement.executeUpdate();

            int idUtilisateur = getLastInsertId(c);

            String addClientQuery = "INSERT INTO client (nom, prenom, idUser, statut) VALUES (?, ?, ?, ?)";
            clientStatement = c.prepareStatement(addClientQuery);
            clientStatement.setString(1, client.getNom_client());
            clientStatement.setString(2, client.getPrenom_client());
            clientStatement.setInt(3, idUtilisateur);
            clientStatement.setString(4, "NOUVEAU");
            clientStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Client> searchClient() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";

        try (PreparedStatement statement = c.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String statutString = resultSet.getString("statut");
                Client.Statut statut = Client.Statut.valueOf(statutString.toUpperCase());

                Client client = new Client(
                        resultSet.getInt("idClient"),
                        null,
                        null,
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        statut
                );
                clients.add(client);
            }
        }

        return clients;
    }

    @Override
    public void updateClient(Client client, Utilisateur utilisateur, String email, String mdp) throws SQLException {

        PreparedStatement utilisateurStatement;
        PreparedStatement clientStatement;

        try {

            int idUtilisateur = getIdUserByEmailAndMdp(email, mdp);

            if (idUtilisateur != -1){

                String updateUserQuery = "UPDATE utilisateur SET email = ?, mdp = ? WHERE id = ?";

                utilisateurStatement = c.prepareStatement(updateUserQuery);
                utilisateurStatement.setString(1, utilisateur.getEmail());
                utilisateurStatement.setString(2, utilisateur.getMdp());
                utilisateurStatement.setInt(3, idUtilisateur);
                utilisateurStatement.executeUpdate();


                String updateClientQuery = "UPDATE client SET nom = ?, prenom = ? WHERE idUser = ?";
                clientStatement = c.prepareStatement(updateClientQuery);
                clientStatement.setString(1, client.getNom_client());
                clientStatement.setString(2, client.getPrenom_client());
                clientStatement.setInt(3, idUtilisateur);
                clientStatement.executeUpdate();


                utilisateurStatement.close();
                clientStatement.close();

            } else {
                System.out.println("Aucun utilisateur trouvé avec ces informations d'identification.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //EMPLOYE
    @Override
    public void addEmploye(Employe employe, Utilisateur utilisateur) throws SQLException{

        PreparedStatement utilisateurStatement;
        PreparedStatement employeStatement;

        try {

            if (verifEmailInscription(utilisateur.getEmail())){

                System.out.println("email déjà existant, vous avez déjà un compte.");
                return;

            }

            String addUserQuery = "INSERT INTO utilisateur (email, mdp) VALUES (?, ?)";
            utilisateurStatement = c.prepareStatement(addUserQuery);
            utilisateurStatement.setString(1, utilisateur.getEmail());
            utilisateurStatement.setString(2, utilisateur.getMdp());
            utilisateurStatement.executeUpdate();

            int idUtilisateur = getLastInsertId(c);

            String addEmployeQuery = "INSERT INTO employe (nom, prenom, idUser, poste) VALUES (?, ?, ?, ?)";
            employeStatement = c.prepareStatement(addEmployeQuery);
            employeStatement.setString(1, employe.getNom_employe());
            employeStatement.setString(2, employe.getPrenom_employe());
            employeStatement.setInt(3, idUtilisateur);
            employeStatement.setString(4, "vendeur");
            employeStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmploye(Employe employe, Utilisateur utilisateur, String email, String mdp) throws SQLException{

        PreparedStatement utilisateurStatement;
        PreparedStatement employeStatement;

        try {

            int idUtilisateur = getIdUserByEmailAndMdp(email, mdp);

            if (idUtilisateur != -1){

                String updateUserQuery = "UPDATE utilisateur SET email = ?, mdp = ? WHERE id = ?";
                utilisateurStatement = c.prepareStatement(updateUserQuery);
                utilisateurStatement.setString(1, utilisateur.getEmail());
                utilisateurStatement.setString(2, utilisateur.getMdp());
                utilisateurStatement.setInt(3, idUtilisateur);
                utilisateurStatement.executeUpdate();


                String updateEmployeQuery = "UPDATE employe SET nom = ?, prenom = ? WHERE idUser = ?";
                employeStatement = c.prepareStatement(updateEmployeQuery);
                employeStatement.setString(1, employe.getNom_employe());
                employeStatement.setString(2, employe.getPrenom_employe());
                employeStatement.setInt(3, idUtilisateur);
                employeStatement.executeUpdate();


                utilisateurStatement.close();
                employeStatement.close();

            } else {
                System.out.println("Aucun utilisateur trouvé avec ces informations d'identification.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    //ENTREPRISE
    @Override
    public void addEntreprise(Entreprise entreprise, Utilisateur utilisateur) throws SQLException{

        PreparedStatement utilisateurStatement;
        PreparedStatement entrepriseStatement;

        try {

            if (verifEmailInscription(utilisateur.getEmail())){

                System.out.println("email déjà existant, vous avez déjà un compte.");
                return;

            }

            String addUserQuery = "INSERT INTO utilisateur (email, mdp) VALUES (?, ?)";
            utilisateurStatement = c.prepareStatement(addUserQuery);
            utilisateurStatement.setString(1, utilisateur.getEmail());
            utilisateurStatement.setString(2, utilisateur.getMdp());
            utilisateurStatement.executeUpdate();

            int idUtilisateur = getLastInsertId(c);

            String addEntrepriseQuery = "INSERT INTO entreprise (nom, siret, idUser) VALUES (?, ?, ?)";
            entrepriseStatement = c.prepareStatement(addEntrepriseQuery);
            entrepriseStatement.setString(1, entreprise.getNom_entreprise());
            entrepriseStatement.setLong(2, entreprise.getSiret());
            entrepriseStatement.setInt(3, idUtilisateur);
            entrepriseStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Entreprise> searchEntreprise() throws SQLException{
        List<Entreprise> entreprises = new ArrayList<>();
        String query = "SELECT * FROM entreprise";
        try (PreparedStatement statement = c.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Entreprise entreprise = new Entreprise(
                        resultSet.getInt("idEntreprise"),
                        null,
                        null,
                        resultSet.getString("nom"),
                        resultSet.getLong("siret")
                );
                entreprises.add(entreprise);
            }
        }

        return entreprises;
    }

    @Override
    public void updateEntreprise(Entreprise entreprise, Utilisateur utilisateur, String email, String mdp) throws SQLException{

        PreparedStatement utilisateurStatement;
        PreparedStatement entrepriseStatement;

        try {

            //c = Mysql.openConnection();

            int idUtilisateur = getIdUserByEmailAndMdp(email, mdp);

            if (idUtilisateur != -1){

                String updateUserQuery = "UPDATE utilisateur SET email = ?, mdp = ? WHERE id = ?";
                utilisateurStatement = c.prepareStatement(updateUserQuery);
                utilisateurStatement.setString(1, utilisateur.getEmail());
                utilisateurStatement.setString(2, utilisateur.getMdp());
                utilisateurStatement.setInt(3, idUtilisateur);
                utilisateurStatement.executeUpdate();


                String updateEntrepriseQuery = "UPDATE entreprise SET nom = ? WHERE idUser = ?";
                entrepriseStatement = c.prepareStatement(updateEntrepriseQuery);
                entrepriseStatement.setString(1, entreprise.getNom_entreprise());
                entrepriseStatement.setInt(2, idUtilisateur);
                entrepriseStatement.executeUpdate();


                utilisateurStatement.close();
                entrepriseStatement.close();

            } else {
                System.out.println("Aucun utilisateur trouvé avec ces informations d'identification.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    //UTILISATEUR
    @Override
    public Utilisateur getUtilisateur(String email, String mdp) throws SQLException {
        Utilisateur utilisateur = null;
        int idUtilisateur = getIdUserByEmailAndMdp(email, mdp);

        if (idUtilisateur != -1) {
            // Vérifier s'il s'agit d'un client
            try (PreparedStatement statement = c.prepareStatement("SELECT * FROM client WHERE idUser = ?")) {
                statement.setInt(1, idUtilisateur);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        utilisateur = new Client(resultSet.getInt("idClient"), mdp, email, resultSet.getString("nom"), resultSet.getString("prenom"), Client.Statut.valueOf(resultSet.getString("statut")));
                    }
                }
            }

            // Si ce n'est pas un client, vérifier s'il s'agit d'un employé
            if (utilisateur == null) {
                try (PreparedStatement statement = c.prepareStatement("SELECT * FROM employe WHERE idUser = ?")) {
                    statement.setInt(1, idUtilisateur);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            utilisateur = new Employe(resultSet.getInt("idEmploye"), mdp, email, resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("poste"));
                        }
                    }
                }
            }

            // Si ce n'est ni un client ni un employé, vérifier s'il s'agit d'une entreprise
            if (utilisateur == null) {
                try (PreparedStatement statement = c.prepareStatement("SELECT * FROM entreprise WHERE idUser = ?")) {
                    statement.setInt(1, idUtilisateur);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            utilisateur = new Entreprise(resultSet.getInt("idEntreprise"), mdp, email, resultSet.getString("nom"), resultSet.getLong("siret"));
                        }
                    }
                }
            }
        }

        return utilisateur;
    }

    //AUTRES FONCTIONS
    private static int getLastInsertId(Connection c) throws SQLException {
        int lastInsertId = -1;
        PreparedStatement statement = null;
        try {
            statement = c.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lastInsertId = resultSet.getInt(1);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return lastInsertId;
    }

    private int getIdUserByEmailAndMdp(String email, String mdp) throws SQLException {
        int idUtilisateur = -1;
        try (PreparedStatement statement = c.prepareStatement("SELECT id FROM utilisateur WHERE email = ? AND mdp = ?")) {
            statement.setString(1, email);
            statement.setString(2, mdp);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idUtilisateur = resultSet.getInt("id");
                }
            }
        }
        return idUtilisateur;
    }

    public boolean verifEmailInscription(String email) throws SQLException {

        String query = "SELECT COUNT(*) AS count FROM utilisateur WHERE email = ?";

        try(PreparedStatement statement = c.prepareStatement(query)) {

            statement.setString(1, email);

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }

            }

        }

        return false;

    }

    public int nbReservations(int idUtilisateur) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM reservation WHERE idUser = ?";
        try (PreparedStatement statement = c.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        }
        return count;
    }

    public void updateStatutClient(Utilisateur utilisateur) throws SQLException {
        int nbReservations = nbReservations(utilisateur.getId_utilisateur());
        String newStatut;
        if (nbReservations >= 0 && nbReservations <= 5) {
            newStatut = String.valueOf(Client.Statut.NOUVEAU);
        } else if (nbReservations >= 6 && nbReservations <= 15) {
            newStatut = String.valueOf(Client.Statut.ADHERENT);
        } else {
            newStatut = String.valueOf(Client.Statut.VIP);
        }

        // Mettre à jour le statut dans la base de données
        String query = "UPDATE client SET statut = ? WHERE idUser = ?";
        try (PreparedStatement statement = c.prepareStatement(query)) {
            statement.setString(1, newStatut);
            statement.setInt(2, utilisateur.getId_utilisateur()); // Définir la valeur du deuxième paramètre
            statement.executeUpdate();
        }
    }

}