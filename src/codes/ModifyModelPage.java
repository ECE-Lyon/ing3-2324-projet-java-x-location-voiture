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


    private int nbrOfLine = 3;
    private JTable table;

    private Connection connection;


    public ModifyModelPage(MainJFrame mainJFrame) throws SQLException {
        this.mainJFrame = mainJFrame;
        this.setLayout(new BorderLayout());

        this.mainPanel.setLayout(gridBagLayout);
        this.topPanel.setLayout(gridBagLayout);
        this.botPanel.setLayout(gridBagLayout);

        this.connection = Mysql.openConnection();



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
        data.add(new String[]{"ID : ", "Nom : ", "Marque : ", "Type : ", "Description : "});


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

                    data.add(new String[]{"" + typeVoiture.getId_type_voiture(), typeVoiture.getNom_type_voiture(),
                            typeVoiture.getMarque_voiture(), "" + typeVoiture.getType(), typeVoiture.getDescription()});


                    if (voiture != null) {
                        System.out.println("Prix par jour: " + voiture.getPrix_par_jour() + " Euros");
                    } else {
                        System.out.println("Prix par jour: N/A");
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
        String[] columnNames = {"ID", "Nom", "Sujet"};
        String[][] dataArray = new String[data.size()][];
        data.toArray(dataArray);
        table = new JTable(dataArray, columnNames);




        this.botPanel.add(table, constraintsBot);




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
