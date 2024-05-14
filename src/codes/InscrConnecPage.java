package codes;

import codes.dao.Mysql;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

public class InscrConnecPage extends JPanel implements ActionListener, MouseListener {


    private MainJFrame mainJFrame;
    private ShopPage shop;

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsMainConnecInsc = new GridBagConstraints();
    private final GridBagConstraints constraintsInnerConnectionInscription = new GridBagConstraints();
    private JPanel inscrConnecMainPanel = new JPanel();




    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();
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

    private Connection connection;


    public InscrConnecPage(MainJFrame main, ShopPage shop) throws SQLException {
        this.mainJFrame = main;
        this.shop = shop;

        this.connection = Mysql.openConnection();



        this.setLayout(new BorderLayout());


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
        this.inscrConnecMainPanel.add(this.legendaryMotorsportPanel, this.constraintsMainConnecInsc);


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


        this.add(inscrConnecMainPanel, BorderLayout.CENTER);


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
                //cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");
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
            case "INSCRIPTION":
                this.mainJFrame.getInscrPage().resetMainContent();
                break;
        }
    }
}
