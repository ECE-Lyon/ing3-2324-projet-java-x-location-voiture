package codes;

import codes.dao.Mysql;
import codes.dao.ReservationDaoImpl;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.model.Entreprise;
import codes.model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesReservations extends JPanel implements ActionListener, MouseListener {
    MainJFrame mainJFrame;
    ConnecPage connecPage;
    InscrPage inscrPage;
    InscrConnecPage inscrConnecPage;
    ShopPage shopPage;
    PrivateSpacePage privateSpacePage;



    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsBot = new GridBagConstraints();
    private final GridBagConstraints constraintsMainPanel = new GridBagConstraints();

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel botPanel = new JPanel();
    private JPanel topAndBotPanel = new JPanel();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
    private JPanel legendaryMotorsportPanel = new JPanel();
    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);



    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);

    private final JButton backButton = new JButton("Retour");


    private JTable table;

    private Connection connection;

    private JButton accessToPaymentPage = new JButton("Accéder à la page de payement");
    private JButton supprAReservation = new JButton("Supprimer un élément du panier ");
    private JDialog dialog = new JDialog();


    public MesReservations (MainJFrame mainJFrame, ConnecPage connecPage, InscrPage inscrPage, InscrConnecPage inscrConnecPage, ShopPage shopPage, PrivateSpacePage privateSpacePage) throws SQLException {
        this.mainJFrame = mainJFrame;
        this.connecPage = connecPage;
        this.inscrPage = inscrPage;
        this.inscrConnecPage = inscrConnecPage;
        this.shopPage = shopPage;
        this.privateSpacePage = privateSpacePage;

        this.connection = Mysql.openConnection();





        this.setLayout(new BorderLayout());
        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);
        this.topAndBotPanel.setLayout(gridBagLayout);
        this.mainPanel.setBackground(Color.WHITE);



        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(font1);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsTop.gridy = 0;


        //this.constraints.gridx = 1;
        this.constraintsTop.anchor = GridBagConstraints.EAST;
        backButton.setActionCommand("BACK TO SHOP");
        backButton.addActionListener(this);
        this.topPanel.add(backButton, constraintsTop);
        this.constraintsTop.anchor = GridBagConstraints.CENTER;
        this.constraintsTop.gridy = 1;
        this.topPanel.add(legendaryMotorsportPanel, constraintsTop);




        ArrayList<String[]> data = new ArrayList<>();

        try {

            ReservationDaoImpl reservationDao = new ReservationDaoImpl(connection);

            // Appeler la méthode searchReservationForOneUser pour un utilisateur donné (par exemple, l'utilisateur avec id 1)
            int userId = this.mainJFrame.getIdUtilisateur();
            List<Reservation> reservations = reservationDao.searchReservationForOneUser(userId);

            // Afficher les réservations obtenues
            for (Reservation reservation : reservations) {
                System.out.println("ID de la réservation : " + reservation.getId_reservation());
                System.out.println("Date de début : " + reservation.getDate_debut());
                System.out.println("Date de fin : " + reservation.getDate_fin());
                System.out.println("Remise : " + reservation.getRemise());
                System.out.println("ID de l'utilisateur : " + reservation.getIdUser());
                System.out.println("ID de la voiture : " + reservation.getIdVoiture());
                System.out.println("----------------------------------");
                data.add(new String[]{"" + reservation.getId_reservation(), "" + reservation.getDate_debut(),
                        "" + reservation.getDate_fin(), "" + reservation.getRemise(), ""+reservation.getIdUser(),
                        ""+reservation.getIdVoiture()});
            }

            UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);


            java.util.List<Entreprise> entreprises = utilisateurDao.searchEntreprise();


            // Définir les en-têtes de colonne
            String[] columnNames = {"ID : ", "Date de début : ", "Date de fin : ", "Remise", "ID de l'utilisateur : ", "ID de la voiture : "};
            String[][] dataArray = new String[data.size()][];
            data.toArray(dataArray);
            table = new JTable(dataArray, columnNames);


            List<Client> clients = utilisateurDao.searchClient();

            this.botPanel.add(table, constraintsBot);


        }
        catch (SQLException er) {
            er.printStackTrace();
        }

        constraintsBot.gridy++;
        this.botPanel.add(table, constraintsBot);
        constraintsBot.gridy++;
        accessToPaymentPage.setActionCommand("ACCESS TO PAYMENT");
        accessToPaymentPage.addActionListener(this);
        this.botPanel.add(accessToPaymentPage, constraintsBot);

        constraintsBot.gridy++;
        supprAReservation.setActionCommand("SUPPRIMER UN ARTICLE");
        supprAReservation.addActionListener(this);
        this.botPanel.add(supprAReservation, constraintsBot);





        constraintsMainPanel.anchor = GridBagConstraints.CENTER;
        constraintsMainPanel.gridx = 0;
        constraintsMainPanel.gridy = 0;
        this.mainPanel.add(this.topPanel, constraintsMainPanel);
        constraintsMainPanel.gridy = 1;
        this.mainPanel.add(this.botPanel, constraintsMainPanel);
        this.add(mainPanel, BorderLayout.CENTER);
    }


    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();

        // Réinitialisez le contenu principal ici :
        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "BACK TO SHOP":
                this.shopPage.resetMainContent();
                break;
            case "ACCESS TO PAYMENT":
                // il faut acceder a la page de payement mtn
                this.mainJFrame.getPaymentPage().resetMainContent();
                break;
            case "SUPPRIMER UN ARTICLE":
                dialog.setLayout(gridBagLayout);
                GridBagConstraints cstr = new GridBagConstraints();
                JButton finish;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}