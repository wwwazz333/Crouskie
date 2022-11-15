package crouskiebackoffice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static ConnectionDB instance;

    private Connection con;

    public Connection getConnection() {
        return con;
    }

    private ConnectionDB() throws SQLException {
        System.out.println("Ouverture de la connection sql");
        con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
//        con = DriverManager.getConnection("jdbc:mysql://iutdoua-web.univ-lyon1.fr/p2100284", "p2100284", "613689");
    }

    public static ConnectionDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public void close() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Fermeture de la connection sql");
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de donnée");
            }
        }
    }

}
