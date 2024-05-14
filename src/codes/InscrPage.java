package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
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

public class InscrPage extends JPanel implements ActionListener, MouseListener {

    private MainJFrame mainJFrame;
    private ShopPage shop;
    private InscrConnecPage inscrConnecPage;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final GridBagConstraints constraints2 = new GridBagConstraints();
    private final GridBagConstraints constraints3 = new GridBagConstraints();
    private final GridBagConstraints constraints4 = new GridBagConstraints();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();


    private JPanel topPanelInscr = new JPanel();
    private JLabel legendaryMotorsportLabel3 = new JLabel("LEGENDARY MOTORSPORT");
    private JPanel legendaryMotorsportPanel3 = new JPanel();

    private JPanel inscrMainPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);

    private JLabel backToMainMenu2 = new JLabel("RETOUR");


    /////// INSCRIPTION
    private JPanel inscrFormPanel1 = new JPanel();
    private JLabel headerInscrForm = new JLabel("Inscription");
    private JLabel firstNameInscrLabel = new JLabel("Prénom : ");
    private JLabel lastNameInscrLabel = new JLabel("Nom : ");
    private JLabel emailInscrLabel = new JLabel("Courriel : ");
    private JLabel passwordInscrLabel = new JLabel("Password : ");
    private JLabel alreadyMember = new JLabel("Déjà membre ? Connectez vous !");
    private JLabel elementMissingInscr = new JLabel("Veuillez remplir tout les champs.");
    private JTextField firstNameInscrTF = new JTextField();
    private JTextField lastNameInscrTF = new JTextField();
    private JTextField emailInscrTF = new JTextField();
    private JPasswordField passwordInscrTF = new JPasswordField();
    private JButton signUpButton1 = new JButton("S'inscrire");


    private JPanel inscrFormPanel2 = new JPanel();
    private JLabel nameCompanyLabel = new JLabel("Nom de l'entreprise : ");
    private JLabel siretCompanyLabel = new JLabel("Siret : ");
    private JLabel emailCompanyLabel = new JLabel("Email : ");
    private JLabel passwordCompanyLabel = new JLabel("Mot de passe : ");
    private JTextField nameCompanyTF = new JTextField();
    private JTextField siretCompanyTF = new JTextField();
    private JTextField emailCompanyTF = new JTextField();
    private JPasswordField passwordCompanyTF = new JPasswordField();
    private JRadioButton radioClient = new JRadioButton("Je souhaite m'inscrire en temps que particulier.");
    private JRadioButton radioCompany = new JRadioButton("Je souhaite m'inscrire en temps qu'entreprise.");
    private ButtonGroup buttonGroup = new ButtonGroup();


    private JPanel inscrFormPanelMain = new JPanel();

    private Connection connection;


    public InscrPage(MainJFrame main, ShopPage shop, InscrConnecPage inscrConnecPage) throws SQLException {
        this.inscrConnecPage = inscrConnecPage;
        this.shop = shop;
        this.mainJFrame = main;
        elementMissingInscr.setVisible(false);

        this.connection = Mysql.openConnection();


        this.setLayout(new BorderLayout());


        // Initialisation :
        this.inscrMainPanel.setLayout(gridBagLayout);
        this.inscrFormPanel1.setLayout(gridBagLayout);
        this.inscrFormPanel2.setLayout(gridBagLayout);
        this.inscrFormPanelMain.setLayout(gridBagLayout);
        this.topPanelInscr.setLayout(gridBagLayout);
        this.inscrMainPanel.setBackground(Color.WHITE);


        // Initialisation des contraintes :
        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.anchor = GridBagConstraints.EAST;
        this.constraints.weightx = 1.0;
        this.constraints.weighty = 1.0;



        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel3.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel3.setForeground(Color.MAGENTA); // Couleur du texte
        this.legendaryMotorsportLabel3.setFont(font1);
        this.legendaryMotorsportPanel3.add(this.legendaryMotorsportLabel3);
        this.legendaryMotorsportPanel3.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints2.gridx = 0;
        this.constraints2.gridy = 0;
        this.constraints2.anchor = GridBagConstraints.NORTHWEST;
        this.constraints4.anchor = GridBagConstraints.NORTHWEST;


        backToMainMenu2.addMouseListener(this);
        this.topPanelInscr.add(backToMainMenu2, constraints2);
        this.constraints2.gridy = 1;
        this.constraints2.anchor = GridBagConstraints.CENTER;
        this.topPanelInscr.add(legendaryMotorsportPanel3, constraints2);




        this.inscrFormPanel1.setVisible(false);
        this.inscrFormPanel2.setVisible(false);

        buttonGroup.add(this.radioClient);
        buttonGroup.add(this.radioCompany);
        this.constraints6.gridx = 0;
        this.constraints6.gridy = 0;
        this.radioClient.setActionCommand("RADIO BUTTON 1");
        this.radioClient.addActionListener(this);
        this.inscrFormPanelMain.add(radioClient, constraints6);
        this.radioCompany.setActionCommand("RADIO BUTTON 2");
        this.radioCompany.addActionListener(this);
        this.constraints6.gridx = 1;
        this.inscrFormPanelMain.add(radioCompany, constraints6);


        //////////// AJOUTER LES CONTRAINTES/METTRE UNE LAYOUT POUR LE inscrFormPanel :
        this.constraints4.gridx=0;
        this.constraints4.gridy=0;
        this.constraints4.anchor = GridBagConstraints.CENTER;
        this.constraints4.weightx = 1.0;
        this.constraints4.weighty = 1.0;


        this.firstNameInscrTF.setColumns(20);
        this.lastNameInscrTF.setColumns(20);
        this.emailInscrTF.setColumns(20);
        this.passwordInscrTF.setColumns(20);



        this.inscrFormPanel1.add(this.headerInscrForm, this.constraints4);
        this.constraints4.gridy=1;
        this.inscrFormPanel1.add(this.firstNameInscrLabel, this.constraints4);
        this.constraints4.gridy=2;
        this.inscrFormPanel1.add(this.firstNameInscrTF, this.constraints4);
        this.constraints4.gridy=3;
        this.inscrFormPanel1.add(this.lastNameInscrLabel, this.constraints4);
        this.constraints4.gridy=4;
        this.inscrFormPanel1.add(this.lastNameInscrTF, this.constraints4);
        this.constraints4.gridy=5;
        this.inscrFormPanel1.add(this.emailInscrLabel, this.constraints4);
        this.constraints4.gridy=6;
        this.inscrFormPanel1.add(this.emailInscrTF, this.constraints4);
        this.constraints4.gridy=7;
        this.inscrFormPanel1.add(this.passwordInscrLabel, this.constraints4);
        this.constraints4.gridy=8;
        this.inscrFormPanel1.add(this.passwordInscrTF, this.constraints4);




        //////////////////////////////TRUC DE DROITE : ///////////////////////////////////
        this.constraints5.gridx=0;
        this.constraints5.gridy=0;
        this.constraints5.anchor = GridBagConstraints.CENTER;
        this.constraints5.weightx = 1.0;
        this.constraints5.weighty = 1.0;


        this.nameCompanyTF.setColumns(20);
        this.siretCompanyTF.setColumns(20);
        this.emailCompanyTF.setColumns(20);
        this.passwordCompanyTF.setColumns(20);

        this.inscrFormPanel2.add(this.nameCompanyLabel, this.constraints5);
        this.constraints5.gridy=1;
        this.inscrFormPanel2.add(this.nameCompanyTF, this.constraints5);
        this.constraints5.gridy=2;
        this.inscrFormPanel2.add(this.siretCompanyLabel, this.constraints5);
        this.constraints5.gridy=3;
        this.inscrFormPanel2.add(this.siretCompanyTF, this.constraints5);
        this.constraints5.gridy=4;
        this.inscrFormPanel2.add(this.emailCompanyLabel, this.constraints5);
        this.constraints5.gridy=5;
        this.inscrFormPanel2.add(this.emailCompanyTF, this.constraints5);
        this.constraints5.gridy=6;
        this.inscrFormPanel2.add(this.passwordCompanyLabel, this.constraints5);
        this.constraints5.gridy=7;
        this.inscrFormPanel2.add(this.passwordCompanyTF, this.constraints5);
        this.constraints5.gridy=8;



        this.constraints6.gridx = 0;
        this.constraints6.gridy = 1;

        this.inscrFormPanelMain.add(inscrFormPanel1, constraints6);
        this.constraints6.gridx = 1;
        this.inscrFormPanelMain.add(inscrFormPanel2, constraints6);


        this.constraints6.gridx = 0;
        this.constraints6.gridwidth = 2;
        this.constraints6.gridy = 2;
        this.inscrFormPanelMain.add(this.alreadyMember, this.constraints6);
        this.constraints6.gridy = 3;

        this.signUpButton1.setActionCommand("PAGE S INSCRIRE");
        this.signUpButton1.addActionListener(this);
        this.inscrFormPanelMain.add(signUpButton1, this.constraints6);
        this.constraints6.gridy = 4;
        this.inscrFormPanelMain.add(this.elementMissingInscr, this.constraints6);





        this.constraints3.gridx=0;
        this.constraints3.gridy=0;
        this.constraints3.anchor = GridBagConstraints.CENTER;
        this.constraints3.weightx = 1.0;
        this.constraints3.weighty = 1.0;

        this.inscrMainPanel.add(topPanelInscr, this.constraints3);
        this.constraints3.gridy=1;
        this.inscrMainPanel.add(inscrFormPanelMain, this.constraints3);


        this.add(inscrMainPanel, BorderLayout.CENTER);

        resetMainContent();
    }

    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();

        // Réinitialisez le contenu principal ici, par exemple :
        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    public void displayRadioButton(int thisRadio){
        if (thisRadio == 0){
            this.inscrFormPanel1.setVisible(true);
            this.inscrFormPanel2.setVisible(false);
        } else {
            this.inscrFormPanel1.setVisible(false);
            this.inscrFormPanel2.setVisible(true);
        }
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
            case "CONNECTION":
                this.mainJFrame.getConnecPage().resetMainContent();
                break;
            case "RADIO BUTTON 1":
                displayRadioButton(0);
                break;
            case "RADIO BUTTON 2":
                displayRadioButton(1);
                break;
            case "PAGE S INSCRIRE" :
                if (inscrFormPanel1.isVisible()) {
                    char[] passwordF2 = this.passwordInscrTF.getPassword();
                    String password2 = new String(passwordF2);
                    String firstName = this.firstNameInscrTF.getText();
                    String lastName = this.lastNameInscrTF.getText();
                    String email2 = this.emailInscrTF.getText();
                    if (password2.isEmpty() || this.firstNameInscrTF.getText().isEmpty() || this.lastNameInscrTF.getText().isEmpty() || this.emailInscrTF.getText().isEmpty()){
                        elementMissingInscr.setVisible(true);
                    } else {
                        this.mainJFrame.setConnected(true);
                        this.mainJFrame.setName(lastName);
                        this.mainJFrame.setFirstName(firstName);
                        this.mainJFrame.setEmail(email2);
                        this.mainJFrame.setPassword(password2);
                        this.shop.resetMainContent();

                        try{

                            UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                            Utilisateur utilisateur = new Utilisateur(-1, password2, email2);
                            Client client = new Client(-1, null, null, lastName, firstName, null);

                            utilisateurDao.addClient(client, utilisateur);

                            System.out.println("Utilisateur ajouté avec succès !");

                        } catch (SQLException er){
                            er.printStackTrace();
                        }
                    }
                } else if (inscrFormPanel2.isVisible()){
                    char[] passwordF2 = this.passwordCompanyTF.getPassword();
                    String password2 = new String(passwordF2);
                    String name = this.nameCompanyTF.getText();
                    long siret = Long.parseLong(this.siretCompanyTF.getText());
                    String email2 = this.emailCompanyTF.getText();
                    if (this.nameCompanyTF.getText().isEmpty() || this.siretCompanyTF.getText().isEmpty() || this.emailCompanyTF.getText().isEmpty() || password2.isEmpty()){
                        elementMissingInscr.setVisible(true);
                    } else {
                        this.mainJFrame.setConnected(true);
                        this.mainJFrame.setSiret(siret);
                        this.mainJFrame.setCompanyName(name);
                        this.mainJFrame.setEmail(email2);
                        this.mainJFrame.setPassword(password2);
                        this.shop.resetMainContent();

                        try{

                            UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                            Utilisateur utilisateur = new Utilisateur(-1, password2, email2);
                            Entreprise entreprise = new Entreprise(-1, null, null, name, siret);

                            utilisateurDao.addEntreprise(entreprise, utilisateur);

                            this.mainJFrame.setUtilisateurDao(utilisateurDao);
                            this.mainJFrame.setEntreprise(entreprise);


                            System.out.println("Utilisateur ajouté avec succès !");

                        } catch (SQLException er){
                            er.printStackTrace();
                        }
                    }

                }
                break;
        }
    }
}
