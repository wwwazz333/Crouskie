package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorConnectionSQL;
import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe permettant de gérer la connexion à la base de données.
 */
public class ConnectionDB {

    /**
     * Instance de la classe (pattern singleton).
     */
    private static ConnectionDB instance;
    /**
     * Objet de connexion à la base de données.
     */
    private Connection con;

    /**
     * Renvoie l'objet de connexion à la base de données.
     *
     * @return Objet de connexion à la base de données.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Constructeur privé de la classe (pattern singleton). Ouvre la connexion à
     * la base de données.
     *
     * @throws SQLException
     * @throws ErrorHandelabelAdapter
     */
    private ConnectionDB() throws SQLException, ErrorHandelabelAdapter {
        System.out.println("Ouverture de la connection sql");
        try {
            con = DriverManager.getConnection("jdbc:mysql://menardbediant.fr:8002/crouskie", "root", "thesaurus-cranberry-reptile");
        } catch (com.mysql.cj.jdbc.exceptions.CommunicationsException e) {
            throw new ErrorConnectionSQL();
        }

//        con = DriverManager.getConnection("jdbc:mysql://iutdoua-web.univ-lyon1.fr/p2100284", "p2100284", "613689");
    }

    /**
     * Renvoie l'instance de la classe (pattern singleton).
     *
     * @return Instance de la classe.
     * @throws SQLException
     * @throws ErrorHandelabelAdapter
     */
    public static ConnectionDB getInstance() throws SQLException, ErrorHandelabelAdapter {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    /**
     * Ferme la connexion à la base de données.
     */
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
