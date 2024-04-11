package codes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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


    private JLabel test1 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel panel = new JPanel();
    private JPanel panelTest1 = new JPanel();
    private JPanel panelTest2 = new JPanel();
    private JPanel panelTest3 = new JPanel();

    private JButton buttonConnexion = new JButton("Connexion");
    private JButton buttonInscription = new JButton("Inscription");

    private int windowSizeWidth;
    private int windowSizeHeight;

    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());

        this.windowSizeWidth = GlobalVariable.getScreenWidth();
        this.windowSizeHeight = GlobalVariable.getScreenHeight();

        this.panel.setLayout(gridBagLayout);
        this.panel.setBackground(Color.WHITE);

        this.test1.setOpaque(true); // Permet de définir la couleur de fond
        this.test1.setBackground(Color.BLUE); // Couleur de fond du carré
        this.test1.setForeground(Color.WHITE); // Couleur du texte
        this.buttonConnexion.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonConnexion.setBackground(Color.BLUE); // Couleur de fond du carré
        this.buttonConnexion.setForeground(Color.WHITE); // Couleur du texte
        this.buttonInscription.setOpaque(true); // Permet de définir la couleur de fond
        this.buttonInscription.setBackground(Color.BLUE); // Couleur de fond du carré
        this.buttonInscription.setForeground(Color.WHITE); // Couleur du texte

        Font font = new Font("Arial", Font.PLAIN, 26);

        // Appliquer la police au JLabel
        test1.setFont(font);
        buttonConnexion.setFont(font);
        buttonInscription.setFont(font);

        panelTest1.add(this.test1);
        panelTest2.add(this.buttonConnexion);
        panelTest3.add(this.buttonInscription);

        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.constraints.weightx = 1.0;
        this.constraints.weighty = 1.0;

        panelTest1.setPreferredSize(new Dimension(windowSizeWidth/3, windowSizeHeight/10));
        panelTest2.setPreferredSize(new Dimension(windowSizeWidth/8, windowSizeHeight/10));
        panelTest3.setPreferredSize(new Dimension(windowSizeWidth/8, windowSizeHeight/10));

        panel.add(this.panelTest1, this.constraints);
        this.constraints.gridy=1;
        panel.add(panelTest2, this.constraints);
        this.constraints.gridy=2;
        panel.add(panelTest3, this.constraints);


        frame.add(panel);

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
