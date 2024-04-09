package codes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

public class MainJFrame extends JFrame implements WindowListener {

    @Serial
    private static final long serialVersionUID = 1L;


    JFrame frame = new JFrame("Window");

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();


    private JLabel test1 = new JLabel("test1");
    private JLabel test2 = new JLabel("test2");
    private JLabel test3 = new JLabel("test3");
    private JLabel test4 = new JLabel("test4");



    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.setSize(800, 500);



        // Création d'un JPanel pour contenir les carrés
        JPanel panel = new JPanel();
        panel.setLayout(gridBagLayout);
        panel.setBackground(Color.WHITE);

        this.test1.setOpaque(true); // Permet de définir la couleur de fond
        this.test1.setBackground(Color.BLUE); // Couleur de fond du carré
        this.test1.setForeground(Color.WHITE); // Couleur du texte
        this.test2.setOpaque(true); // Permet de définir la couleur de fond
        this.test2.setBackground(Color.BLUE); // Couleur de fond du carré
        this.test2.setForeground(Color.WHITE); // Couleur du texte
        this.test3.setOpaque(true); // Permet de définir la couleur de fond
        this.test3.setBackground(Color.BLUE); // Couleur de fond du carré
        this.test3.setForeground(Color.WHITE); // Couleur du texte
        this.test4.setOpaque(true); // Permet de définir la couleur de fond
        this.test4.setBackground(Color.BLUE); // Couleur de fond du carré
        this.test4.setForeground(Color.WHITE); // Couleur du texte


        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.anchor = GridBagConstraints.CENTER;
        //this.constraints.weightx = 1.0;
        //this.constraints.weighty = 1.0;
        this.constraints.insets = new Insets(0, 0, 0, 0);

        panel.add(this.test1, this.constraints);
        this.constraints.gridx=0;
        this.constraints.gridy=1;
        panel.add(test2, this.constraints);
        this.constraints.gridy=2;
        panel.add(test3, this.constraints);
        this.constraints.gridy=3;
        panel.add(test4, this.constraints);


        // Ajout du JPanel à la fenêtre
        frame.add(panel);

        // Affichage de la fenêtre
        frame.setVisible(true);
    }

    // Méthode utilitaire pour créer un carré avec du texte
    private static JLabel addSquare(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true); // Permet de définir la couleur de fond
        label.setBackground(Color.BLUE); // Couleur de fond du carré
        label.setForeground(Color.WHITE); // Couleur du texte

        // Ajout d'une bordure vide pour créer un espace autour du carré
        label.setBorder(new EmptyBorder(1, 1, 1, 1)); // padding de 10 pixels de chaque côté

        return label;
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
}
