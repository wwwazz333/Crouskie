package crouskiebackoffice.exceptions;

import crouskiebackoffice.model.OnCrash;
import crouskiebackoffice.view.MainWindow;

public class ErrorConnectionSQL extends ErrorHandelabelAdapter implements OnCrash {

    public ErrorConnectionSQL() {
        super("Impossible de se connecter à la base de données.");
    }

    @Override
    public void crashed() {
        System.out.println(MainWindow.instance );
        if (MainWindow.instance != null) {
            MainWindow.instance.dispose();
        }
    }

}
