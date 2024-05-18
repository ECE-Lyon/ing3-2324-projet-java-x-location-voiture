package codes;

import codes.dao.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MAJImage extends JDialog implements ActionListener {
    private JTextField modeleIdField;
    private JButton selectImageButton;
    private JLabel selectedImageLabel;
    private JButton insertOrUpdateImageButton;
    private JComboBox<String> columnComboBox; // Ajout d'une comboBox pour sélectionner la colonne
    private JFileChooser fileChooser;

    private Connection connection;


    public MAJImage() {
        setTitle("Image Uploader");

        modeleIdField = new JTextField(10);
        selectImageButton = new JButton("Sélectionner une image");
        selectImageButton.addActionListener(this);
        selectedImageLabel = new JLabel();
        insertOrUpdateImageButton = new JButton("Insérer/Mettre à jour l'image");
        insertOrUpdateImageButton.addActionListener(this);
        columnComboBox = new JComboBox<>(new String[]{"image1", "image2", "image3"});

        fileChooser = new JFileChooser();

        JPanel panel = new JPanel(new GridLayout(4, 1));
        JPanel idPanel = new JPanel();
        idPanel.add(new JLabel("ID du modèle : "));
        idPanel.add(modeleIdField);
        panel.add(idPanel);
        JPanel imagePanel = new JPanel();
        imagePanel.add(selectImageButton);
        imagePanel.add(selectedImageLabel);
        panel.add(imagePanel);
        JPanel columnPanel = new JPanel();
        columnPanel.add(new JLabel("Choisir la colonne : "));
        columnPanel.add(columnComboBox);
        panel.add(columnPanel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertOrUpdateImageButton);
        panel.add(buttonPanel);
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Establish database connection
        try {
            connection = Mysql.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Impossible d'établir une connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectImageButton) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                displaySelectedImage(selectedFile);
            }
        } else if (e.getSource() == insertOrUpdateImageButton) {
            String modeleId = modeleIdField.getText();
            String column = (String) columnComboBox.getSelectedItem(); // Récupération de la colonne sélectionnée
            if (!modeleId.isEmpty() && column != null) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    insertOrUpdateImage(Integer.parseInt(modeleId), selectedFile, column); // Appel à la méthode avec la colonne
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez saisir l'ID du modèle et sélectionner une colonne.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displaySelectedImage(File file) {
        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        selectedImageLabel.setIcon(new ImageIcon(image));
    }

    private void insertOrUpdateImage(int modeleId, File file, String column) {
        try {
            byte[] imageData = readImageFromFile(file);
            if (imageData != null) {
                if (isModeleIdExists(connection, modeleId)) {
                    updateImage(connection, modeleId, imageData, column);
                } else {
                    insertImage(connection, modeleId, imageData, column);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Impossible de lire l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'insertion ou de la mise à jour de l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isModeleIdExists(Connection connection, int modeleId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM modele WHERE id = ?";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, modeleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private void insertImage(Connection connection, int modeleId, byte[] imageData, String column) throws SQLException {
        String sql = "INSERT INTO modele (id, nom, " + column + ") VALUES (?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, modeleId);
            statement.setString(2, null); // Remplacez "Nom du modèle" par la valeur souhaitée
            statement.setBytes(3, imageData);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Image insérée avec succès dans la base de données.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void updateImage(Connection connection, int modeleId, byte[] imageData, String column) throws SQLException {
        String sql = "UPDATE modele SET " + column + " = ? WHERE id = ?";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            statement.setBytes(1, imageData);
            statement.setInt(2, modeleId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Image mise à jour avec succès dans la base de données.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aucune ligne mise à jour dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private byte[] readImageFromFile(File file) throws IOException {
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(imageData);
        }
        return imageData;
    }

}


