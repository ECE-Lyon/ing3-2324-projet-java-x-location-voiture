package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PrivateSpacePage extends JPanel implements ActionListener, MouseListener {

    private InscrPage inscrPage;
    private InscrConnecPage inscrConnecPage;
    private ConnecPage connecPage;
    private ShopPage shopPage;
    private MainJFrame mainJFrame;

    private final JButton backButton = new JButton("Retour");
    private final JButton modify1 = new JButton("Modifier");
    private final JButton modify2 = new JButton("Modifier");
    private final JButton modify3 = new JButton("Modifier");
    private final JButton modify4 = new JButton("Modifier");
    private final JButton validate = new JButton("Valider");
    private JLabel name;
    private JLabel firstName;
    private JLabel email;
    private JLabel password;

    private final JPanel botPanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    private final JPanel infoInnerPanel = new JPanel();
    private final JPanel modifyPanel = new JPanel();


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints1 = new GridBagConstraints();
    private final GridBagConstraints constraints2 = new GridBagConstraints();
    private final GridBagConstraints constraints3 = new GridBagConstraints();
    private final GridBagConstraints constraints4 = new GridBagConstraints();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();



    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);







    /////////////////////////// LE SHOP ///////////////////////////////
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();




    public PrivateSpacePage(MainJFrame mainJFrame, ConnecPage connecPage, InscrPage inscrPage, InscrConnecPage inscrConnecPage, ShopPage shopPage){
        this.mainJFrame = mainJFrame;
        this.connecPage = connecPage;
        this.inscrConnecPage = inscrConnecPage;
        this.inscrPage = inscrPage;
        this.shopPage = shopPage;

        this.setLayout(new BorderLayout());

        String text1 = "Nom : " + "sdf";
        String text2 = "Nom : " + "sdf";
        String text3 = "Nom : " + "sdf";
        String text4 = "Nom : " + "sdf";
        name = new JLabel(text1);
        firstName = new JLabel(text2);
        email = new JLabel(text3);
        password = new JLabel(text4);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);
        this.infoPanel.setLayout(gridBagLayout);


        ///////////////////////////// LE TOP :  ///////////////////////////////
        this.legendaryMotorsportLabel.setOpaque(true);
        this.legendaryMotorsportLabel.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel.setFont(font1);
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);
        this.legendaryMotorsportPanel.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints1.gridx = 0;
        this.constraints1.gridy = 0;
        this.constraints1.anchor = GridBagConstraints.NORTHWEST;
        this.constraints2.anchor = GridBagConstraints.NORTHEAST;



        backButton.setActionCommand("BACK TO SHOP");
        backButton.addActionListener(this);
        this.topPanel.add(backButton, constraints2);

        this.constraints1.gridy = 1;
        this.constraints1.anchor = GridBagConstraints.CENTER;
        this.topPanel.add(legendaryMotorsportPanel, constraints1);



        this.constraints3.gridx = 0;
        this.constraints3.gridy = 0;
        this.constraints3.anchor = GridBagConstraints.WEST;

        this.infoPanel.add(name, constraints3);
        this.constraints3.gridy = 1;
        this.infoPanel.add(firstName, constraints3);
        this.constraints3.gridy = 2;
        this.infoPanel.add(email, constraints3);
        this.constraints3.gridy = 3;
        this.infoPanel.add(password, constraints3);


        this.constraints3.gridx = 1;
        this.constraints3.gridy = 0;
        this.infoPanel.add(name, constraints3);
        this.constraints3.gridy = 1;
        this.infoPanel.add(firstName, constraints3);
        this.constraints3.gridy = 2;
        this.infoPanel.add(email, constraints3);
        this.constraints3.gridy = 3;
        this.infoPanel.add(password, constraints3);








        this.constraints4.gridx = 0;
        this.constraints4.gridy = 0;
        this.constraints4.anchor = GridBagConstraints.CENTER;
        this.botPanel.add(this.infoPanel, constraints4);
        this.constraints4.gridy = 1;
        this.botPanel.add(this.validate, constraints4);

        this.constraints5.gridx = 0;
        this.constraints5.gridy = 0;

        this.mainPanel.add(this.topPanel, constraints5);
        this.constraints5.gridy = 1;
        this.mainPanel.add(this.botPanel, constraints5);



        this.add(mainPanel, BorderLayout.CENTER);

        this.resetMainContent();
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
            case "BACK TO SHOP" :
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
