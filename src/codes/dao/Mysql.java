package codes.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Mysql {

    private static String database = "reservationvoiture";
    private static String user = "root";
    private static String password = "root";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/";

    private static Connection connection = null;

    public static Connection openConnection() throws SQLException {
        if(connection == null) {
            try {
                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(url + database, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                System.err.println("Impossible de sauvegarder les données. La base de données ne répond pas.");
                ex.printStackTrace();

            }
        }
        return connection;
    }
}
