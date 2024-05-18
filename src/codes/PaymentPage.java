package codes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.time.YearMonth;

public class PaymentPage {

    public static void main(String[] args) {
        int X = 5; // Exemple de nombre de jours de location
        String Y = "SUV"; // Exemple de type de voiture
        double Z = 200.50; // Exemple de montant total

        createPaymentPage(X, Y, Z);
    }

    public static void createPaymentPage(int X, String Y, double Z) {
        // Créer le frame principal
        JFrame frame = new JFrame("Paiement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridLayout(4, 1));

        // Titre
        JLabel titleLabel = new JLabel("Paiement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel);

        // Montant à régler
        JLabel amountLabel = new JLabel("Le montant à régler pour " + X + " jours de location d'une voiture de type " + Y + " est de " + Z + " €", SwingConstants.CENTER);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(amountLabel);

        // Carte bancaire
        JPanel cardPanel = new JPanel(new GridLayout(5, 2, 10, 2));
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

        frame.add(cardPanel);

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

        frame.add(paymentOptionsPanel);

        // Bouton de paiement
        JButton payButton = new JButton("Payer");
        frame.add(payButton);

        googlePayButton.addActionListener(e -> openUrl("https://pay.google.com"));
        payPalButton.addActionListener(e -> openUrl("https://www.paypal.com"));

        payButton.addActionListener(e -> {
            boolean isValid = true;

            String cardNumber = cardNumberField.getText().trim();
            String expirationDate = expirationDateField.getText().trim();
            String securityCode = securityCodeField.getText().trim();
            String cardHolderName = cardHolderNameField.getText().trim();
            StringBuilder errorMessage = new StringBuilder("Veuillez corriger les erreurs suivantes:\n");

            if (!validateCardNumber(cardNumber)) {
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

            if (securityCode.equals("123") || securityCode.isEmpty()) {
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
                JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un mode de paiement");
                isValid = false;
            }

            if (isValid) {
                JOptionPane.showMessageDialog(frame, "Paiement effectué!");
            } else {
                JOptionPane.showMessageDialog(frame, errorMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Afficher la fenêtre
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
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
                    return string.matches("[a-zA-Z\\-\\.\\s]*");
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
        textField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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
}
