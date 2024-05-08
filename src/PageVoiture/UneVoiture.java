package PageVoiture;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UneVoiture extends JFrame {
    private int currentImageIndex = 0;
    private ArrayList<String> images;
    private JLabel imageLabel;
    private JTextArea descriptionArea;
    private JTextArea selectedDatesArea;
    private Date selectedStartDate;
    private Date selectedEndDate;
    private boolean selectingStartDate = false;
    private boolean selectingEndDate = false;

    public UneVoiture(String titre, String description, ArrayList<String> images, String prix, String annee) {
        setTitle(titre);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Récupérer les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panneau pour le carrousel d'images
        JPanel carouselPanel = new JPanel(new BorderLayout());
        this.images = images;
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height / 2));
        JButton prevImageButton = new JButton("Précédent");
        JButton nextImageButton = new JButton("Suivant");

        prevImageButton.addActionListener(e -> showPreviousImage());
        nextImageButton.addActionListener(e -> showNextImage());

        carouselPanel.add(prevImageButton, BorderLayout.WEST);
        carouselPanel.add(imageLabel, BorderLayout.CENTER);
        carouselPanel.add(nextImageButton, BorderLayout.EAST);

        // Simulation des images du carrousel
        JPanel imagePanel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            JPanel square = new JPanel();
            square.setBackground(Color.GRAY);
            imagePanel.add(square);
        }

        // Panneau pour les informations sur l'article
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel prixLabel = new JLabel("Prix: " + prix);
        JLabel anneeLabel = new JLabel("Année: " + annee);
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea(description);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        infoPanel.add(prixLabel);
        infoPanel.add(anneeLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(descriptionArea);

        JPanel calendarPanel = new JPanel(new BorderLayout());
        JCalendar calendar = new JCalendar();
        calendarPanel.add(calendar, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton startDateButton = new JButton("Date de départ");
        JButton endDateButton = new JButton("Date de fin");

        // Appliquer un fond bleu lorsque les boutons sont sélectionnés
        Color selectedColor = new Color(30, 144, 255); // Bleu royal
        startDateButton.setBackground(UIManager.getColor("Button.background"));
        endDateButton.setBackground(UIManager.getColor("Button.background"));

        JDayChooser dayChooser = calendar.getDayChooser();
        dayChooser.addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = calendar.getDate();
                Date currentDate = new Date();

                // Vérifier si la date sélectionnée est dans le passé
                if (selectedDate.before(currentDate)) {
                    // Afficher un message à l'utilisateur
                    JOptionPane.showMessageDialog(UneVoiture.this, "Vous ne pouvez pas sélectionner une date passée.", "Date invalide", JOptionPane.ERROR_MESSAGE);
                    // Réinitialiser la date sélectionnée à la date actuelle
                } else {
                    if (selectingStartDate) {
                        selectedStartDate = selectedDate;
                        startDateButton.setText("Date de départ sélectionnée");
                        endDateButton.setEnabled(true);
                        selectingStartDate = true;
                        updateSelectedDates();
                    } else if (selectingEndDate) {
                        selectedEndDate = selectedDate;
                        endDateButton.setText("Date de fin sélectionnée");
                        startDateButton.setEnabled(true);
                        selectingEndDate = true;
                        updateSelectedDates();
                    }
                }
            }
        });

        startDateButton.addActionListener(e -> {
            selectingStartDate = true;
            selectingEndDate = false;
            startDateButton.setEnabled(false);
            endDateButton.setEnabled(true);
            startDateButton.setText("Sélectionnez une date de départ");
            endDateButton.setText("Date de fin");
        });

        endDateButton.addActionListener(e -> {
            selectingEndDate = true;
            selectingStartDate = false;
            endDateButton.setEnabled(false);
            startDateButton.setEnabled(true);
            startDateButton.setText("Date de départ");
            endDateButton.setText("Sélectionnez une date de fin");
        });

        buttonPanel.add(startDateButton);
        buttonPanel.add(endDateButton);

        calendarPanel.add(buttonPanel, BorderLayout.EAST);

        // JTextArea pour afficher les dates sélectionnées
        selectedDatesArea = new JTextArea("Dates de début:\nDates de fin:");
        selectedDatesArea.setEditable(false);
        calendarPanel.add(selectedDatesArea, BorderLayout.SOUTH);

        mainPanel.add(carouselPanel, BorderLayout.WEST);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(calendarPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Désactivation de la décoration de la fenêtre pour cacher la barre de titre et la bordure

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Afficher la première image au démarrage
        showImage(0);
    }

    private void showImage(int index) {
        if (index >= 0 && index < images.size()) {
            currentImageIndex = index;
            // Charger l'image correspondante
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(images.get(index)));
            // Mettre à jour l'imageLabel avec l'image chargée
            imageLabel.setIcon(imageIcon);
        }
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % images.size();
        showImage(currentImageIndex);
    }

    private void showPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + images.size()) % images.size();
        showImage(currentImageIndex);
    }

    // Mettre à jour le JTextArea avec les dates de début et de fin sélectionnées
    private void updateSelectedDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String startDateString = selectedStartDate != null ? dateFormat.format(selectedStartDate) : "Non sélectionnée";
        String endDateString = selectedEndDate != null ? dateFormat.format(selectedEndDate) : "Non sélectionnée";

        selectedDatesArea.setText("Date de début: " + startDateString + "\nDate de fin: " + endDateString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Exemple d'utilisation
            String titre = "Voiture à louer";
            String description = "Super voiture en excellent état !";
            ArrayList<String> images = new ArrayList<>();
            images.add("renault_20clioeditiononehb5b_noirétoilé.webp");
            images.add("téléchargement.webp");
            String prix = "100 € par jour";
            String annee = "2018";
            new UneVoiture(titre, description, images, prix, annee);
        });
    }
}
