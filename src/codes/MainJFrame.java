package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

public class MainJFrame extends JFrame implements WindowListener, ComponentListener, MouseListener, ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;


    JFrame frame = new JFrame("Window");

    private final GridBagLayout mainGridBagLayoutConnectionIscriptionMenu = new GridBagLayout();
    private final GridBagLayout innerGridBagLayoutConnectionInscription = new GridBagLayout();
    private final GridBagLayout mainGridBagLayoutConnectionMenu = new GridBagLayout();
    private final GridBagLayout innerGridBagLayoutConnection = new GridBagLayout();
    private final BorderLayout borderLayout = new BorderLayout();
    private final GridBagConstraints constraintsMainConnecInsc = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnectionInscription = new GridBagConstraints();
    private final GridBagConstraints constraintsMainConnec = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnec = new GridBagConstraints();


    private JPanel panelContainer = new JPanel();
    private JPanel inscrConnecMainPanel = new JPanel();
    private JPanel inscrMainPanel = new JPanel();
    private JPanel connecMainPanel = new JPanel();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
    private JLabel legendaryMotorsportLabel2 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();
    private JPanel legendaryMotorsportPanel2 = new JPanel();
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
    private JLabel sInscrire = new JLabel("S'inscrire !");
    private JPanel topPanelConnec = new JPanel();

    private JLabel displayUsernameField = new JLabel("Adresse E-mail : ");
    private JLabel displayPasswordField = new JLabel("Mot de passe : ");

    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton connectionButton = new JButton("Connection");






    CardLayout cardLayout = new CardLayout();

    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());

        this.windowSizeWidth = GlobalVariable.getScreenWidth();
        this.windowSizeHeight = GlobalVariable.getScreenHeight();


        panelContainer.setLayout(cardLayout);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////// PREMIERE PAGE A ETRE AFFICHEE : CONNEXION / INSCRIPTION  ///////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.inscrConnecMainPanel.setLayout(mainGridBagLayoutConnectionIscriptionMenu);
        this.inscrConnecMainPanel.setBackground(Color.WHITE);
        this.connexionInscriptionBigPanel.setLayout(innerGridBagLayoutConnectionInscription);

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
        this.connecMainPanel.setLayout(mainGridBagLayoutConnectionIscriptionMenu);
        this.usernamePWPanel.setLayout(mainGridBagLayoutConnectionMenu);
        this.topPanelConnec.setLayout(innerGridBagLayoutConnection);
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
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.EAST;
        this.constraintsMainConnecInsc.gridx = 0;
        this.constraintsMainConnecInsc.gridy = 1;
        this.usernamePWPanel.add(this.displayPasswordField, this.constraintsMainConnecInsc);
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.WEST;
        this.constraintsMainConnecInsc.gridx = 1;
        this.passwordField.setColumns(20);
        this.usernamePWPanel.add(this.passwordField, this.constraintsMainConnecInsc);
        this.constraintsMainConnecInsc.gridx = 1;
        this.constraintsMainConnecInsc.gridy = 2;
        this.constraintsMainConnecInsc.anchor = GridBagConstraints.WEST;
        this.usernamePWPanel.add(this.sInscrire, this.constraintsMainConnecInsc);




        this.legendaryMotorsportLabel2.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel2.setForeground(Color.MAGENTA); // Couleur du texte
        this.legendaryMotorsportLabel2.setFont(font1);
        this.legendaryMotorsportPanel2.add(this.legendaryMotorsportLabel2);
        this.legendaryMotorsportPanel2.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsInnerConnec.gridx = 0;
        this.constraintsInnerConnec.gridy = 0;
        this.constraintsInnerConnec.anchor = GridBagConstraints.NORTHWEST;



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









        panelContainer.add(inscrMainPanel);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////// Ajout de toutes les pages au cardLayout  ///////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////






        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////// LIGNES FINALES  ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panelContainer.add(inscrConnecMainPanel, "INSCRIPTION/CONNECTION");
        panelContainer.add(connecMainPanel, "CONNECTION");
        panelContainer.add(inscrMainPanel, "INSCRIPTION");
        cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "INSCRIPTION/CONNECTION" -> cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");
            case "CONNECTION" -> cardLayout.show(panelContainer, "CONNECTION");
            case "INSCRIPTION" -> cardLayout.show(panelContainer, "INSCRIPTION");
            case "page4" -> cardLayout.show(panelContainer, "page4");
        }
    }
}
