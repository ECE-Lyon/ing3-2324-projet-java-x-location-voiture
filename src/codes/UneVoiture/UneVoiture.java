package codes.UneVoiture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import codes.dao.VoitureDao;
import codes.dao.VoitureDaoImpl;
import codes.model.Reservation;
import codes.model.Voiture;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

public class UneVoiture extends JFrame implements ActionListener, MouseListener {
    private int currentImageIndex = 0;
    private ArrayList<String> images;
    private JLabel imageLabel;
    private JTextArea descriptionArea;
    private JTextArea selectedDatesArea;
    private Date selectedStartDate;
    private Date selectedEndDate;
    private boolean selectingStartDate = false;
    private boolean selectingEndDate = false;
    private JComboBox<String> startTimeComboBox;
    private JComboBox<String> endTimeComboBox;
    private com.toedter.calendar.JDayChooser dayChooser;


    private GridBagLayout gridBagLayout = new GridBagLayout();
    private JButton validateButton = new JButton("Valider");
    private JLabel areUSureLabel = new JLabel("Souhaitez-vous vraiment ajouter ce produit au panier ?");
    private GridBagConstraints constraints = new GridBagConstraints();
    public UneVoiture(String description, ArrayList<String> images, int prix, int annee) {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Récupérer les dimensions de l'écran
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvrir en plein écran

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panneau pour le carrousel d'images
        JPanel carouselPanel = new JPanel(new BorderLayout());
        this.images = images;
        imageLabel = new JLabel();
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

        JLabel prixLabel = new JLabel("Prix: " + prix+" €/j");
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

        // Ajouter le bouton "Réservé"
        JButton reserveButton = new JButton("Réservez !");
        reserveButton.setActionCommand("RESERVEZ!");
        reserveButton.addActionListener(this);
        reserveButton.setForeground(new Color(0, 128, 0)); // Vert
        infoPanel.add(reserveButton);

        JPanel calendarPanel = new JPanel(new BorderLayout());
        JCalendar calendar = new JCalendar();

        // Configurer la date minimum autorisée
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DAY_OF_MONTH, -1);
        calendar.setMinSelectableDate(minDate.getTime());

        calendarPanel.add(calendar, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton startDateButton = new JButton("Date de départ");
        JButton endDateButton = new JButton("Date de fin");

        Dimension buttonSize = new Dimension(200, 30); // Taille fixe pour les boutons
        startDateButton.setPreferredSize(buttonSize);
        endDateButton.setPreferredSize(buttonSize);

        // Appliquer un fond bleu lorsque les boutons sont sélectionnés
        startDateButton.setBackground(UIManager.getColor("Button.background"));
        endDateButton.setBackground(UIManager.getColor("Button.background"));

        JDayChooser dayChooser = calendar.getDayChooser();
        dayChooser.addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = calendar.getDate();

                // Vérifier si la date sélectionnée est dans le passé
                if (selectedDate.before(minDate.getTime())) {

                    JOptionPane.showMessageDialog(UneVoiture.this, "Vous ne pouvez pas sélectionner une date passée.", "Date invalide", JOptionPane.ERROR_MESSAGE);
                } else if (selectingStartDate) {
                    selectedStartDate = selectedDate;
                    startDateButton.setText("Date de départ sélectionnée");
                    endDateButton.setEnabled(true);
                    selectingStartDate = true;
                    selectingEndDate = true;
                    if(selectedStartDate != null && selectedEndDate != null && selectedDate.after(selectedEndDate)) {
                        JOptionPane.showMessageDialog(UneVoiture.this, "La date de début ne peut pas être postérieur à la date de fin.", "Date invalide", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        updateSelectedDates();
                    }
                } else if (selectingEndDate) {
                    if (selectedDate.before(selectedStartDate)) {
                        JOptionPane.showMessageDialog(UneVoiture.this, "La date de fin ne peut pas être antérieure à la date de départ.", "Date invalide", JOptionPane.ERROR_MESSAGE);
                    } else {
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

        // Ajouter les menus déroulants pour l'heure de départ et de retour
        JPanel timePanel = new JPanel(new GridLayout(2, 2));
        timePanel.add(new JLabel("Heure de départ:"));
        startTimeComboBox = new JComboBox<>(generateTimeOptions());
        timePanel.add(startTimeComboBox);
        timePanel.add(new JLabel("Heure de retour:"));
        endTimeComboBox = new JComboBox<>(generateTimeOptions());
        timePanel.add(endTimeComboBox);

        calendarPanel.add(buttonPanel, BorderLayout.EAST);
        calendarPanel.add(timePanel, BorderLayout.WEST);

        // JTextArea pour afficher les dates sélectionnées
        selectedDatesArea = new JTextArea("Date de début:\nDate de fin:");
        selectedDatesArea.setEditable(false);
        calendarPanel.add(selectedDatesArea, BorderLayout.SOUTH);

        mainPanel.add(carouselPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        mainPanel.add(calendarPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Afficher la première image au démarrage
        showImage(0);
    }

    private String[] generateTimeOptions() {
        String[] options = new String[48];
        for (int i = 0; i < 24; i++) {
            options[2 * i] = String.format("%02d:00", i);
            options[2 * i + 1] = String.format("%02d:30", i);
        }
        return options;
    }

    private void showImage(int index) {
        if (index >= 0 && index < images.size()) {
            currentImageIndex = index;
            // Charger l'image correspondante
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(images.get(index)));
            int w = imageIcon.getIconWidth();
            int h = imageIcon.getIconHeight();
            double wh = (double) w / h;
            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(1000, (int) (1000 / wh), Image.SCALE_SMOOTH));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String startDateString = selectedStartDate != null ? dateFormat.format(selectedStartDate) : "Non sélectionnée";
        String endDateString = selectedEndDate != null ? dateFormat.format(selectedEndDate) : "Non sélectionnée";

        selectedDatesArea.setText("Date de début: " + startDateString + "\nDate de fin: " + endDateString);
    }

    private void colorSelectedDates() {
        if (selectedStartDate != null && selectedEndDate != null) {
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(selectedStartDate);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(selectedEndDate);

            for (Component comp : dayChooser.getDayPanel().getComponents()) {
                comp.setBackground(UIManager.getColor("Panel.background")); // Réinitialiser la couleur
            }

            while (!startCal.after(endCal)) {
                int day = startCal.get(Calendar.DAY_OF_MONTH);
                Component dayComponent = dayChooser.getDayPanel().getComponent(day - 1);
                if (dayComponent != null) {
                    dayComponent.setBackground(new Color(200, 216, 230));
                }
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Exemple d'utilisation
            String titre = "Voiture à louer";
            String description = "Super voiture en excellent état !";
            ArrayList<String> images = new ArrayList<>();
            images.add("renault-clio-2 (1).png");
            images.add("renault_PNG1.png");
            images.add("cover-r4x3w1200-5798f0940a24d-renault-clio-iii-collection-2012.jpg");
            new UneVoiture(description, images, 100, 2020);
        });
    }
    public void processReservation(Voiture voiture, Reservation reservation) throws SQLException {
        // Récupérer l'ID de la voiture
        int idVoiture = voiture.getId_voiture();
        VoitureDaoImpl voitureDaoImpl = new VoitureDaoImpl(connection);

        // Modifier le statut de la voiture
        voiture.setId_voiture(idVoiture);
        modifVoiture(voiture);

        // Créer la réservation
        reservation.setIdVoiture(idVoiture);
        addReservation(reservation);
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
