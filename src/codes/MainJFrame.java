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

    private JPanel panel = new JPanel();
    private JPanel panelTest1 = new JPanel();
    private JPanel panelTest2 = new JPanel();
    private JPanel panelTest3 = new JPanel();
    private JPanel panelTest4 = new JPanel();



    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());




        this.panel.setLayout(gridBagLayout);
        this.panel.setBackground(Color.WHITE);

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

        panelTest1.add(this.test1);
        panelTest2.add(this.test2);
        panelTest3.add(this.test3);
        panelTest4.add(this.test4);

        this.constraints.gridx=0;
        this.constraints.gridy=0;
        this.constraints.anchor = GridBagConstraints.CENTER;
        //this.constraints.weightx = 1.0;
        //this.constraints.weighty = 1.0;
        this.constraints.insets = new Insets(25, 25, 25, 25);

        panel.add(this.panelTest1, this.constraints);
        this.constraints.gridy=1;
        panel.add(panelTest2, this.constraints);
        this.constraints.gridy=2;
        panel.add(panelTest3, this.constraints);
        this.constraints.gridy=3;
        panel.add(panelTest4, this.constraints);


        // Ajout du JPanel à la fenêtre
        frame.add(panel);

        // Affichage de la fenêtre
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
}
