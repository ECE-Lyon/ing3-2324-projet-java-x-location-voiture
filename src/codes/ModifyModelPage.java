package codes;

import codes.dao.Mysql;
import codes.dao.Type_voitureDao;
import codes.dao.Type_voitureDaoImpl;
import codes.model.Type_voiture;
import codes.model.Voiture;
import com.mysql.cj.conf.ConnectionUrlParser;

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

public class ModifyModelPage extends JPanel implements ActionListener, MouseListener {
    private MainJFrame mainJFrame;


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraintsTop = new GridBagConstraints();
    private final GridBagConstraints constraintsMain = new GridBagConstraints();
    private final GridBagConstraints constraintsBot = new GridBagConstraints();



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


    private JButton ajouterVehicule = new JButton("Ajouter un nouveau véhicule");
    private JButton ajouterModele = new JButton("Ajouter un nouveau modèle de véhicule");
    private JButton supprimerVehicule = new JButton("Supprimer un véhicule");

    private JTable table;

    private Connection connection;




    ///////////////////////////////////////CREATION DES JDIALOGS PERMETTANT D'AJOUTER/SUPPRIMER VEHICLE ET MODELS
    private JDialog dialog1 = new JDialog(mainJFrame);;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JLabel idModelLabel = new JLabel("Saisir l'ID du modèle");
    private JLabel prixParJourLabel = new JLabel("Saisir le prix de location par jour du véhicule");
    private JTextField tfId = new JTextField();
    private JTextField tfPrixParJour = new JTextField();
    private JButton validateButton = new JButton("Valider");

    private JDialog dialog2 = new JDialog(mainJFrame);;
    private GridBagConstraints constraints2 = new GridBagConstraints();
    private JLabel idModelLabel2 = new JLabel("Saisir l'ID du modèle");
    private JLabel prixParJourLabel2 = new JLabel("Saisir le prix de location par jour du véhicule");
    private JTextField tfId2 = new JTextField();
    private JTextField tfPrixParJour2 = new JTextField();
    private JButton validateButton2 = new JButton("Valider");

    private JDialog dialog3 = new JDialog(mainJFrame);;
    private GridBagConstraints constraints3 = new GridBagConstraints();
    private JLabel nameLabel = new JLabel("Saisir le name du modèle");
    private JLabel marqueLabel = new JLabel("Saisir la marque du modèle");
    private JTextField nameTf = new JTextField();
    private JTextField marqueTf = new JTextField();
    private ImageIcon imageIcon1;
    private ImageIcon imageIcon2;
    private ImageIcon imageIcon3;
    private JButton validateButton3 = new JButton("Valider");


    private JComboBox<String> parentComboBox;
    private JComboBox<String> childComboBox;


    public ModifyModelPage(MainJFrame mainJFrame) throws SQLException {
        this.mainJFrame = mainJFrame;
        this.setLayout(new BorderLayout());

        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);

        this.connection = Mysql.openConnection();

        parentComboBox = new JComboBox<>();
        childComboBox = new JComboBox<>();

        setupUI();



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




        ArrayList<String[]> data = new ArrayList<>();


        try {

            Type_voitureDao modeleDao =new Type_voitureDaoImpl(connection);

            List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> modeleVoiturePairs = modeleDao.searchAllModele();

            if (!modeleVoiturePairs.isEmpty()) {
                for (ConnectionUrlParser.Pair<Type_voiture, Voiture> modeleVoiturePair : modeleVoiturePairs) {
                    Type_voiture typeVoiture = modeleVoiturePair.left;
                    Voiture voiture = modeleVoiturePair.right;

                    /*System.out.println("ID: " + typeVoiture.getId_type_voiture());
                    System.out.println("Nom: " + typeVoiture.getNom_type_voiture());
                    System.out.println("Marque: " + typeVoiture.getMarque_voiture());
                    System.out.println("Type: " + typeVoiture.getType());
                    System.out.println("Description: " + typeVoiture.getDescription());*/



                    if (voiture != null) {
                        System.out.println("Prix par jour: " + voiture.getPrix_par_jour() + " Euros");

                        data.add(new String[]{"" + typeVoiture.getId_type_voiture(), typeVoiture.getNom_type_voiture(),
                                typeVoiture.getMarque_voiture(), "" + typeVoiture.getType(), typeVoiture.getDescription(),
                                "" + voiture.getPrix_par_jour()});

                    } else {
                        System.out.println("Prix par jour: N/A");

                        data.add(new String[]{"" + typeVoiture.getId_type_voiture(), typeVoiture.getNom_type_voiture(),
                                typeVoiture.getMarque_voiture(), "" + typeVoiture.getType(), typeVoiture.getDescription(),
                                "0"});
                    }
                    System.out.println("------------------------------");
                }
            } else {
                System.out.println("Aucun modèle trouvé.");
            }

        } catch (SQLException er) {
            er.printStackTrace();
        }

        // Définir les en-têtes de colonne
        String[] columnNames = {"ID : ", "Nom : ", "Marque : ", "Type : ", "Description : ", "Prix : "};
        String[][] dataArray = new String[data.size()][];
        data.toArray(dataArray);
        table = new JTable(dataArray, columnNames);


