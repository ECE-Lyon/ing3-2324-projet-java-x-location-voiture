package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BasketPage extends JPanel implements ActionListener, MouseListener {
    MainJFrame mainJFrame;
    ConnecPage connecPage;
    InscrPage inscrPage;
    InscrConnecPage inscrConnecPage;
    ShopPage shopPage;
    PrivateSpacePage privateSpacePage;



    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsMainPanel = new GridBagConstraints();

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel topButtons = new JPanel();
    private JPanel botPanel = new JPanel();
    private JPanel topAndBotPanel = new JPanel();


    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
    private JPanel legendaryMotorsportPanel = new JPanel();
    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);



    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);

    private final JButton backButton = new JButton("Retour");




    public BasketPage (MainJFrame mainJFrame, ConnecPage connecPage, InscrPage inscrPage, InscrConnecPage inscrConnecPage, ShopPage shopPage, PrivateSpacePage privateSpacePage){
        this.mainJFrame = mainJFrame;
        this.connecPage = connecPage;
        this.inscrPage = inscrPage;
        this.inscrConnecPage = inscrConnecPage;
        this.shopPage = shopPage;
        this.privateSpacePage = privateSpacePage;





        this.setLayout(new BorderLayout());
        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);
        this.topAndBotPanel.setLayout(gridBagLayout);
        this.mainPanel.setBackground(Color.WHITE);



        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(font1);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints.gridy = 0;


        //this.constraints.gridx = 1;
        this.constraints.anchor = GridBagConstraints.EAST;
        backButton.setActionCommand("BACK TO SHOP");
        backButton.addActionListener(this);
        this.topPanel.add(backButton, constraints);
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.constraints.gridy = 1;
        this.topPanel.add(legendaryMotorsportPanel, constraints);




        constraintsMainPanel.anchor = GridBagConstraints.CENTER;
        constraintsMainPanel.gridx = 0;
        constraintsMainPanel.gridy = 0;
        this.mainPanel.add(this.topPanel, constraintsMainPanel);
        constraintsMainPanel.gridy = 1;
        this.mainPanel.add(this.botPanel, constraintsMainPanel);
        this.add(mainPanel, BorderLayout.CENTER);
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
            case "BACK TO SHOP":
                this.shopPage.resetMainContent();
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
