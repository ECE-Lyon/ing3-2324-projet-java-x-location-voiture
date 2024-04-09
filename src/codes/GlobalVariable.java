package codes;

import java.awt.*;

public class GlobalVariable {
    private static Dimension screenSize;
    private static int screenWidth;
    private static int screenHeight;

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(Dimension screenSize) {
        GlobalVariable.screenSize = screenSize;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        GlobalVariable.screenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        GlobalVariable.screenHeight = screenHeight;
    }
}
