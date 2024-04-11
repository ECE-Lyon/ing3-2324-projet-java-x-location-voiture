package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

public class MainJFrame extends JFrame implements WindowListener, ComponentListener {

    @Serial
    private static final long serialVersionUID = 1L;


    JFrame frame = new JFrame("Window");

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel mainLabel = new JPanel();
    private JPanel legendaryMotorsportPanel = new JPanel();
    private JPanel connexionPanel = new JPanel();
    private JPanel inscriptionPanel = new JPanel();

    private JButton buttonConnexion = new JButton("Connexion");
    private JButton buttonInscription = new JButton("Inscription");

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);
    private Dimension dimensionConnexionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);
    private Dimension dimensionInscriptionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);

    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());

        this.windowSizeWidth = GlobalVariable.getScreenWidth();
        this.windowSizeHeight = GlobalVariable.getScreenHeight();

        this.mainLabel.setLayout(gridBagLayout);
        this.mainLabel.setBackground(Color.WHITE);

        this.legendaryMotorsportLabel.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel.setBackground(Color.BLUE); // Couleur de fond du carré
        this.legendaryMotorsportLabel.setForeground(Color.WHITE); // Couleur du texte
        this.buttonConnexion.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonConnexion.setBackground(Color.BLUE); // Couleur de fond du carré
        this.buttonConnexion.setForeground(Color.WHITE); // Couleur du texte
        this.buttonInscription.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonInscription.setBackground(Color.BLUE); // Couleur de fond du carré
        this.buttonInscription.setForeground(Color.WHITE); // Couleur du texte

        Font font = new Font("Arial", Font.PLAIN, 26);

        // Appliquer la police au JLabel
        legendaryMotorsportLabel.setFont(font);
        buttonConnexion.setFont(font);
        buttonInscription.setFont(font);

        legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        connexionPanel.add(this.buttonConnexion);
        inscriptionPanel.add(this.buttonInscription);

        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.constraints.weightx = 1.0;
        this.constraints.weighty = 1.0;

        legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);
        connexionPanel.setPreferredSize(dimensionConnexionPanel);
        inscriptionPanel.setPreferredSize(dimensionInscriptionPanel);

        mainLabel.add(this.legendaryMotorsportPanel, this.constraints);
        this.constraints.gridy=1;
        mainLabel.add(connexionPanel, this.constraints);
        this.constraints.gridy=2;
        mainLabel.add(inscriptionPanel, this.constraints);


        frame.add(mainLabel);

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
        this.windowSizeHeight = this.frame.getSize().height;
        this.windowSizeWidth = this.frame.getSize().width;
        this.dimensionLegendaryMotorsportPanel = new Dimension(this.windowSizeWidth / 3, this.windowSizeHeight / 10);
        this.dimensionConnexionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);
        this.dimensionInscriptionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);

        this.legendaryMotorsportPanel.setPreferredSize(this.dimensionLegendaryMotorsportPanel);
        this.connexionPanel.setPreferredSize(this.dimensionConnexionPanel);
        this.inscriptionPanel.setPreferredSize(this.dimensionInscriptionPanel);
        this.mainLabel.revalidate();
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
}
