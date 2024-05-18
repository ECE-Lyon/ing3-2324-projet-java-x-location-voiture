package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmployeeMainPage extends JPanel implements ActionListener, MouseListener {
    private MainJFrame mainJFrame;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsMain = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();
    private final GridBagConstraints constraintsBot = new GridBagConstraints();





    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font fontTop = new Font("Arial", Font.PLAIN, fontSizeLM);


    private JButton disconnectButton = new JButton("Se déconnecter");
    private JButton toClientInfoPage = new JButton("Voir les informations des clients et des entreprises");
    private JButton toModifyModelPage = new JButton("Voir les informations des véhicules");
    private JButton toPopularityPage = new JButton("Voir les côtes de popularité des véhicules");


    /////////////////////////// LE SHOP ///////////////////////////////
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel topButtons = new JPanel();
    private JPanel botPanel = new JPanel();



    public EmployeeMainPage(MainJFrame mainJFrame) {
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
        this.constraints10.anchor = GridBagConstraints.NORTHWEST;
        disconnectButton.setActionCommand("DISCONNECT");
        disconnectButton.addActionListener(this);
        this.topButtons.add(disconnectButton, constraints10);

        ////////////////////////////////


        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;
        this.topPanel.add(topButtons, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.topPanel.setMaximumSize(new Dimension(windowSizeWidth / 5, windowSizeHeight / 10));
        this.topPanel.add(legendaryMotorsportPanel, constraintsTop);





        constraintsBot.gridx = 0;
        constraintsBot.gridy = 0;
        toPopularityPage.setActionCommand("TO POPULARITY");
        toPopularityPage.addActionListener(this);
        this.botPanel.add(toPopularityPage, constraintsBot);

        constraintsBot.gridx = 1;
        toModifyModelPage.setActionCommand("TO MODIFY MODEL");
        toModifyModelPage.addActionListener(this);
        this.botPanel.add(toModifyModelPage, constraintsBot);


        constraintsBot.gridx = 0;
        constraintsBot.gridy = 1;
        constraintsBot.gridwidth = 2;
        toClientInfoPage.setActionCommand("TO CLIENT INFOS");
        toClientInfoPage.addActionListener(this);
        this.botPanel.add(toClientInfoPage, constraintsBot);





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
            case "TO CLIENT INFOS":
                this.mainJFrame.getClientInfosPage().resetMainContent();
                break;
            case "TO MODIFY MODEL":
                this.mainJFrame.getModifyModelPage().resetMainContent();
                break;

            case "TO POPULARITY":
                this.mainJFrame.getPopularityPage().resetMainContent();
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
