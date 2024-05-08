package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.model.Employe;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnecPage extends JPanel implements ActionListener, MouseListener {

    private MainJFrame mainJFrame;
    private ShopPage shop;
    private InscrConnecPage inscrConnecPage;
    private InscrPage inscrPage;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsMainConnecInsc = new GridBagConstraints();
    private final GridBagConstraints constraintsMainConnec = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnec = new GridBagConstraints();





    private JPanel connecMainPanel = new JPanel();


    private JLabel legendaryMotorsportLabel2 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel2 = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);





    private JPanel usernamePWPanel = new JPanel();
    private JLabel backToMainMenu = new JLabel("RETOUR");
    private JLabel sInscrire = new JLabel("S'inscrire !");
    private JPanel topPanelConnec = new JPanel();

    private JLabel displayUsernameField = new JLabel("Adresse E-mail : ");
    private JLabel displayPasswordField = new JLabel("Mot de passe : ");

    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton connectionButton = new JButton("Connection");







    public ConnecPage(MainJFrame mainJFrame, ShopPage shop, InscrConnecPage inscrConnecPage, InscrPage inscrPage){
        this.mainJFrame = mainJFrame;
        this.inscrConnecPage = inscrConnecPage;
        this.inscrPage = inscrPage;
        this.shop = shop;


        this.setLayout(new BorderLayout());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////// SECONDE PAGE A ETRE AFFICHEE : CONNEXION  //////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Initialisation :
        this.connecMainPanel.setLayout(gridBagLayout);
        this.usernamePWPanel.setLayout(gridBagLayout);
        this.topPanelConnec.setLayout(gridBagLayout);
        this.connecMainPanel.setBackground(Color.WHITE);

        // Initialisation des contraintes :
        this.constraintsMainConnecInsc.gridx=0;
        this.constraintsMainConnecInsc.gridy=0;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.EAST;
        this.constraintsMainConnecInsc.weightx = 1.0;
        this.constraintsMainConnecInsc.weighty = 1.0;

        // On met les différents éléments dans le panel :
        this.usernamePWPanel.add(this.displayUsernameField, this.constraintsMainConnecInsc);
        this.constraintsMainConnecInsc.gridx = 1;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.WEST;
        this.usernameField.setColumns(20);
        this.usernamePWPanel.add(this.usernameField, this.constraintsMainConnecInsc);


        this.constraintsMainConnecInsc.gridx = 0;
        this.constraintsMainConnecInsc.gridy = 1;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.EAST;
        this.usernamePWPanel.add(this.displayPasswordField, this.constraintsMainConnecInsc);
        this.constraintsMainConnecInsc.gridx = 1;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.WEST;
        this.passwordField.setColumns(20);
        this.usernamePWPanel.add(this.passwordField, this.constraintsMainConnecInsc);


        this.constraintsMainConnecInsc.gridx = 1;
        this.constraintsMainConnecInsc.gridy = 2;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.WEST;

        ////////////////////////////////////////// verifier que toutes les informations sont entrées ///////////////////
        this.connectionButton.setActionCommand("BOUTON SE CONNECTER");
        this.connectionButton.addActionListener(this);
        this.usernamePWPanel.add(this.connectionButton, this.constraintsMainConnecInsc);
        this.constraintsMainConnecInsc.gridy = 3;
        this.usernamePWPanel.add(this.sInscrire, this.constraintsMainConnecInsc);


        this.legendaryMotorsportLabel2.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel2.setForeground(Color.MAGENTA); // Couleur du texte
        this.legendaryMotorsportLabel2.setFont(font1);
        this.legendaryMotorsportPanel2.add(this.legendaryMotorsportLabel2);
        this.legendaryMotorsportPanel2.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsInnerConnec.gridx = 0;
        this.constraintsInnerConnec.gridy = 0;
        this.constraintsInnerConnec.anchor = GridBagConstraints.NORTHWEST;


        backToMainMenu.addMouseListener(this);

        this.topPanelConnec.add(backToMainMenu, constraintsInnerConnec);
        this.constraintsInnerConnec.gridy = 1;
        this.constraintsInnerConnec.anchor = GridBagConstraints.CENTER;
        this.topPanelConnec.add(legendaryMotorsportPanel2, constraintsInnerConnec);


        // ajout des différents composants dans le panel principal.
        this.constraintsMainConnec.gridx = 0;
        this.constraintsMainConnec.gridy = 0;
        this.constraintsMainConnec.anchor = GridBagConstraints.CENTER;
        this.constraintsMainConnec.weightx = 1.0;
        this.constraintsMainConnec.weighty = 1.5;
        this.constraintsMainConnec.fill = GridBagConstraints.BOTH;

        this.connecMainPanel.add(this.topPanelConnec, constraintsMainConnec);
        this.constraintsMainConnec.anchor = GridBagConstraints.CENTER;
        this.constraintsMainConnec.gridy = 1;
        this.constraintsMainConnec.weighty = 4.5;
        this.connecMainPanel.add(this.usernamePWPanel, constraintsMainConnec);



        add(connecMainPanel, BorderLayout.CENTER);


    }

    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();

        // Réinitialisez le contenu principal ici, par exemple :
        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    public void mouseClicked(MouseEvent e) {
        JLabel labelClic = (JLabel) e.getSource();
        String text = labelClic.getText();
        switch (text){
            case "RETOUR":
                inscrConnecPage.resetMainContent();
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
        if(e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            Font font = label.getFont();
            label.setForeground(Color.RED);
            label.setFont(font.deriveFont(16f));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            label.setForeground(UIManager.getColor("Label.foreground"));
            Font font = label.getFont();
            label.setFont(font.deriveFont(12f));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "INSCRIPTION":
                inscrPage.resetMainContent();
                break;
            case "BOUTON SE CONNECTER" :
                char[] passwordF = this.passwordField.getPassword();
                String email = this.usernameField.getText();

                String password = new String(passwordF);
                if(password.isEmpty() || this.usernameField.getText().isEmpty()){} else {
                    try(Connection connection = Mysql.openConnection()) {

                        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                        Utilisateur utilisateur = utilisateurDao.getUtilisateur(email, password);

                        if(utilisateur != null){

                            if (utilisateur instanceof Client) {
                                System.out.println("Informations du client :");
                                this.mainJFrame.setClient((Client) utilisateur);
                                System.out.println("Nom : " + this.mainJFrame.getClient().getNom_client());
                                System.out.println("Prénom : " + this.mainJFrame.getClient().getPrenom_client());
                                // Ajoutez d'autres informations si nécessaire

                                this.mainJFrame.setEmail(email);
                                this.mainJFrame.setPassword(password);
                                this.mainJFrame.setName(this.mainJFrame.getClient().getNom_client());
                                this.mainJFrame.setFirstName(this.mainJFrame.getClient().getPrenom_client());

                                this.mainJFrame.setConnected(true);
                                this.shop.resetMainContent();


                            } else if (utilisateur instanceof Employe) {
                                System.out.println("Informations de l'employé :");
                                Employe employe = (Employe) utilisateur;
                                System.out.println("Nom : " + employe.getNom_employe());
                                System.out.println("Prénom : " + employe.getPrenom_employe());
                                System.out.println("Poste : " + employe.getPoste());
                                // Ajoutez d'autres informations si nécessaire
                            } else if (utilisateur instanceof Entreprise) {
                                System.out.println("Informations de l'entreprise :");
                                Entreprise entreprise = (Entreprise) utilisateur;
                                System.out.println("Nom : " + entreprise.getNom_entreprise());
                                System.out.println("Siret : " + entreprise.getSiret());
                                // Ajoutez d'autres informations si nécessaire
                            }

                        } else {
                            System.out.println("Aucun utilisateur trouvé avec ces informations d'identification.");
                        }

                    } catch (SQLException er){
                        er.printStackTrace();
                    }

                }
                break;
        }
    }
}