        constraintsBot.gridwidth = 2;
        constraintsBot.gridx = 0;
        constraintsBot.gridy = 0;
        this.botPanel.add(table, constraintsBot);
        constraintsBot.gridwidth = 1;
        constraintsBot.gridy = 1;
        ajouterVehicule.setActionCommand("ADD VEHICLE");
        ajouterVehicule.addActionListener(this);
        botPanel.add(ajouterVehicule, constraintsBot);
        constraintsBot.gridx = 1;
        supprimerVehicule.setActionCommand("SUPP VEHICLE");
        supprimerVehicule.addActionListener(this);
        botPanel.add(supprimerVehicule, constraintsBot);
        constraintsBot.gridx = 0;
        constraintsBot.gridy = 2;
        constraintsBot.gridwidth = 2;
        ajouterModele.setActionCommand("ADD MODEL");
        ajouterModele.addActionListener(this);
        botPanel.add(ajouterModele, constraintsBot);




        constraintsMain.gridx = 0;
        constraintsMain.gridy = 0;
        constraintsMain.anchor = GridBagConstraints.CENTER;
        this.mainPanel.add(topPanel, constraintsMain);
        constraintsMain.gridy = 1;
        this.mainPanel.add(botPanel, constraintsMain);
        add(this.mainPanel, BorderLayout.CENTER);
        resetMainContent();
    }

    private void setupUI() {
        // Configurez les JComboBox et autres composants ici
        DefaultComboBoxModel<String> parentComboBoxModel = new DefaultComboBoxModel<>();
        parentComboBox.setModel(parentComboBoxModel);
        parentComboBoxModel.addElement("Prix croissant");
        parentComboBoxModel.addElement("Prix décroissant");
        parentComboBoxModel.addElement("Trier par type de véhicule");

        childComboBox.addItem("Break");
        childComboBox.addItem("Berline");
        childComboBox.addItem("SUV");
        childComboBox.addItem("Sport");
        childComboBox.addItem("Limousine");
        childComboBox.addItem("Pick-up");

        childComboBox.setVisible(false);

        parentComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parentComboBox.getSelectedIndex() == 2) {
                    childComboBox.setVisible(true);
                } else {
                    childComboBox.setVisible(false);
                }
            }
        });

        // Ajoutez les JComboBox au panel ou aux dialogs appropriés
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
            case "ADD MODEL":
                dialog3.setSize(300, 100);
                dialog3.setLayout(gridBagLayout);
                constraints3.gridx = 0;
                constraints3.gridy = 0;
                dialog3.setAlwaysOnTop(true);
                dialog3.setLocationRelativeTo(this.mainJFrame.getFrame());
                JPanel panel = new JPanel();



                dialog3.add(nameLabel, constraints3);
                constraints3.gridx = 1;
                dialog3.add(nameTf, constraints3);
                constraints3.gridx = 0;
                constraints3.gridy = 1;
                dialog3.add(marqueLabel, constraints3);
                constraints3.gridx = 1;
                dialog3.add(marqueTf, constraints3);
                constraints3.gridx = 0;
                constraints3.gridy = 2;
                constraints3.gridwidth=2;


                panel.add(parentComboBox);
                panel.add(childComboBox);
                constraints3.gridy = 3;

                dialog3.add(panel);
                validateButton3.setActionCommand("EXIT DIALOG");
                validateButton3.addActionListener(this);
                dialog3.add(this.validateButton3, constraints3);
                dialog3.setVisible(true);
                break;
            case "SUPP VEHICLE":
                dialog2.setSize(800, 300);

                this.tfId2.setColumns(20);
                this.tfPrixParJour2.setColumns(20);

                dialog2.setLayout(gridBagLayout);
                constraints2.gridx = 0;
                constraints2.gridy = 0;
                dialog2.setAlwaysOnTop(true);
                dialog2.setLocationRelativeTo(this.mainJFrame.getFrame());

                dialog2.add(idModelLabel2, constraints2);
                constraints2.gridx = 1;
                dialog2.add(tfId2, constraints2);
                constraints2.gridx = 0;
                constraints2.gridy = 1;
                dialog2.add(prixParJourLabel2, constraints2);
                constraints2.gridx = 1;
                dialog2.add(tfPrixParJour2, constraints2);
                constraints2.gridx = 0;
                constraints2.gridwidth=2;

                constraints2.gridy = 2;
                validateButton2.setActionCommand("GO DESTROY IT");
                validateButton2.addActionListener(this);
                dialog2.add(this.validateButton2, constraints2);
                dialog2.setVisible(true);
                break;
            case "ADD VEHICLE":
                dialog1.setSize(800, 300);

                this.tfId.setColumns(20);
                this.tfPrixParJour.setColumns(20);

                dialog1.setLayout(gridBagLayout);
                constraints.gridx = 0;
                constraints.gridy = 0;
                dialog1.setAlwaysOnTop(true);
                dialog1.setLocationRelativeTo(this.mainJFrame.getFrame());

                dialog1.add(idModelLabel, constraints);
                constraints.gridx = 1;
                dialog1.add(tfId, constraints);
                constraints.gridx = 0;
                constraints.gridy = 1;
                dialog1.add(prixParJourLabel, constraints);
                constraints.gridx = 1;
                dialog1.add(tfPrixParJour, constraints);
                constraints.gridx = 0;
                constraints.gridwidth=2;

                constraints.gridy = 2;
                validateButton.setActionCommand("LETS CREATE IT");
                validateButton.addActionListener(this);
                dialog1.add(this.validateButton, constraints);
                dialog1.setVisible(true);
                break;
            case  "GO DESTROY IT":
                tfId.getText();
                tfPrixParJour.getText();
                dialog2.dispose();
                break;
            case "LETS CREATE IT" :
                dialog1.dispose();
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
