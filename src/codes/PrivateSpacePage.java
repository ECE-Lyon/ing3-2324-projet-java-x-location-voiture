package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PrivateSpacePage extends JPanel implements ActionListener, MouseListener {

    private InscrPage inscrPage;
    private InscrConnecPage inscrConnecPage;
    private ConnecPage connecPage;
    private ShopPage shopPage;
    private MainJFrame mainJFrame;

    private final JButton backButton = new JButton("Retour");
    private final JButton modify1 = new JButton("Modifier");
    private final JButton modify2 = new JButton("Modifier");
    private final JButton modify3 = new JButton("Modifier");
    private final JButton modify4 = new JButton("Modifier");
    private final JButton validate = new JButton("Valider");
    private JLabel name;
    private JLabel firstName;
    private JLabel email;
    private JLabel password;

    private final JPanel botPanel = new JPanel();
    private final JPanel infoPanel = new JPanel();


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints1 = new GridBagConstraints();
    private final GridBagConstraints constraints2 = new GridBagConstraints();
    private final GridBagConstraints constraints3 = new GridBagConstraints();
    private final GridBagConstraints constraints4 = new GridBagConstraints();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();
    private final GridBagConstraints constraints7 = new GridBagConstraints();
    private final GridBagConstraints constraints8 = new GridBagConstraints();
    private final GridBagConstraints constraints9 = new GridBagConstraints();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);







    /////////////////////////// LE SHOP ///////////////////////////////
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();


    private JDialog dialog1 = new JDialog(mainJFrame);
    private JDialog dialog2 = new JDialog(mainJFrame);
    private JDialog dialog3 = new JDialog(mainJFrame);
    private JDialog dialog4 = new JDialog(mainJFrame);
    private JLabel newNameLabel = new JLabel("Saisir le nouveau nom : ");
    private JLabel newFirstNameLabel = new JLabel("Saisir le nouveau nom : ");
    private JLabel newEmailLabel = new JLabel("Saisir le nouveau nom : ");
    private JLabel newPasswordLabel = new JLabel("Saisir le nouveau nom : ");
    private JTextField newNameTF = new JTextField();
    private JTextField newFirstNameTF = new JTextField();
    private JTextField newEmailTF = new JTextField();
    private JTextField newPasswordTF = new JTextField();
    private JButton validateButton1 = new JButton("Valider");
    private JButton validateButton2 = new JButton("Valider");
    private JButton validateButton3 = new JButton("Valider");
    private JButton validateButton4 = new JButton("Valider");


    public PrivateSpacePage(MainJFrame mainJFrame, ConnecPage connecPage, InscrPage inscrPage, InscrConnecPage inscrConnecPage, ShopPage shopPage){

        this.mainJFrame = mainJFrame;
        this.connecPage = connecPage;
        this.inscrConnecPage = inscrConnecPage;
        this.inscrPage = inscrPage;
        this.shopPage = shopPage;

        this.setLayout(new BorderLayout());

        String text1 = "Nom : " + "sdf";
        String text2 = "Prénom : " + "sdf";
        String text3 = "E-mail : " + "sdf";
        String text4 = "Mot de passe : " + "sdf";
        name = new JLabel(text1);
        firstName = new JLabel(text2);
        email = new JLabel(text3);
        password = new JLabel(text4);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);
        this.infoPanel.setLayout(gridBagLayout);


        ///////////////////////////// LE TOP :  ///////////////////////////////
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(font1);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints1.gridx = 0;
        this.constraints1.gridy = 0;
        this.constraints1.anchor = GridBagConstraints.NORTHWEST;
        this.constraints2.anchor = GridBagConstraints.NORTHEAST;



        backButton.setActionCommand("BACK TO SHOP");
        backButton.addActionListener(this);
        this.topPanel.add(backButton, constraints2);

        this.constraints1.gridy = 1;
        this.constraints1.anchor = GridBagConstraints.CENTER;
        this.topPanel.add(legendaryMotorsportPanel, constraints1);



        this.constraints3.gridx = 0;
        this.constraints3.gridy = 0;
        this.constraints3.anchor = GridBagConstraints.WEST;

        this.infoPanel.add(name, constraints3);
        this.constraints3.gridy = 1;
        this.infoPanel.add(firstName, constraints3);
        this.constraints3.gridy = 2;
        this.infoPanel.add(email, constraints3);
        this.constraints3.gridy = 3;
        this.infoPanel.add(password, constraints3);


        this.constraints3.gridx = 1;
        this.constraints3.gridy = 0;
        this.infoPanel.add(name, constraints3);
        this.constraints3.gridy = 1;
        this.infoPanel.add(firstName, constraints3);
        this.constraints3.gridy = 2;
        this.infoPanel.add(email, constraints3);
        this.constraints3.gridy = 3;
        this.infoPanel.add(password, constraints3);


        this.constraints3.gridx = 2;
        this.constraints3.gridy = 0;
        modify1.setActionCommand("NAME");
        modify1.addActionListener(this);
        this.infoPanel.add(modify1, constraints3);
        this.constraints3.gridy = 1;
        modify2.setActionCommand("FIRSTNAME");
        modify2.addActionListener(this);
        this.infoPanel.add(modify2, constraints3);
        this.constraints3.gridy = 2;
        modify3.setActionCommand("EMAIL");
        modify3.addActionListener(this);
        this.infoPanel.add(modify3, constraints3);
        this.constraints3.gridy = 3;
        modify4.setActionCommand("PASSWORD");
        modify4.addActionListener(this);
        this.infoPanel.add(modify4, constraints3);





        this.constraints4.gridx = 0;
        this.constraints4.gridy = 0;
        this.constraints4.anchor = GridBagConstraints.CENTER;
        this.botPanel.add(this.infoPanel, constraints4);
        this.constraints4.gridy = 1;
        this.botPanel.add(this.validate, constraints4);

        this.constraints5.gridx = 0;
        this.constraints5.gridy = 0;

        this.mainPanel.add(this.topPanel, constraints5);
        this.constraints5.gridy = 1;
        this.mainPanel.add(this.botPanel, constraints5);



        this.add(mainPanel, BorderLayout.CENTER);

        this.resetMainContent();
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
            case "BACK TO SHOP" :
                this.shopPage.resetMainContent();
                break;
            case "NAME" :
                dialog1.setSize(300, 200);
                this.newNameTF.setColumns(20);
                dialog1.setLayout(gridBagLayout);
                constraints6.gridx = 0;
                constraints6.gridy = 0;
                dialog1.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog1.add(newNameLabel, constraints6);
                constraints6.gridy = 1;
                dialog1.add(this.newNameTF, constraints6);
                constraints6.gridy = 2;
                validateButton1.setActionCommand("EXIT DIALOG");
                validateButton1.addActionListener(this);
                dialog1.add(this.validateButton1, constraints6);
                dialog1.setVisible(true);
                break;
            case "FIRSTNAME" :
                dialog2.setSize(300, 200);
                this.newFirstNameTF.setColumns(20);
                dialog2.setLayout(gridBagLayout);
                constraints7.gridx = 0;
                constraints7.gridy = 0;
                dialog2.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog2.add(newFirstNameLabel, constraints7);
                constraints7.gridy = 1;
                dialog2.add(this.newFirstNameTF, constraints7);
                constraints7.gridy = 2;
                validateButton2.setActionCommand("EXIT DIALOG");
                validateButton2.addActionListener(this);
                dialog2.add(this.validateButton2, constraints7);
                dialog2.setVisible(true);
                break;
            case "EMAIL" :
                dialog3.setSize(300, 200);
                this.newEmailTF.setColumns(20);
                dialog3.setLayout(gridBagLayout);
                constraints8.gridx = 0;
                constraints8.gridy = 0;
                dialog3.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog3.add(newEmailLabel, constraints8);
                constraints8.gridy = 1;
                dialog3.add(this.newEmailTF, constraints8);
                constraints8.gridy = 2;
                validateButton3.setActionCommand("EXIT DIALOG");
                validateButton3.addActionListener(this);
                dialog3.add(this.validateButton3, constraints8);
                dialog3.setVisible(true);
                break;
            case "PASSWORD" :
                dialog4.setSize(300, 200);
                this.newPasswordTF.setColumns(20);
                dialog4.setLayout(gridBagLayout);
                constraints9.gridx = 0;
                constraints9.gridy = 0;
                dialog4.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog4.add(newPasswordLabel, constraints9);
                constraints9.gridy = 1;
                dialog4.add(this.newPasswordTF, constraints9);
                constraints9.gridy = 2;
                validateButton4.setActionCommand("EXIT DIALOG");
                validateButton4.addActionListener(this);
                dialog4.add(this.validateButton4, constraints9);
                dialog4.setVisible(true);
                break;
            case "EXIT DIALOG" :
                dialog4.dispose();
                dialog3.dispose();
                dialog2.dispose();
                dialog1.dispose();
                break;
            case "APPLY" :

                try(Connection connection = Mysql.openConnection()){

                    this.mainJFrame.getClient().getStatut();

                    String oldClientEmail = this.mainJFrame.getEmail();
                    String oldClientPassword = this.mainJFrame.getPassword();

                    this.mainJFrame.setUtilisateurDao(new UtilisateurDaoImpl(connection));
                    this.mainJFrame.setUtilisateur(this.mainJFrame.getUtilisateurDao().getUtilisateur(oldClientEmail, oldClientPassword));

                    ////// On récupère les valeurs des trucs

                    String newLastNameClient = this.mainJFrame.getName();
                    String newFirstNameClient = this.mainJFrame.getFirstName();
                    String newPasswordClient = this.mainJFrame.getPassword();
                    String newEmailClient = this.mainJFrame.getEmail();

                    this.mainJFrame.setClient((Client) this.mainJFrame.getUtilisateur());

                    this.mainJFrame.getClient().setNom_client(newLastNameClient);
                    this.mainJFrame.getClient().setPrenom_client(newFirstNameClient);
                    this.mainJFrame.getUtilisateur().setEmail(newEmailClient);
                    this.mainJFrame.getUtilisateur().setMdp(newPasswordClient);

                    this.mainJFrame.getUtilisateurDao().updateClient(this.mainJFrame.getClient(), this.mainJFrame.getUtilisateur(), oldClientEmail, oldClientPassword);

                    this.shopPage.resetMainContent();

                } catch (SQLException er){
                    er.printStackTrace();
                }

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
