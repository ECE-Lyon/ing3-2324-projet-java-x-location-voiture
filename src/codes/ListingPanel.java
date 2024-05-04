/*package codes;

import codes.dao.Mysql;
import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.model.Employe;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ListingPanel extends JPanel implements ActionListener {

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private JButton connexionButtonShop = new JButton("Identifiez-vous !");
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);


    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints5 = new GridBagConstraints();
    private final GridBagConstraints constraints6 = new GridBagConstraints();
    private final GridBagConstraints constraints7 = new GridBagConstraints();
    private final GridBagConstraints constraints8 = new GridBagConstraints();
    private final GridBagConstraints constraints9 = new GridBagConstraints();
    private final GridBagConstraints constraints10 = new GridBagConstraints();
    private final JPanel mainPanelShop = new JPanel();
    private final JPanel topPanelShop = new JPanel();
    private final JPanel botPanelShop = new JPanel();
    private final JPanel topAndBotPanelShop = new JPanel();



    private int numberOfRentableCars = 50;
    private JScrollBar scrollBarShop = new JScrollBar();
    private JPanel descImgPanelShop = new JPanel();
    private JPanel[] rentableCarsPanelShop = new JPanel[numberOfRentableCars];
    private ImageIcon[] imagesCarsShop = new ImageIcon[10];
    private JLabel[] descriptionShop = new JLabel[10];
    private final GridBagConstraints mainPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints topPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints botPanelShopConstraints = new GridBagConstraints();
    private final GridBagConstraints rentableCarsPanelConstraints = new GridBagConstraints();
    private JLabel legendaryMotorsportLabel4 = new JLabel("LEGENDARY MOTORSPORT");
    private JPanel legendaryMotorsportPanel4 = new JPanel();
    private JLabel[] imagesCarsLabelShop = new JLabel[numberOfRentableCars];
    private int fontSizeLM = 36;
    private Font font1 = new Font("Arial", Font.PLAIN, fontSizeLM);






    public ListingPanel() {

        super();
        setLayout(new BorderLayout());

        ////////////////////////////////////////// INITIALISATION DES TABLEAUX /////////////////////////////////////////
        int w, h;
        double wh;
        String fileName = "";

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


        //// au top faut mettre le top


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
        this.legendaryMotorsportLabel4.setOpaque(true); // Permet de définir la couleur de fond
        this.legendaryMotorsportLabel4.setForeground(Color.MAGENTA); // Couleur du texte
        this.legendaryMotorsportLabel4.setFont(font1);
        this.legendaryMotorsportPanel4.add(this.legendaryMotorsportLabel4);

        this.legendaryMotorsportPanel4.setPreferredSize(dimensionLegendaryMotorsportPanel);


        this.constraints5.gridx = 0;
        this.constraints5.gridy = 0;
        this.constraints5.anchor = GridBagConstraints.NORTHWEST;
        this.constraints6.anchor = GridBagConstraints.NORTHEAST;


        connexionButtonShop.addMouseListener(this);
        this.topPanelShop.add(connexionButtonShop, constraints6);
        this.constraints5.gridy = 1;
        this.constraints5.anchor = GridBagConstraints.CENTER;
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
                System.out.println(k);

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
        // scrollPane.setPreferredSize(new Dimension(800, 600));
        //this.mainPanelShop.add(this.topAndBotPanelShop, this.constraints8);


        //this.constraints8.anchor = GridBagConstraints.EAST;
        this.constraints8.gridx = 1;
        this.constraints8.weightx = 2;

        //this.mainPanelShop.add(scrollPane, this.constraints8);
        this.mainPanelShop.add(scrollPane, BorderLayout.CENTER);










    }

    public String imagesName(int i) {
        String s = "Picture1.png";
        return s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "INSCRIPTION/CONNECTION" :
                cardLayout.show(panelContainer, "INSCRIPTION/CONNECTION");
                break;
            case "BOUTON SE CONNECTER" :
                if(this.passwordField.getPassword().equals("") || this.usernameField.getText().equals("")){
                    elementMissingInscr.setVisible(true);
                } else {
                    char[] passwordF = this.passwordField.getPassword();
                    String email = this.usernameField.getText().toString();

                    String password = new String(passwordF);

                    System.out.println(email);
                    System.out.println(password);

                    try(Connection connection = Mysql.openConnection()) {

                        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                        Utilisateur utilisateur = utilisateurDao.getUtilisateur(email, password);

                        if(utilisateur != null){

                            if (utilisateur instanceof Client) {
                                System.out.println("Informations du client :");
                                Client client = (Client) utilisateur;
                                System.out.println("Nom : " + client.getNom_client());
                                System.out.println("Prénom : " + client.getPrenom_client());
                                // Ajoutez d'autres informations si nécessaire
                                cardLayout.show(panelContainer, "PAGE DE LOCATION");
                            } else if (utilisateur instanceof Employe) {
                                System.out.println("Informations de l'employé :");
                                Employe employe = (Employe) utilisateur;
                                System.out.println("Nom : " + employe.getNom_employe());
                                System.out.println("Prénom : " + employe.getPrenom_employe());
                                System.out.println("Poste : " + employe.getPoste());
                                // Ajoutez d'autres informations si nécessaire
                            } else if (utilisateur instanceof Entreprise) {
                                System.out.println("Informations de l'entreprise :");
                                Entreprise entreprise = (Entreprise) utilisateur;
                                System.out.println("Nom : " + entreprise.getNom_entreprise());
                                System.out.println("Siret : " + entreprise.getSiret());
                                // Ajoutez d'autres informations si nécessaire
                            }

                        } else {
                            System.out.println("Aucun utilisateur trouvé avec ces informations d'identification.");
                        }

                    } catch (SQLException er){
                        er.printStackTrace();
                    }

                    elementMissingInscr.setVisible(false);
                }
                break;
            case "PAGE S INSCRIRE" :

                System.out.println(this.passwordInscrTF);

                if(this.passwordInscrTF.getPassword().equals("") || this.firstNameInscrTF.getText().isEmpty() || this.lastNameInscrTF.getText().isEmpty() || this.emailInscrTF.getText().isEmpty()){
                    elementMissingInscr.setVisible(true);
                }else {
                    cardLayout.show(panelContainer, "PAGE DE LOCATION");
                    char[] passwordF = this.passwordInscrTF.getPassword();
                    String password = new String(passwordF);
                    String firstName = this.firstNameInscrTF.getText().toString();
                    String lastName = this.lastNameInscrTF.getText().toString();
                    String email = this.emailInscrTF.getText().toString();

                    try(Connection connection = Mysql.openConnection()) {

                        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(connection);

                        Utilisateur utilisateur = new Utilisateur(-1, password, email);
                        Client client = new Client(-1, null, null, firstName, lastName, null);

                        utilisateurDao.addClient(client, utilisateur);

                        System.out.println("Utilisateur ajouté avec succès !");

                    } catch (SQLException er){
                        er.printStackTrace();
                    }

                }
                break;
        }
    }
}
*/