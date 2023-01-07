package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.OnCrash;
import crouskiebackoffice.view.MainWindow;

/**
 * Exception levée lorsqu'il est impossible de se connecter à la base de données.
 */
public class ErrorConnectionSQL extends ErrorHandelabelAdapter implements OnCrash {

    /**
     * Crée une nouvelle instance de ErrorConnectionSQL.
     */
    public ErrorConnectionSQL() {
        super("Impossible de se connecter à la base de données.");
    }

    @Override
    public void crashed() {
        System.out.println(MainWindow.instance);
        if (MainWindow.instance != null) {
            MainWindow.instance.dispose();
        }
    }
}
