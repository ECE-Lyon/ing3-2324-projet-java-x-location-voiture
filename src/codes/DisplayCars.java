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

    private ArrayList<ImageIcon> images1 = new ArrayList<>();
    private ArrayList<ImageIcon> images2 = new ArrayList<>();
    private ArrayList<ImageIcon> images3 = new ArrayList<>();
    private ArrayList<Integer> id = new ArrayList<>();
    private MainJFrame mainJFrame;

    public DisplayCars(MainJFrame mainJFrame) throws SQLException {
        connection = Mysql.openConnection();
        this.mainJFrame = mainJFrame;
        updateImages();
    }

    public void updateImages() {
        switch (this.mainJFrame.getFilter()) {
            case "No filter":
                this.id.clear();
                this.images1.clear();
                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try (PreparedStatement statement = connection.prepareStatement("SELECT id, image1, image2, image3 FROM modele")) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        boolean found = false;
                        while (resultSet.next()) {
                            int carId = resultSet.getInt("id");
                            byte[] imageData = resultSet.getBytes("image1");
                            byte[] imageData2 = resultSet.getBytes("image2");
                            byte[] imageData3 = resultSet.getBytes("image3");


                            if (imageData != null && imageData.length > 0) {
                                /////////////////////////////////////

                                //////////////////AJOUTER UNE VAR QUI S'INCREMENTE ET RANGER LES IMAGES DANS UN TABLEAU AU LIEU DE LES AFFCHER
                                id.add(carId);
                                images1.add(toImageIcon(obtenirImage(imageData)));
                                Image image = obtenirImage(imageData);
                                images2.add(toImageIcon(obtenirImage(imageData2)));
                                Image image2 = obtenirImage(imageData2);
                                images3.add(toImageIcon(obtenirImage(imageData3)));
                                Image image3 = obtenirImage(imageData3);
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
                break;
            case "BMW":
                break;
            case "AUDI":
                break;
            case "Prix croissant":
                break;
            case "Prix décroissant":
                break;
            case "Berline":
                break;
            case "SUV":
                break;
            case "Sport":
                break;
            case "Limousine":
                break;
            case "Pick-up":
                break;

        }
    }

    public Image obtenirImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();
        return bufferedImage;
    }


    public ArrayList<ImageIcon> getImages2() {
        return images2;
    }

    public void setImages2(ArrayList<ImageIcon> images2) {
        this.images2 = images2;
    }

    public ArrayList<ImageIcon> getImages3() {
        return images3;
    }

    public void setImages3(ArrayList<ImageIcon> images3) {
        this.images3 = images3;
    }

    public ArrayList<ImageIcon> getImages1() {
        return images1;
    }

    public void setImages1(ArrayList<ImageIcon> images1) {
        this.images1 = images1;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }

    public ImageIcon toImageIcon(Image img) {
        ImageIcon imgI = new ImageIcon(img);
        return imgI;
    }
}
