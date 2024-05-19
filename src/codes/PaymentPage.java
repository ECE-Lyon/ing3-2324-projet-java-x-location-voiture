package codes;

import codes.dao.Mysql;
import codes.dao.ReservationDaoImpl;
import codes.dao.UtilisateurDaoImpl;
import codes.dao.VoitureDaoImpl;
import codes.model.Client;
import codes.model.Reservation;
import codes.model.Utilisateur;
import codes.model.Voiture;
import codes.services.ServicePaiementReservation;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;

public class PaymentPage extends JPanel implements ActionListener {


    MainJFrame mainJFrame;
    private JPanel mainPanel = new JPanel();
    private int nbJour = 10;
    String typeVehicule = "sfdfqsddqs";
    double prixParJour = 36;
    double prix = 360;

    private Connection connection;

    public PaymentPage (MainJFrame mainJFrame) throws SQLException {
        this.mainJFrame = mainJFrame;
        this.createPaymentPage();
    }

    public void payer (){
        try {

            UtilisateurDaoImpl dao = new UtilisateurDaoImpl(connection);
            ReservationDaoImpl reservationDao = new ReservationDaoImpl(connection);
            VoitureDaoImpl voitureDao = new VoitureDaoImpl(connection);
            ServicePaiementReservation serviceReservation = new ServicePaiementReservation(connection);

            Reservation reservation = reservationDao.getReservation(5);
            Voiture voiture = voitureDao.getVoiture(2);

            serviceReservation.effectuerPaiement(reservation, voiture);

            codes.model.Client client = (Client) this.mainJFrame.getUtilisateur();
            dao.updateStatutClient(client);

            System.out.println("Paiement effectué avec succès !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPaymentPage() throws SQLException {
        // Créer le frame principal
        this.setLayout(new BorderLayout());
        this.mainPanel.setLayout(new GridLayout(6,1));

        this.connection = Mysql.openConnection();


        // Titre
        JLabel titleLabel = new JLabel("Paiement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));


        // Montant à régler
        JLabel amountLabel = new JLabel("Le montant à régler pour " + nbJour + " jours de location d'une voiture de type " + typeVehicule + " est de " + prix + " €", SwingConstants.CENTER);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.mainPanel.add(amountLabel);

        // Options de paiement
        JPanel paymentOptionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton cardPaymentButton = new JRadioButton("Carte bancaire");
        JRadioButton googlePayButton = new JRadioButton("Google Pay");
        JRadioButton payPalButton = new JRadioButton("PayPal");

        paymentOptionsPanel.add(cardPaymentButton);
        paymentOptionsPanel.add(googlePayButton);
        paymentOptionsPanel.add(payPalButton);

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cardPaymentButton);
        paymentGroup.add(googlePayButton);
        paymentGroup.add(payPalButton);

        mainPanel.add(paymentOptionsPanel);

        // Carte bancaire
        JPanel cardPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // Ajustement de l'espace entre les lignes
        cardPanel.setBorder(BorderFactory.createTitledBorder("Détails de la carte"));

        JTextField cardNumberField = createHintTextField("1234 5678 9012 3456");
        JTextField expirationDateField = createHintTextField("MM/AA");
        JTextField securityCodeField = createHintTextField("123");
        JTextField cardHolderNameField = createHintTextField("J. Smith");

        addDocumentFilter(cardNumberField, 16, true, false, "#### #### #### ####");
        addDocumentFilter(expirationDateField, 5, true, false, "##/##");
        addDocumentFilter(securityCodeField, 3, true, false, null);
        addDocumentFilter(cardHolderNameField, 26, false, true, null);

        cardPanel.add(new JLabel("Numéro de la carte:"));
        cardPanel.add(cardNumberField);

        cardPanel.add(new JLabel("Date d'expiration:"));
        cardPanel.add(expirationDateField);

        cardPanel.add(new JLabel("Code de sécurité:"));
        cardPanel.add(securityCodeField);

        cardPanel.add(new JLabel("Nom sur la carte:"));
        cardPanel.add(cardHolderNameField);

        mainPanel.add(cardPanel);

        // Bouton de paiement

        JButton payButton = new JButton("Payez !");
        payButton.setActionCommand("PAYER");
        payButton.addActionListener(this);
        payButton.setPreferredSize(new Dimension(100, 50)); // Réduire la taille du bouton
        payButton.setFont(new Font("Arial", Font.BOLD, 16)); // Taille du texte
        payButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));



        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payButton.setBackground(Color.LIGHT_GRAY); // Couleur de fond sur hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                payButton.setBackground(Color.WHITE); // Réinitialiser la couleur de fond
            }
        });

        this.add(mainPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButton);
        mainPanel.add(buttonPanel);

        googlePayButton.addActionListener(e -> openUrl("https://pay.google.com"));
        payPalButton.addActionListener(e -> openUrl("https://www.paypal.com"));

        payButton.addActionListener(e -> {
            boolean isValid = true;

            String cardNumber = cardNumberField.getText().trim();
            String expirationDate = expirationDateField.getText().trim();
            String securityCode = securityCodeField.getText().trim();
            String cardHolderName = cardHolderNameField.getText().trim();
            StringBuilder errorMessage = new StringBuilder("Veuillez corriger les erreurs suivantes:\n");

            if (!validateCardNumber(cardNumber) || cardNumber.equals("1234 5678 9012 3456") ) {
                setErrorBorder(cardNumberField, "Numéro de carte invalide");
                errorMessage.append("- Numéro de carte invalide\n");
                isValid = false;
            } else {
                resetErrorBorder(cardNumberField);
            }

            if (!validateExpirationDate(expirationDate)) {
                setErrorBorder(expirationDateField, "Date d'expiration invalide ou dépassée");
                errorMessage.append("- Date d'expiration invalide ou dépassée\n");
                isValid = false;
            } else {
                resetErrorBorder(expirationDateField);
            }

            if (!validateSecurityCode(securityCode)) {
                setErrorBorder(securityCodeField, "Code de sécurité invalide");
                errorMessage.append("- Code de sécurité invalide\n");
                isValid = false;
            } else {
                resetErrorBorder(securityCodeField);
            }

            if (cardHolderName.equals("J. Smith") || cardHolderName.isEmpty()) {
                setErrorBorder(cardHolderNameField, "Le nom sur la carte est requis");
                errorMessage.append("- Le nom sur la carte est requis\n");
                isValid = false;
            } else {
                resetErrorBorder(cardHolderNameField);
            }

            if (!cardPaymentButton.isSelected() && !googlePayButton.isSelected() && !payPalButton.isSelected()) {
                JOptionPane.showMessageDialog(this.mainJFrame, "Veuillez sélectionner un mode de paiement");
                isValid = false;
            }

            if (isValid) {
                JOptionPane.showMessageDialog(this.mainJFrame, "Paiement effectué!");
            } else {
                JOptionPane.showMessageDialog(this.mainJFrame, errorMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Ajouter un KeyListener à chaque champ de texte pour détecter les saisies au clavier
        cardNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                cardNumberField.setForeground(Color.BLACK);
            }
        });

        expirationDateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                expirationDateField.setForeground(Color.BLACK);
            }
        });

        securityCodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                securityCodeField.setForeground(Color.BLACK);
            }
        });

        cardHolderNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                cardHolderNameField.setForeground(Color.BLACK);
            }
        });

        // Assurer que le champ "Numéro de carte" n'est pas sélectionné au chargement
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
            this.getRootPane().requestFocus(); // Assurer que le focus n'est pas sur le champ de numéro de carte
        });
    }

    private static JTextField createHintTextField(String hint) {
        JTextField textField = new JTextField(hint);
        textField.setForeground(Color.LIGHT_GRAY);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        return textField;
    }

    private static void addDocumentFilter(JTextField textField, int maxLength, boolean digitsOnly, boolean lettersOnly, String format) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null || !isValidString(string)) return;
                if (doc.getLength() + string.length() <= maxLength) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
                if (string == null || !isValidString(string)) return;
                if (doc.getLength() - length + string.length() <= maxLength) {
                    super.replace(fb, offset, length, string, attrs);
                }
            }

            private boolean isValidString(String string) {
                if (digitsOnly) {
                    return string.matches("[\\d/]*");
                }
                if (lettersOnly) {
                    return string.matches("[\\p{L}\\-\\.\\s]*"); // Ajout des caractères accentués
                }
                return true;
            }
        });
    }

    private static void openUrl(String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void setErrorBorder(JTextField textField, String message) {
        textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        textField.setToolTipText(message);
    }

    private static void resetErrorBorder(JTextField textField) {
        textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        textField.setToolTipText(null);
    }

    private static boolean validateCardNumber(String cardNumber) {
        return cardNumber.replaceAll("\\s", "").matches("\\d{16}");
    }

    private static boolean validateExpirationDate(String expirationDate) {
        try {
            String[] parts = expirationDate.split("/");
            if (parts.length != 2) return false;

            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]) + 2000;
            if (month < 1 || month > 12) return false;

            YearMonth currentYearMonth = YearMonth.now();
            YearMonth cardYearMonth = YearMonth.of(year, month);
            return cardYearMonth.isAfter(currentYearMonth);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean validateSecurityCode(String securityCode) {
        return securityCode.matches("\\d{3}");
    }

    public void resetMainContent() {
        mainJFrame.getFrame().getContentPane().removeAll();
        System.out.println("7777777777");
        /*this.nbJour = nbJour;
        this.typeVehicule = typeVehicule;
        this.prixParJour = prixParJour;*/
        prix = 360;


        mainJFrame.getFrame().getContentPane().add(this, BorderLayout.CENTER);

        mainJFrame.getFrame().revalidate();
        mainJFrame.getFrame().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "PAYER":
                payer();
                break;
        }
    }
}
