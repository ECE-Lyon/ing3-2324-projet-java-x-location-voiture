package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopularityPage extends JPanel implements ActionListener, MouseListener {
    private MainJFrame mainJFrame;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsMain = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();





    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font fontTop = new Font("Arial", Font.PLAIN, fontSizeLM);

    private JLabel backToMainMenu = new JLabel("RETOUR");


    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel topButtons = new JPanel();
    private JPanel botPanel = new JPanel();



    public PopularityPage(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
        this.setLayout(new BorderLayout());

        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);



        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(fontTop);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsTop.gridx = 0;
        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.NORTHWEST;

        ////////////////////////////////
        //////////  Ajouter Boutons !!!!
        ////////////////////////////////
        this.constraintsTop.gridx = 0;
        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.NORTHWEST;

        backToMainMenu.addMouseListener(this);

        this.topPanel.add(backToMainMenu, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;


        ////////////////////////////////


        this.constraintsTop.gridy = 1;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;
        this.topPanel.add(topButtons, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.topPanel.setMaximumSize(new Dimension(windowSizeWidth / 5, windowSizeHeight / 10));
        this.topPanel.add(legendaryMotorsportPanel, constraintsTop);











        constraintsMain.gridx = 0;
        constraintsMain.gridy = 0;
        constraintsMain.anchor = GridBagConstraints.CENTER;
        this.mainPanel.add(topPanel, constraintsMain);
        constraintsMain.gridy = 1;
        this.mainPanel.add(botPanel, constraintsMain);
        add(this.mainPanel, BorderLayout.CENTER);
        resetMainContent();
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
            case "INSCRIPTION":
                //inscrPage.resetMainContent();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel labelClic = (JLabel) e.getSource();
        String text = labelClic.getText();
        switch (text){
            case "RETOUR":
                this.mainJFrame.getEmployeeMainPage().resetMainContent();
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
        }    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            label.setForeground(UIManager.getColor("Label.foreground"));
            Font font = label.getFont();
            label.setFont(font.deriveFont(12f));
        }
    }
}
