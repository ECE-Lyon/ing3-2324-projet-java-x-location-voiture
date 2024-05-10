package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
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
    private JPanel inscrFormPanel = new JPanel();
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
    private JButton signUpButton = new JButton("S'inscrire");



    public InscrPage(MainJFrame main, ShopPage shop, InscrConnecPage inscrConnecPage){
        this.inscrConnecPage = inscrConnecPage;
        this.shop = shop;
        this.mainJFrame = main;
        elementMissingInscr.setVisible(false);


        this.setLayout(new BorderLayout());


        // Initialisation :
        this.inscrMainPanel.setLayout(gridBagLayout);
        this.inscrFormPanel.setLayout(gridBagLayout);
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



        this.inscrFormPanel.add(this.headerInscrForm, this.constraints4);
        this.constraints4.gridy=1;
        this.inscrFormPanel.add(this.firstNameInscrLabel, this.constraints4);
        this.constraints4.gridy=2;
        this.inscrFormPanel.add(this.firstNameInscrTF, this.constraints4);
        this.constraints4.gridy=3;
        this.inscrFormPanel.add(this.lastNameInscrLabel, this.constraints4);
        this.constraints4.gridy=4;
        this.inscrFormPanel.add(this.lastNameInscrTF, this.constraints4);
        this.constraints4.gridy=5;
        this.inscrFormPanel.add(this.emailInscrLabel, this.constraints4);
        this.constraints4.gridy=6;
        this.inscrFormPanel.add(this.emailInscrTF, this.constraints4);
        this.constraints4.gridy=7;
        this.inscrFormPanel.add(this.passwordInscrLabel, this.constraints4);
        this.constraints4.gridy=8;
        this.inscrFormPanel.add(this.passwordInscrTF, this.constraints4);
        this.constraints4.gridy=9;
        this.inscrFormPanel.add(this.alreadyMember, this.constraints4);
        this.constraints4.gridy=10;
        this.inscrFormPanel.add(this.signUpButton, this.constraints4);
        this.constraints4.gridy=11;

        ////////////////////////////////////////////METTRE UN IF toutes les conditions sont respectees (tous les champs
        //  Sont remplis, les mdp/identifiants sont correctes)
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.signUpButton.setActionCommand("PAGE S INSCRIRE");
        this.signUpButton.addActionListener(this);
        this.inscrFormPanel.add(signUpButton, this.constraints4);
        this.constraints4.gridy=12;
        this.inscrFormPanel.add(this.elementMissingInscr, this.constraints4);


        this.constraints3.gridx=0;
        this.constraints3.gridy=0;
        this.constraints3.anchor = GridBagConstraints.CENTER;
        this.constraints3.weightx = 1.0;
        this.constraints3.weighty = 1.0;

        this.inscrMainPanel.add(topPanelInscr, this.constraints3);
        this.constraints3.gridy=1;
        this.inscrMainPanel.add(inscrFormPanel, this.constraints3);


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
            case "PAGE S INSCRIRE" :
                char[] passwordF2 = this.passwordInscrTF.getPassword();
                String password2 = new String(passwordF2);
                String firstName = this.firstNameInscrTF.getText();
                String lastName = this.lastNameInscrTF.getText();
                String email2 = this.emailInscrTF.getText();
                if(password2.isEmpty() || this.firstNameInscrTF.getText().isEmpty() || this.lastNameInscrTF.getText().isEmpty() || this.emailInscrTF.getText().isEmpty()){
                    elementMissingInscr.setVisible(true);
                }else {
                    this.mainJFrame.setConnected(true);
                    this.mainJFrame.setName(lastName);
                    this.mainJFrame.setFirstName(firstName);
                    this.mainJFrame.setEmail(email2);
                    this.mainJFrame.setPassword(password2);
                    this.shop.resetMainContent();

                    try(Connection connection = Mysql.openConnection()) {

                        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                        Utilisateur utilisateur = new Utilisateur(-1, password2, email2);
                        Client client = new Client(-1, null, null, lastName, firstName, null);

                        utilisateurDao.addClient(client, utilisateur);

                        System.out.println("Utilisateur ajouté avec succès !");

                    } catch (SQLException er){
                        er.printStackTrace();
                    }

                }
                break;
        }
    }
}
