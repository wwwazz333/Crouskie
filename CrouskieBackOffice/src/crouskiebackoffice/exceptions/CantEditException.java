package crouskiebackoffice.exceptions;

import java.sql.SQLException;

public class CantEditException extends SQLException {

    public CantEditException(String what) {
        super("Editiont of " + what + " not allowed");
    }

}
