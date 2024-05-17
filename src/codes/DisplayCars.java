package codes;

import codes.dao.Mysql;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayCars {

    private static Connection connection;

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }

    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Integer> id = new ArrayList<>();

    public DisplayCars () throws SQLException {


        connection = Mysql.openConnection();
        updateImages();
    }

    public void updateImages() {
        this.id.clear();
        this.images.clear();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, image1 FROM modele")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    int carId = resultSet.getInt("id");
                    byte[] imageData = resultSet.getBytes("image1");



                    if (imageData != null && imageData.length > 0) {
                    /////////////////////////////////////

                        //////////////////AJOUTER UNE VAR QUI S'INCREMENTE ET RANGER LES IMAGES DANS UN TABLEAU AU LIEU DE LES AFFCHER
                        id.add(carId);
                        images.add(obtenirImage(imageData));
                        Image image = obtenirImage(imageData);
                        found = true;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Aucune image trouvée pour les voitures dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la récupération des images des voitures.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Image obtenirImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();
        return bufferedImage;
    }
}
