package crouskiebackoffice.controle;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.CanCrash;
import javax.swing.JFrame;

public class ErrorHandeler {

    public static ErrorHandeler instance;

    public ErrorHandeler getInstance() {
        if (instance == null) {
            instance = new ErrorHandeler();
        }
        return instance;
    }

    private boolean inisialized = false;
    private JFrame parent;

    private ErrorHandeler() {
    }

    public void init(JFrame mainWindow) {
        this.parent = mainWindow;
        inisialized = true;
    }

    public void exec(CanCrash canCrash) {
        if (!inisialized) {
            throw new RuntimeException("ErrorHandeler n'a pas été initializer");
        }
        try {
            canCrash.run();
        } catch (ErrorHandelabelAdapter error) {

        } catch (Exception error) {

        }
    }
}
