package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;
import java.awt.image.BufferedImage;

public class MainJFrame extends JFrame implements WindowListener, ComponentListener, ActionListener, MouseListener {

    @Serial
    private static final long serialVersionUID = 1L;


    JFrame frame = new JFrame("Window");

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsMainConnecInsc = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnectionInscription = new GridBagConstraints();
    private final GridBagConstraints constraintsMainConnec = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnec = new GridBagConstraints();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final GridBagConstraints constraints2 = new GridBagConstraints();
    private final GridBagConstraints constraints3 = new GridBagConstraints();
    private final GridBagConstraints constraints4 = new GridBagConstraints();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();
    private final GridBagConstraints constraints7 = new GridBagConstraints();
    private final GridBagConstraints constraints8 = new GridBagConstraints();
    private final GridBagConstraints constraints9 = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();



    private JPanel panelContainer = new JPanel();
    private JPanel inscrConnecMainPanel = new JPanel();
    private JPanel inscrMainPanel = new JPanel();
    private JPanel connecMainPanel = new JPanel();
    //private JPanel rentMainPanel = new JPanel();

    private JPanel topPanelInscr = new JPanel();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
    private JLabel legendaryMotorsportLabel2 = new JLabel("LEGENDARY MOTORSPORT");
    private JLabel legendaryMotorsportLabel3 = new JLabel("LEGENDARY MOTORSPORT");
    private JLabel legendaryMotorsportLabel4 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();
    private JPanel legendaryMotorsportPanel2 = new JPanel();
    private JPanel legendaryMotorsportPanel3 = new JPanel();
    private JPanel legendaryMotorsportPanel4 = new JPanel();
    private JPanel connexionPanel = new JPanel();
    private JPanel inscriptionPanel = new JPanel();
    private JPanel connexionInscriptionBigPanel = new JPanel();

