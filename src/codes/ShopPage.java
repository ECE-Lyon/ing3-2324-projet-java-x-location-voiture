package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopPage extends JPanel implements ActionListener, MouseListener {


    private MainJFrame mainJFrame;
    private InscrConnecPage inscrConnecPage;

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();
    private final GridBagConstraints constraints7 = new GridBagConstraints();
    private final GridBagConstraints constraints8 = new GridBagConstraints();
    private final GridBagConstraints constraints9 = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();





    private JLabel legendaryMotorsportLabel4 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel4 = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);

    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);







    /////////////////////////// LE SHOP ///////////////////////////////
    private int numberOfRentableCars = 50;
    private JPanel mainPanelShop = new JPanel();
    private JPanel topPanelShop = new JPanel();
    private JPanel topButtons = new JPanel();
    private JPanel botPanelShop = new JPanel();
    private JPanel topAndBotPanelShop = new JPanel();
    private JPanel[] rentableCarsPanelShop = new JPanel[numberOfRentableCars];
    private ImageIcon[] imagesCarsShop = new ImageIcon[numberOfRentableCars];
    private JLabel[] imagesCarsLabelShop = new JLabel[numberOfRentableCars];
    private JLabel[] descriptionShop = new JLabel[numberOfRentableCars];
    private JButton connexionButtonShop = new JButton("Identifiez-vous !");
    private JButton mySpaceButtonShop = new JButton("Mon espace personnel");
    private JButton disconnectButton = new JButton("Se déconnecter");
    private JButton myBasketButton = new JButton("Voir mon panier");



    private JDialog dialog = new JDialog(mainJFrame);


    private final JLabel areUSureLabel = new JLabel("Etes vous sur de vouloir vous déconnecter ?");

    private final JButton validateButton = new JButton("Valider");

    private JButton addFilterButton = new JButton("Filtres");
    private JDialog dialogFilters = new JDialog(mainJFrame);
    private JMenu menu;
    private JMenuItem downToUpPrice, upToDownPrice, carType, carPrice;
    private JMenuBar menubar = new JMenuBar();


    public ShopPage(MainJFrame mainFrame) {


        this.mainJFrame = mainFrame;
        this.setLayout(new BorderLayout());

        ////////////////////////////////////////// INITIALISATION DES TABLEAUX /////////////////////////////////////////
        int w, h;
        double wh;
        String fileName = "";

        menu = new JMenu("Menu");
        // Créer le sous menu
        menu = new JMenu("Sous Menu");
        // Créer les éléments du menu et sous menu
        carPrice = new JMenuItem("Prix décroissant");
        carType = new JMenuItem("Prix croissant");
        upToDownPrice = new JMenuItem("upToDownPrice");
        downToUpPrice = new JMenuItem(" 4");



        /////////////////////////// NUMBERVOITURE IL FAUT CHERCHER LE NBR DE VOITURES DIFFERENTES DANS LA BDDD























        for (int i = 0; i < numberOfRentableCars-1; i++) {
            fileName = "src/images/" + imagesName(i);
            imagesCarsShop[i] = new ImageIcon(fileName);
        }
        imagesCarsShop[numberOfRentableCars-1] = new ImageIcon("C:/ECE/2023-2024/2nd semestre/info/codes/ing3-2324-projet-java-x-location-voiture/src/images/lambo.png");

        for(int i = 0; i < numberOfRentableCars; i++) {
            rentableCarsPanelShop[i] = new JPanel();
            w = imagesCarsShop[i].getIconWidth();
            h = imagesCarsShop[i].getIconHeight();
            wh = (double) w/h;
            imagesCarsShop[i] = new ImageIcon(imagesCarsShop[i].getImage().getScaledInstance(100, (int)(100/wh), Image.SCALE_SMOOTH));
            imagesCarsLabelShop[i] = new JLabel(imagesCarsShop[i]);
            descriptionShop[i] = new JLabel("Description");
        }






        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////  QUATRIEME PAGE ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        this.mainPanelShop.setLayout(new BorderLayout());
        this.topPanelShop.setLayout(gridBagLayout);
        this.botPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setBackground(Color.red);


        for (int i = 0; i < numberOfRentableCars; i++){
            this.rentableCarsPanelShop[i].setLayout(gridBagLayout);
            rentableCarsPanelShop[i].setBackground(Color.BLUE);
        }


        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel4.setOpaque(true);
        this.legendaryMotorsportLabel4.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel4.setFont(font1);
        this.legendaryMotorsportPanel4.add(this.legendaryMotorsportLabel4);
        this.legendaryMotorsportPanel4.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints5.gridx = 0;
        this.constraints5.gridy = 0;
        this.constraints5.anchor = GridBagConstraints.NORTHWEST;



        updateButtonState();

        this.constraints5.gridx = 1;
        this.constraints5.anchor = GridBagConstraints.CENTER;
        this.topPanelShop.add(topButtons, constraints5);
        this.constraints5.gridy = 1;
        this.topPanelShop.add(legendaryMotorsportPanel4, constraints5);




        /////////////////////////////////       BAS DE LA PAGE      ///////////////////////////////////////
        //// Faut ajouter une description a chaque truc
        int k = 0;
        this.constraints7.gridy = 0;
        constraints7.anchor = GridBagConstraints.CENTER;
        constraints7.fill = GridBagConstraints.BOTH;
        //constraints9.fill = GridBagConstraints.BOTH;
        constraints9.gridx = 0;
        constraints9.gridy = 0;
        constraints9.anchor = GridBagConstraints.CENTER;
        constraints9.fill = GridBagConstraints.BOTH;
        for(int i = 0; i < (numberOfRentableCars + 3-numberOfRentableCars%3)/3; i++) {
            this.constraints7.gridy = i;
            constraints9.gridy = i;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints9.gridx = i;
                constraints7.gridx = j;
                rentableCarsPanelShop[k].add(imagesCarsLabelShop[k], constraints9);
                //botPanelShop.add(rentableCarsPanelShop[k], constraints7);
                k++;

            }

        }
        k=0;
        for (int i = 0; i < (numberOfRentableCars + 3-numberOfRentableCars%3)/3; i++) {
            this.constraints7.gridy = i;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints7.gridx = j;
                botPanelShop.add(rentableCarsPanelShop[k], constraints7);
                k++;
            }
        }



        this.constraints6.gridx = 0;
        this.constraints6.gridy = 0;
        this.constraints6.anchor = GridBagConstraints.CENTER;
        constraints6.fill = GridBagConstraints.BOTH;

        this.topAndBotPanelShop.add(this.topPanelShop, this.constraints6);
        this.constraints6.gridy = 1;
        topAndBotPanelShop.setMaximumSize(new Dimension(1000, 800));
        this.topAndBotPanelShop.add(this.botPanelShop, this.constraints6);



        this.constraints8.gridx = 0;
        this.constraints8.gridy = 0;
        this.constraints8.anchor = GridBagConstraints.CENTER;
        this.constraints8.weightx = 25;
        constraints8.fill = GridBagConstraints.BOTH;

        JScrollPane scrollPane = new JScrollPane(topAndBotPanelShop, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        //this.constraints8.anchor = GridBagConstraints.EAST;
        this.constraints8.gridx = 1;
        this.constraints8.weightx = 2;

        //this.mainPanelShop.add(scrollPane, this.constraints8);
        this.mainPanelShop.add(scrollPane, BorderLayout.CENTER);

        add(mainPanelShop, BorderLayout.CENTER);

        resetMainContent();







    }


    public void updateButtonState(){
        constraints10.gridx = 0;
        if (!this.mainJFrame.isConnected()) {
            topButtons.removeAll();
            this.constraints10.anchor = GridBagConstraints.NORTHEAST;
            connexionButtonShop.setActionCommand("CONNECTION/INSCRIPTION");
            connexionButtonShop.addActionListener(this);
            this.topButtons.add(connexionButtonShop, constraints10);
        } else {
            topButtons.remove(connexionButtonShop);
            this.constraints10.anchor = GridBagConstraints.NORTHWEST;
            disconnectButton.setActionCommand("DISCONNECT");
            disconnectButton.addActionListener(this);
            this.topButtons.add(disconnectButton, constraints10);
            constraints10.gridx = 1;
            this.constraints10.anchor = GridBagConstraints.NORTH;
            myBasketButton.setActionCommand("MY BASKET");
            myBasketButton.addActionListener(this);
            this.topButtons.add(myBasketButton, constraints10);
            constraints10.gridx = 2;
            this.constraints10.anchor = GridBagConstraints.NORTHEAST;
            mySpaceButtonShop.setActionCommand("MON ESPACE PERSONNEL");
            mySpaceButtonShop.addActionListener(this);
            this.topButtons.add(mySpaceButtonShop, constraints10);
        }
        this.revalidate();
        this.repaint();
    }

    public String imagesName(int i) {
        String s = "Picture1.png";
        return s;
    }


    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();
        updateButtonState();

        // Réinitialisez le contenu principal ici :
        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "CONNECTION/INSCRIPTION" :
                this.mainJFrame.getInscrConnecPage().resetMainContent();
                break;
            case "MON ESPACE PERSONNEL" :
                mainJFrame.getPrivateSpacePage().resetMainContent();
                break;
            case "MY BASKET" :
                mainJFrame.getBasketPage().resetMainContent();
                break;
            case "DISCONNECT" :
                dialog.setSize(300, 100);
                dialog.setLayout(gridBagLayout);
                constraints9.gridx = 0;
                constraints9.gridy = 0;
                dialog.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog.add(areUSureLabel, constraints9);
                constraints9.gridy = 1;
                validateButton.setActionCommand("EXIT DIALOG");
                validateButton.addActionListener(this);
                dialog.add(this.validateButton, constraints9);
                dialog.setVisible(true);
                break;
            case "EXIT DIALOG" :
                dialog.dispose();
                this.mainJFrame.setConnected(false);
                updateButtonState();
                break;
            case "CLICK ON AN IMG":
                ////////////////Ici faut qu'on révupère l'ID voiture /////

                //Ensuite on va sur la page de PAULOOOOOOOO
                //this.mainJFrame.get
                break;
            case "FILTER BUTTON":

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
