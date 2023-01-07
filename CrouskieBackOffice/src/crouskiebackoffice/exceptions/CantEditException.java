package crouskiebackoffice.exceptions;

import java.sql.SQLException;

/**
 * Exception levée lorsqu'il est interdit d'éditer quelque chose.
 */
public class CantEditException extends SQLException {

    /**
     * Crée une nouvelle instance de CantEditException.
     * @param what La chose qui ne peut pas être éditée
     */
    public CantEditException(String what) {
        super("Edition de " + what + " non autorisée");
    }
}