    private JButton buttonConnexion = new JButton("Connexion");
    private JButton buttonInscription = new JButton("Inscription");

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);
    private Dimension dimensionConnexionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);
    private Dimension dimensionInscriptionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private int fontSizeConnexion = 24;
    private int getFontSizeInscription = 24;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);
    private Font font2 = new Font("Arial", Font.PLAIN, fontSizeConnexion);
    private Font font3 = new Font("Arial", Font.PLAIN, getFontSizeInscription);





    private JPanel usernamePWPanel = new JPanel();
    private JLabel backToMainMenu = new JLabel("RETOUR");
    private JLabel backToMainMenu2 = new JLabel("RETOUR");
    private JLabel sInscrire = new JLabel("S'inscrire !");
    private JPanel topPanelConnec = new JPanel();

    private JLabel displayUsernameField = new JLabel("Adresse E-mail : ");
    private JLabel displayPasswordField = new JLabel("Mot de passe : ");

    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton connectionButton = new JButton("Connection");


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
    private JTextField passwordInscrTF = new JTextField();
    private JButton signUpButton = new JButton("S'inscrire");


    /////////////////////////// LE SHOP ///////////////////////////////
    private int numberOfRentableCars = 50;
    private JScrollBar scrollBarShop = new JScrollBar();
    private JPanel mainPanelShop = new JPanel();
    private JPanel topPanelShop = new JPanel();
    private JPanel botPanelShop = new JPanel();
    private JPanel topAndBotPanelShop = new JPanel();
    private JPanel[] rentableCarsPanelShop = new JPanel[numberOfRentableCars];
    private ImageIcon[] imagesCarsShop = new ImageIcon[numberOfRentableCars];
    private JLabel[] imagesCarsLabelShop = new JLabel[numberOfRentableCars];
    private JLabel[] descriptionShop = new JLabel[numberOfRentableCars];
    private final GridBagConstraints mainPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints topPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints botPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints rentableCarsPanelConstraints = new GridBagConstraints();
    private JButton connexionButtonShop = new JButton("Identifiez-vous !");




    CardLayout cardLayout = new CardLayout();

    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.addComponentListener(this);
        //frame.addMouseListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());

        this.windowSizeWidth = GlobalVariable.getScreenWidth();
        this.windowSizeHeight = GlobalVariable.getScreenHeight();



        ////////////////////////////////////////// INITIALISATION DES TABLEAUX /////////////////////////////////////////
        int w, h;
        double wh;
        String fileName = "";

        for (int i = 0; i < numberOfRentableCars-1; i++) {
            fileName = "src/images/" + imagesName(i);
            imagesCarsShop[i] = new ImageIcon(fileName);
        }
        imagesCarsShop[numberOfRentableCars-1] = new ImageIcon("C:/ECE/2023-2024/2nd semestre/info/codes/ing3-2324-projet-java-x-location-voiture/src/images/lambo.png");

        for(int i = 0; i < numberOfRentableCars; i++) {
            rentableCarsPanelShop[i] = new JPanel();
            w = imagesCarsShop[i].getIconWidth();
            h = imagesCarsShop[i].getIconHeight();
            wh = (double) w/h;
            imagesCarsShop[i] = new ImageIcon(imagesCarsShop[i].getImage().getScaledInstance(100, (int)(100/wh), Image.SCALE_SMOOTH));
            imagesCarsLabelShop[i] = new JLabel(imagesCarsShop[i]);
            descriptionShop[i] = new JLabel("Description");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////





        panelContainer.setLayout(cardLayout);
        elementMissingInscr.setVisible(false);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////// PREMIERE PAGE A ETRE AFFICHEE : CONNEXION / INSCRIPTION  ///////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.inscrConnecMainPanel.setLayout(gridBagLayout);
        this.inscrConnecMainPanel.setBackground(Color.WHITE);
        this.connexionInscriptionBigPanel.setLayout(gridBagLayout);

        this.legendaryMotorsportLabel.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA); // Couleur du texte
        this.buttonConnexion.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonConnexion.setForeground(Color.MAGENTA); // Couleur du texte
        this.buttonInscription.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonInscription.setForeground(Color.MAGENTA); // Couleur du texte


        // Appliquer la police au JLabel
        legendaryMotorsportLabel.setFont(font1);
        buttonConnexion.setFont(font2);
        buttonInscription.setFont(font3);

        legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);


        buttonConnexion.setActionCommand("CONNECTION");
        buttonConnexion.addActionListener(this);
        connexionPanel.add(this.buttonConnexion);

        buttonInscription.setActionCommand("INSCRIPTION");
        buttonInscription.addActionListener(this);
        inscriptionPanel.add(this.buttonInscription);

        this.constraintsMainConnecInsc.gridx=0;
        this.constraintsMainConnecInsc.gridy=0;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.CENTER;
        this.constraintsMainConnecInsc.weightx = 1.0;
        this.constraintsMainConnecInsc.weighty = 1.5;

        legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);
        connexionPanel.setPreferredSize(dimensionConnexionPanel);
        inscriptionPanel.setPreferredSize(dimensionInscriptionPanel);
        inscrConnecMainPanel.add(this.legendaryMotorsportPanel, this.constraintsMainConnecInsc);


        this.constraintsInnerConnectionInscription.gridx=0;
        this.constraintsInnerConnectionInscription.gridy=0;
        this.constraintsInnerConnectionInscription.anchor = GridBagConstraints.CENTER;
        this.constraintsInnerConnectionInscription.weightx = 1.0;
        this.constraintsInnerConnectionInscription.weighty = 1.0;
        this.connexionInscriptionBigPanel.add(this.connexionPanel, this.constraintsInnerConnectionInscription);
        this.constraintsInnerConnectionInscription.gridy=1;
        this.connexionInscriptionBigPanel.add(this.inscriptionPanel, this.constraintsInnerConnectionInscription);

        this.constraintsMainConnecInsc.gridy=1;
        this.constraintsMainConnecInsc.weighty = 4.5;

        inscrConnecMainPanel.add(this.connexionInscriptionBigPanel, this.constraintsMainConnecInsc);


        panelContainer.add(inscrConnecMainPanel);



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
        this.connectionButton.setActionCommand("PAGE DE LOCATION");
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



        panelContainer.add(connecMainPanel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////// TROISIEME PAGE A ETRE AFFICHEE : INSCRIPTION  ////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        this.signUpButton.setActionCommand("PAGE DE LOCATION");
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








        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////  QUATRIEME PAGE ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //// au top faut mettre le top


/*
        private JScrollBar scrollBarShop = new JScrollBar();
        private JPanel mainPanelShop = new JPanel();
        private JPanel topPanelShop = new JPanel();
        private JPanel botPanelShop = new JPanel();
        private JPanel topAndBotPanelShop = new JPanel();
        private JPanel descImgPanelShop = new JPanel();
        private JPanel[] rentableCarsPanelShop = new JPanel[numberOfRentableCars];
        private ImageIcon[] imagesCarsShop = new ImageIcon[10];
        private JLabel[] descriptionShop = new JLabel[10];
        private final GridBagConstraints mainPanelShopConstraints = new GridBagConstraints();
        private final GridBagConstraints topPanelShopConstraints = new GridBagConstraints();
        private final GridBagConstraints botPanelShopConstraints = new GridBagConstraints();
        private final GridBagConstraints rentableCarsPanelConstraints = new GridBagConstraints();

*/
        this.mainPanelShop.setLayout(new BorderLayout());
        this.topPanelShop.setLayout(gridBagLayout);
        this.botPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setBackground(Color.red);


        for (int i = 0; i < numberOfRentableCars; i++){
            this.rentableCarsPanelShop[i].setLayout(gridBagLayout);
            rentableCarsPanelShop[i].setBackground(Color.BLUE);
        }


        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel4.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel4.setForeground(Color.MAGENTA); // Couleur du texte
        this.legendaryMotorsportLabel4.setFont(font1);
        this.legendaryMotorsportPanel4.add(this.legendaryMotorsportLabel4);
        this.legendaryMotorsportPanel4.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints5.gridx = 0;
        this.constraints5.gridy = 0;
        this.constraints5.anchor = GridBagConstraints.NORTHWEST;
        this.constraints6.anchor = GridBagConstraints.NORTHEAST;


        connexionButtonShop.addMouseListener(this);
        this.topPanelShop.add(connexionButtonShop, constraints6);
        this.constraints5.gridy = 1;
        this.constraints5.anchor = GridBagConstraints.CENTER;
        this.topPanelShop.add(legendaryMotorsportPanel4, constraints5);




        /////////////////////////////////       BAS DE LA PAGE      ///////////////////////////////////////
        //// Faut ajouter une description a chaque truc
        int k = 0;
        this.constraints7.gridy = 0;
        constraints7.anchor = GridBagConstraints.CENTER;
        constraints7.fill = GridBagConstraints.BOTH;
        //constraints9.fill = GridBagConstraints.BOTH;
        constraints9.gridx = 0;
        constraints9.gridy = 0;
        constraints9.anchor = GridBagConstraints.CENTER;
        constraints9.fill = GridBagConstraints.BOTH;
        for(int i = 0; i < (numberOfRentableCars + 3-numberOfRentableCars%3)/3; i++) {
            this.constraints7.gridy = i;
            constraints9.gridy = i;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints9.gridx = i;
                constraints7.gridx = j;
                rentableCarsPanelShop[k].add(imagesCarsLabelShop[k], constraints9);
                //botPanelShop.add(rentableCarsPanelShop[k], constraints7);
                k++;
                System.out.println(k);

            }

        }
        k=0;
        for (int i = 0; i < (numberOfRentableCars + 3-numberOfRentableCars%3)/3; i++) {
            this.constraints7.gridy = i;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints7.gridx = j;
                botPanelShop.add(rentableCarsPanelShop[k], constraints7);
                k++;
            }
        }



        this.constraints6.gridx = 0;
        this.constraints6.gridy = 0;
        this.constraints6.anchor = GridBagConstraints.CENTER;
        constraints6.fill = GridBagConstraints.BOTH;

        this.topAndBotPanelShop.add(this.topPanelShop, this.constraints6);
        this.constraints6.gridy = 1;
        topAndBotPanelShop.setMaximumSize(new Dimension(1000, 800));
        this.topAndBotPanelShop.add(this.botPanelShop, this.constraints6);



        this.constraints8.gridx = 0;
        this.constraints8.gridy = 0;
        this.constraints8.anchor = GridBagConstraints.CENTER;
        this.constraints8.weightx = 25;
        constraints8.fill = GridBagConstraints.BOTH;

        JScrollPane scrollPane = new JScrollPane(topAndBotPanelShop, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       // scrollPane.setPreferredSize(new Dimension(800, 600));
        //this.mainPanelShop.add(this.topAndBotPanelShop, this.constraints8);


        //this.constraints8.anchor = GridBagConstraints.EAST;
        this.constraints8.gridx = 1;
        this.constraints8.weightx = 2;

        //this.mainPanelShop.add(scrollPane, this.constraints8);
        this.mainPanelShop.add(scrollPane, BorderLayout.CENTER);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////// Ajout de toutes les pages au cardLayout  ///////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panelContainer.add(inscrConnecMainPanel, "INSCRIPTION/CONNECTION");
        panelContainer.add(connecMainPanel, "CONNECTION");
        panelContainer.add(inscrMainPanel, "INSCRIPTION");
        panelContainer.add(mainPanelShop, "PAGE DE LOCATION");
        cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");





        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////// LIGNES FINALES  ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public String imagesName(int i) {
        String s = "Picture1.png";
        return s;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
        System.out.println("The window has been destroy successfully.");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////AJOUTER LA MOFIFICATION DE LA TAILLE DE LA POLICE SI ON A LE TEMPS ET LA DETER//////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //this.legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);



        this.windowSizeHeight = this.frame.getSize().height;
        this.windowSizeWidth = this.frame.getSize().width;
        this.dimensionLegendaryMotorsportPanel = new Dimension(this.windowSizeWidth / 3, this.windowSizeHeight / 10);
        this.dimensionConnexionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);
        this.dimensionInscriptionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);

        this.legendaryMotorsportPanel.setPreferredSize(this.dimensionLegendaryMotorsportPanel);
        this.connexionPanel.setPreferredSize(this.dimensionConnexionPanel);
        this.inscriptionPanel.setPreferredSize(this.dimensionInscriptionPanel);
        this.inscrConnecMainPanel.revalidate();
        this.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel labelClic = (JLabel) e.getSource();
        String text = labelClic.getText();
        switch (text){
            case "RETOUR":
                cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");
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
            case "INSCRIPTION/CONNECTION" -> cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");
            case "CONNECTION" -> cardLayout.show(panelContainer, "CONNECTION");
            case "INSCRIPTION" -> cardLayout.show(panelContainer, "INSCRIPTION");
            case "SIGN UP" -> cardLayout.show(panelContainer, "SIGN UP");
            case "PAGE DE LOCATION" -> cardLayout.show(panelContainer, "PAGE DE LOCATION");
        }
    }
}
