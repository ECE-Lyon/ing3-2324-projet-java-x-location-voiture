package codes;

import codes.UneVoiture;
import codes.dao.Type_voitureDaoImpl;
import codes.model.Type_voiture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ShopPage extends JPanel implements ActionListener, MouseListener {


    private MainJFrame mainJFrame;
    private InscrConnecPage inscrConnecPage;

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();
    private final GridBagConstraints constraints7 = new GridBagConstraints();
    private final GridBagConstraints constraints8 = new GridBagConstraints();
    private final GridBagConstraints constraints9 = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();
    private final GridBagConstraints constraints11 = new GridBagConstraints();


    private JLabel legendaryMotorsportLabel4 = new JLabel("LEGENDARY MOTORSPORT");

    private JPanel legendaryMotorsportPanel4 = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth / 3, windowSizeHeight / 10);

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
    //private ImageIcon[] imagesCarsShop = new ImageIcon[numberOfRentableCars];
    private JLabel[] imagesCarsLabelShop = new JLabel[numberOfRentableCars];
    private JLabel[] descriptionShop = new JLabel[numberOfRentableCars];
    private JButton connexionButtonShop = new JButton("Identifiez-vous !");
    private JButton mySpaceButtonShop = new JButton("Mon espace personnel");
    private JButton disconnectButton = new JButton("Se déconnecter");
    private JButton myBasketButton = new JButton("Voir mon panier");


    private JDialog dialog = new JDialog(mainJFrame);


    private final JLabel areUSureLabel = new JLabel("Etes vous sur de vouloir vous déconnecter ?");

    private final JButton validateButton = new JButton("Valider");

    private ArrayList<ImageIcon> imagesArrayList1 = new ArrayList<>();
    private ArrayList<ImageIcon> imagesArrayList2 = new ArrayList<>();
    private ArrayList<ImageIcon> imagesArrayList3 = new ArrayList<>();
    private ArrayList<Integer> idArrayList = new ArrayList<>();
    private String description;
    private int prix;

    private JButton addFilterButton = new JButton("Ajouter un filtre");
    private JDialog dialogFilters = new JDialog(mainJFrame);
    private JButton validateFilterButton = new JButton("Filtrer");

    private JComboBox<String> parentComboBox;
    private JComboBox<String> childComboBox;
    private JComboBox<String> childComboBox2;


    private Connection connection;

    public ShopPage(MainJFrame mainFrame) {


        this.mainJFrame = mainFrame;
        this.setLayout(new BorderLayout());

        this.connection = connection;

        parentComboBox = new JComboBox<>();
        childComboBox = new JComboBox<>();
        childComboBox2 = new JComboBox<>();

        setupUI();

        ////////////////////////////////////////// INITIALISATION DES TABLEAUX /////////////////////////////////////////


        updateDisplay();
        /*this.mainJFrame.getDisplayCars().updateImages();
        this.numberOfRentableCars = this.mainJFrame.getDisplayCars().getId().size();
        this.idArrayList = this.mainJFrame.getDisplayCars().getId();
        this.imagesArrayList1.addAll(this.mainJFrame.getDisplayCars().getImages1());
        this.imagesArrayList2.addAll(this.mainJFrame.getDisplayCars().getImages2());
        this.imagesArrayList3.addAll(this.mainJFrame.getDisplayCars().getImages3());*/




        updateImages();


        this.mainPanelShop.setLayout(new BorderLayout());
        this.topPanelShop.setLayout(gridBagLayout);
        this.botPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setLayout(gridBagLayout);
        this.topAndBotPanelShop.setBackground(Color.white);


        for (int i = 0; i < numberOfRentableCars; i++) {
            this.rentableCarsPanelShop[i].setLayout(gridBagLayout);
        }


        ///////////////////////////// LE TOP :
        this.legendaryMotorsportLabel4.setOpaque(true);
        this.legendaryMotorsportLabel4.setForeground(Color.MAGENTA);
        this.legendaryMotorsportLabel4.setFont(font1);
        this.legendaryMotorsportPanel4.add(this.legendaryMotorsportLabel4);
        this.legendaryMotorsportPanel4.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraintsTop.gridx = 0;
        this.constraintsTop.gridy = 0;
        this.constraintsTop.anchor = GridBagConstraints.NORTHWEST;


        updateButtonState();

        this.constraintsTop.gridx = 1;
        this.constraintsTop.anchor = GridBagConstraints.CENTER;
        this.topPanelShop.add(topButtons, constraintsTop);
        this.constraintsTop.gridy = 1;
        this.topPanelShop.setMaximumSize(new Dimension(windowSizeWidth / 5, windowSizeHeight / 10));
        this.topPanelShop.add(legendaryMotorsportPanel4, constraintsTop);


        /////////////////////////////////       BAS DE LA PAGE      ///////////////////////////////////////
        //// Faut ajouter une description a chaque truc
        /*int k = 0;
        this.constraints7.gridy = 0;
        constraints7.anchor = GridBagConstraints.CENTER;
        constraints7.fill = GridBagConstraints.BOTH;
        constraints9.gridx = 0;
        constraints9.gridy = 0;
        constraints9.anchor = GridBagConstraints.CENTER;
        constraints9.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < (numberOfRentableCars + 3 - numberOfRentableCars % 3) / 3; i++) {
            this.constraints7.gridy = i;
            constraints9.gridy = i;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints9.gridx = i;
                constraints7.gridx = j;

                constraints9.gridy = 0;
                rentableCarsPanelShop[k].add(imagesCarsLabelShop[k], constraints9);
                constraints9.gridy = 1;
                rentableCarsPanelShop[k].add(descriptionShop[k], constraints9);
                k++;

            }

        }
        k = 0;
        for (int i = 0; i < (numberOfRentableCars + 3 - numberOfRentableCars % 3) / 3; i++) {
            this.constraints7.gridy = i;
            this.constraints7.ipadx = 150;
            this.constraints7.ipady = 10;
            for (int j = 0; j < 3; j++) {
                if (k == numberOfRentableCars) {
                    break;
                }
                constraints7.gridx = j;
                botPanelShop.add(rentableCarsPanelShop[k], constraints7);
                k++;
            }
        }*/


        this.constraints6.gridx = 0;
        this.constraints6.gridy = 0;
        this.constraints6.anchor = GridBagConstraints.CENTER;
        constraints6.fill = GridBagConstraints.BOTH;
        this.legendaryMotorsportPanel4.setMaximumSize(new Dimension(windowSizeWidth / 5, windowSizeHeight / 10));

        this.topAndBotPanelShop.add(this.topPanelShop, this.constraints6);
        this.constraints6.gridy = 1;

        addFilterButton.setActionCommand("FILTER");
        addFilterButton.addActionListener(this);
        this.topAndBotPanelShop.add(addFilterButton, constraints6);

        this.constraints6.gridy = 2;
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

    public void updateDisplay(){
        this.mainJFrame.getDisplayCars().updateImages();
        this.numberOfRentableCars = this.mainJFrame.getDisplayCars().getId().size();
        this.idArrayList = this.mainJFrame.getDisplayCars().getId();
        this.imagesArrayList1.clear();
        this.imagesArrayList2.clear();
        this.imagesArrayList3.clear();
        this.imagesArrayList1.addAll(this.mainJFrame.getDisplayCars().getImages1());
        this.imagesArrayList2.addAll(this.mainJFrame.getDisplayCars().getImages2());
        this.imagesArrayList3.addAll(this.mainJFrame.getDisplayCars().getImages3());
        updateImages();

        this.revalidate();
        this.repaint();
    }

    public void updateImages(){
        int w1, h1, w2, h2, w3, h3;
        double wh1, wh2, wh3;
        botPanelShop.removeAll();
        for (int i = 0; i < numberOfRentableCars; i++) {
            rentableCarsPanelShop[i] = new JPanel();
            rentableCarsPanelShop[i].setLayout(gridBagLayout); // Assurez-vous que le layout est défini

            w1 = this.imagesArrayList1.get(i).getIconWidth();
            w2 = this.imagesArrayList2.get(i).getIconWidth();
            w3 = this.imagesArrayList3.get(i).getIconWidth();
            h1 = this.imagesArrayList1.get(i).getIconHeight();
            h2 = this.imagesArrayList2.get(i).getIconHeight();
            h3 = this.imagesArrayList3.get(i).getIconHeight();
            wh1 = (double) w1 / h1;
            wh2 = (double) w2 / h2;
            wh3 = (double) w3 / h3;

            this.imagesArrayList1.set(i, new ImageIcon(this.imagesArrayList1.get(i).getImage().getScaledInstance(300, (int) (300 / wh1), Image.SCALE_SMOOTH)));
            this.imagesArrayList2.set(i, new ImageIcon(this.imagesArrayList2.get(i).getImage().getScaledInstance(300, (int) (300 / wh2), Image.SCALE_SMOOTH)));
            this.imagesArrayList3.set(i, new ImageIcon(this.imagesArrayList3.get(i).getImage().getScaledInstance(300, (int) (300 / wh3), Image.SCALE_SMOOTH)));

            imagesCarsLabelShop[i] = new JLabel(this.imagesArrayList1.get(i));
            imagesCarsLabelShop[i].addMouseListener(this);
            imagesCarsLabelShop[i].putClientProperty("carId", idArrayList.get(i));
            descriptionShop[i] = new JLabel("Description");

            constraints9.gridx = 0;
            constraints9.gridy = 0;
            rentableCarsPanelShop[i].add(imagesCarsLabelShop[i], constraints9);
            constraints9.gridy = 1;
            rentableCarsPanelShop[i].add(descriptionShop[i], constraints9);

            // Ajouter chaque panneau de voiture à botPanelShop
            this.constraints7.gridx = i % 3;
            this.constraints7.gridy = i / 3;
            botPanelShop.add(rentableCarsPanelShop[i], constraints7);
        }
        botPanelShop.revalidate(); // Indique à Swing de recalculez la disposition des composants
        botPanelShop.repaint();
    }


    public void updateButtonState() {
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


    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();
        updateButtonState();

        // Réinitialisez le contenu principal ici :
        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    private void setupUI() {
        // Configurez les JComboBox et autres composants ici
        DefaultComboBoxModel<String> parentComboBoxModel = new DefaultComboBoxModel<>();
        parentComboBox.setModel(parentComboBoxModel);
        parentComboBoxModel.addElement("Prix croissant");
        parentComboBoxModel.addElement("Prix décroissant");
        parentComboBoxModel.addElement("Trier par type de véhicule");
        parentComboBoxModel.addElement("Trier par marques");
        parentComboBoxModel.addElement("Supprimer les filtres");

        childComboBox.addItem("Break");
        childComboBox.addItem("Berline");
        childComboBox.addItem("SUV");
        childComboBox.addItem("Sport");
        childComboBox.addItem("Limousine");
        childComboBox.addItem("Pick-up");

        childComboBox2.addItem("AUDI");
        childComboBox2.addItem("BMW");


        childComboBox.setVisible(false);
        childComboBox2.setVisible(false);

        parentComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parentComboBox.getSelectedIndex() == 2) {
                    childComboBox.setVisible(true);
                } else if (parentComboBox.getSelectedIndex() == 3){
                    childComboBox2.setVisible(true);
                } else {
                    childComboBox.setVisible(false);
                    childComboBox2.setVisible(false);
                }
            }
        });

        // Ajoutez les JComboBox au panel ou aux dialogs appropriés
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "CONNECTION/INSCRIPTION":
                this.mainJFrame.getInscrConnecPage().resetMainContent();
                break;
            case "MON ESPACE PERSONNEL":
                mainJFrame.getPrivateSpacePage().resetMainContent();
                break;
            case "MY BASKET":
                mainJFrame.getBasketPage().resetMainContent();
                break;
            case "DISCONNECT":
                dialog.setSize(300, 100);
                dialog.setLayout(gridBagLayout);
                constraints9.gridx = 0;
                constraints9.gridy = 0;
                dialog.setAlwaysOnTop(true);
                dialog.setLocationRelativeTo(this.mainJFrame.getFrame());
                dialog.add(areUSureLabel, constraints9);
                constraints9.gridy = 1;
                validateButton.setActionCommand("EXIT DIALOG");
                validateButton.addActionListener(this);
                dialog.add(this.validateButton, constraints9);
                dialog.setVisible(true);
                break;
            case "EXIT DIALOG":
                if (dialog.isActive()) {
                    dialog.dispose();
                    this.mainJFrame.setConnected(false);
                    updateButtonState();
                } else if (dialogFilters.isActive()) {
                    String selectedParent = (String) parentComboBox.getSelectedItem();
                    String selectedChild = (String) childComboBox.getSelectedItem();
                    if (Objects.equals(selectedParent, "Prix croissant")) {
                        this.mainJFrame.setFilter("Prix croissant");
                    } else if (Objects.equals(selectedParent, "Prix décroissant")) {
                        this.mainJFrame.setFilter("Prix décroissant");
                    } else if (Objects.equals(selectedParent, "Trier par type de véhicule")) {
                        if (Objects.equals(selectedChild, "Break")) {
                            this.mainJFrame.setFilter("Break");
                        } else if (Objects.equals(selectedChild, "Berline")) {
                            this.mainJFrame.setFilter("Berline");
                        } else if (Objects.equals(selectedChild, "SUV")) {
                            this.mainJFrame.setFilter("SUV");
                        } else if (Objects.equals(selectedChild, "Sport")) {
                            this.mainJFrame.setFilter("Sport");
                        } else if (Objects.equals(selectedChild, "Limousine")) {
                            this.mainJFrame.setFilter("Limousine");
                        } else if (Objects.equals(selectedChild, "Pick-up")) {
                            this.mainJFrame.setFilter("Pick-up");
                        }
                    }
                    else if (Objects.equals(selectedParent, "Trier par marques")) {
                        if (Objects.equals(selectedChild, "BMW")) {
                            this.mainJFrame.setFilter("BMW");
                        } else if (Objects.equals(selectedChild, "AUDI")) {
                            this.mainJFrame.setFilter("AUDI");
                        }
                    } else if (Objects.equals(selectedParent, "Supprimer les filtres")){
                        this.mainJFrame.setFilter("No filter");
                    }
                    dialogFilters.dispose();
                    updateDisplay();
                }
                break;
            case "FILTER":
                childComboBox.setVisible(false);
                dialogFilters.setSize(300, 100);
                dialogFilters.setLayout(gridBagLayout);
                constraints11.gridx = 0;
                constraints11.gridy = 0;
                dialogFilters.setAlwaysOnTop(true);
                dialogFilters.setLocationRelativeTo(this.mainJFrame.getFrame());

                JPanel panel = new JPanel();

                panel.add(parentComboBox);
                panel.add(childComboBox);

                dialogFilters.add(panel);

                constraints11.gridy = 1;
                validateFilterButton.setActionCommand("EXIT DIALOG");
                validateFilterButton.addActionListener(this);
                dialogFilters.add(this.validateFilterButton, constraints11);
                dialogFilters.setVisible(true);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ////////////////////////////////////FINIR DE FAIRE QUE QUAND ON CLIQUE SUR UNE VOITURE CA FAIT QUELQUE CHOSE

        JLabel sourceLabel = (JLabel) e.getSource();
        Integer carId = (Integer) sourceLabel.getClientProperty("carId");
        System.out.println("Car ID: " + carId);

        ImageIcon[] img = new ImageIcon[imagesArrayList1.size()];
        img[0] = imagesArrayList1.get(carId-1);
        img[1] = imagesArrayList2.get(carId-1);
        img[2] = imagesArrayList3.get(carId-1);
        if(this.mainJFrame.getUneVoiture() == null){
            try {
                this.mainJFrame.setUneVoiture(new UneVoiture(this.mainJFrame, idArrayList.get(carId-1), img, description, prix));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            this.mainJFrame.getUneVoiture().resetMainContent(idArrayList.get(1), img, description, prix);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
