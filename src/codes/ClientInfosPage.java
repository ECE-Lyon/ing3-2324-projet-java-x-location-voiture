package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.dao.UtilisateurDao;
import codes.model.Entreprise;

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

public class ClientInfosPage extends JPanel implements ActionListener, MouseListener {
    private MainJFrame mainJFrame;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsMain = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth / 3, windowSizeHeight / 10);

    private int fontSizeLM = 36;
    private Font fontTop = new Font("Arial", Font.PLAIN, fontSizeLM);

    private JLabel backToMainMenu = new JLabel("RETOUR");


    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel topButtons = new JPanel();
    private JPanel botPanel = new JPanel();


    private JTable table;
    private JTable table2;


    private Connection connection;

    public ClientInfosPage(MainJFrame mainJFrame) throws SQLException {
        this.mainJFrame = mainJFrame;
        this.setLayout(new BorderLayout());

        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);


        this.connection = Mysql.openConnection();

        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(fontTop);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsTop.gridx = 0;
        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.NORTHWEST;

        ////////////////////////////////
        //////////  Ajouter Boutons !!!!
        ////////////////////////////////
        this.constraintsTop.gridx = 0;
        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.NORTHWEST;

        backToMainMenu.addMouseListener(this);

        this.topPanel.add(backToMainMenu, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;


        ////////////////////////////////


        this.constraintsTop.gridy = 1;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;
        this.topPanel.add(topButtons, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.topPanel.setMaximumSize(new Dimension(windowSizeWidth / 5, windowSizeHeight / 10));
        this.topPanel.add(legendaryMotorsportPanel, constraintsTop);


        ArrayList<String[]> data = new ArrayList<>();
        ArrayList<String[]> data2 = new ArrayList<>();

        try {

            UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);


            List<Entreprise> entreprises = utilisateurDao.searchEntreprise();
            System.out.println("Informations des entreprises :");
            for (Entreprise entreprise : entreprises) {
                System.out.println("Nom : " + entreprise.getNom_entreprise());
                System.out.println("Siret : " + entreprise.getSiret());
                data.add(new String[]{entreprise.getNom_entreprise(), "" + entreprise.getSiret()});
            }

            // Définir les en-têtes de colonne
            String[] columnNames = {"Nom : ", "Siret : "};
            String[][] dataArray = new String[data.size()][];
            data.toArray(dataArray);
            table = new JTable(dataArray, columnNames);


            List<Client> clients = utilisateurDao.searchClient();
            System.out.println("Informations des clients :");
            for (Client client : clients) {
                System.out.println("Nom : " + client.getNom_client());
                System.out.println("Prénom : " + client.getPrenom_client());
                System.out.println("Statut : " + client.getStatut());
                data2.add(new String[]{client.getNom_client(), client.getPrenom_client(), "" + client.getStatut()});
            }
            String[] columnNames2 = {"Nom : ", "Prénom : ", "Statut : "};
            String[][] dataArray2 = new String[data2.size()][];
            data2.toArray(dataArray2);
            table2 = new JTable(dataArray2, columnNames2);


        } catch (SQLException er) {
            er.printStackTrace();
        }


        constraintsMain.gridx = 0;
        constraintsMain.gridy = 0;
        constraintsMain.anchor = GridBagConstraints.CENTER;
        this.mainPanel.add(topPanel, constraintsMain);
        constraintsMain.gridy = 1;
        this.mainPanel.add(botPanel, constraintsMain);
        add(this.mainPanel, BorderLayout.CENTER);
        resetMainContent();
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
            case "INSCRIPTION":
                //inscrPage.resetMainContent();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel labelClic = (JLabel) e.getSource();
        String text = labelClic.getText();
        switch (text) {
            case "RETOUR":
                this.mainJFrame.getEmployeeMainPage().resetMainContent();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            Font font = label.getFont();
            label.setForeground(Color.RED);
            label.setFont(font.deriveFont(16f));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            label.setForeground(UIManager.getColor("Label.foreground"));
            Font font = label.getFont();
            label.setFont(font.deriveFont(12f));
        }
    }
}
