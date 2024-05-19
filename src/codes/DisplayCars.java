package codes;

import codes.dao.Mysql;
import codes.dao.Type_voitureDaoImpl;
import codes.model.Client;
import codes.model.Type_voiture;
import codes.model.Voiture;
import com.mysql.cj.conf.ConnectionUrlParser;

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
import java.util.List;
import java.util.Set;

public class DisplayCars {

    private static Connection connection;

    private ArrayList<ImageIcon> images1 = new ArrayList<>();
    private ArrayList<ImageIcon> images2 = new ArrayList<>();
    private ArrayList<ImageIcon> images3 = new ArrayList<>();
    private ArrayList<Integer> id = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();



    private ArrayList<Type_voiture.Type> types = new ArrayList<>();



    private MainJFrame mainJFrame;

    public DisplayCars(MainJFrame mainJFrame) throws SQLException {
        connection = Mysql.openConnection();
        this.mainJFrame = mainJFrame;
        updateImages();
    }

    public void updateImages() {
        this.id.clear();
        this.images1.clear();
        this.images2.clear();
        this.images3.clear();
        this.description.clear();
        this.types.clear();
        switch (this.mainJFrame.getFilter()) {
            case "No filter":
                try (PreparedStatement statement = connection.prepareStatement("SELECT id, image1, image2, image3, type, description FROM modele")) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int carId = resultSet.getInt("id");
                            byte[] imageData = resultSet.getBytes("image1");
                            byte[] imageData2 = resultSet.getBytes("image2");
                            byte[] imageData3 = resultSet.getBytes("image3");
                            Type_voiture.Type type = Type_voiture.Type.valueOf(resultSet.getString("type"));
                            String description = resultSet.getString("description");


                            if (imageData != null && imageData.length > 0) {
                                id.add(carId);
                                images1.add(toImageIcon(obtenirImage(imageData)));
                                images2.add(toImageIcon(obtenirImage(imageData2)));
                                images3.add(toImageIcon(obtenirImage(imageData3)));
                                types.add(type);
                            }
                        }
                    }
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la récupération des images des voitures.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "BMW":
                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String marque = "bmw";
                    List<Type_voiture> modeles = modeleDao.searchModele(marque);

                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "AUDI":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String marque = "audi";
                    List<Type_voiture> modeles = modeleDao.searchModele(marque);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Prix croissant":

                try {
                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> modeleVoiturePairs = modeleDao.searchAllModeleASCPrice();

                    if (!modeleVoiturePairs.isEmpty()) {
                        for (ConnectionUrlParser.Pair<Type_voiture, Voiture> modeleVoiturePair : modeleVoiturePairs) {
                            Type_voiture typeVoiture = modeleVoiturePair.left;
                            Voiture voiture = modeleVoiturePair.right;

                            if (typeVoiture.getImage1() != null && typeVoiture.getImage1().length > 0) {
                                id.add(typeVoiture.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(typeVoiture.getImage1())));
                                images2.add(toImageIcon(obtenirImage(typeVoiture.getImage2())));
                                images3.add(toImageIcon(obtenirImage(typeVoiture.getImage3())));
                                description.add(typeVoiture.getDescription());
                            }

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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Prix décroissant":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    List<ConnectionUrlParser.Pair<Type_voiture, Voiture>> modeleVoiturePairs = modeleDao.searchAllModeleDESCPrice();

                    if (!modeleVoiturePairs.isEmpty()) {
                        for (ConnectionUrlParser.Pair<Type_voiture, Voiture> modeleVoiturePair : modeleVoiturePairs) {
                            Type_voiture typeVoiture = modeleVoiturePair.left;
                            Voiture voiture = modeleVoiturePair.right;

                            if (typeVoiture.getImage1() != null && typeVoiture.getImage1().length > 0) {
                                id.add(typeVoiture.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(typeVoiture.getImage1())));
                                images2.add(toImageIcon(obtenirImage(typeVoiture.getImage2())));
                                images3.add(toImageIcon(obtenirImage(typeVoiture.getImage3())));
                                description.add(typeVoiture.getDescription());
                            }

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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Berline":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.BERLINE);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "SUV":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.SUV);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Sport":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.SPORT);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                            // Ajoutez d'autres informations si nécessaire
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Limousine":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.LIMOUSINE);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                            // Ajoutez d'autres informations si nécessaire
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Pick-up":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.PICK_UP);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());

                            }
                            // Ajoutez d'autres informations si nécessaire
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Break":

                try {

                    Type_voitureDaoImpl modeleDao = new Type_voitureDaoImpl(connection);

                    String type = String.valueOf(Type_voiture.Type.BREAK);
                    List<Type_voiture> modeles = modeleDao.searchType(type);
                    if (!modeles.isEmpty()) {
                        System.out.println("Modèles trouvés :");
                        for (Type_voiture modele : modeles) {
                            if (modele.getImage1() != null && modele.getImage1().length > 0) {
                                id.add(modele.getId_type_voiture());
                                images1.add(toImageIcon(obtenirImage(modele.getImage1())));
                                images2.add(toImageIcon(obtenirImage(modele.getImage2())));
                                images3.add(toImageIcon(obtenirImage(modele.getImage3())));
                                description.add(modele.getDescription());
                                types.add(modele.getType());
                            }
                            // Ajoutez d'autres informations si nécessaire
                        }
                    } else {
                        System.out.println("Aucun modèle trouvé pour la marque spécifiée.");
                    }

                } catch (SQLException er) {
                    er.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

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

    public ArrayList<Type_voiture.Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type_voiture.Type> types) {
        this.types = types;
    }
}
