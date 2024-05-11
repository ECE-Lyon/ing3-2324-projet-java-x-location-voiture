package codes;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main extends JFrame {


    public static void main(String[] args) throws SQLException {
        // Obtenir le Toolkit par défaut
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Obtenir la dimension de l'écran en pixels
        Dimension screenSize = toolkit.getScreenSize();

        GlobalVariable.setScreenSize(screenSize);
        GlobalVariable.setScreenHeight((int)screenSize.getHeight());
        GlobalVariable.setScreenWidth((int)screenSize.getWidth());

        MainJFrame mainWindow = new MainJFrame();
    }
}