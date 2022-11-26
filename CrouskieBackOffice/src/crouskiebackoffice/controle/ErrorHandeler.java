package crouskiebackoffice.controle;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.CanCrash;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorHandeler {

    public static ErrorHandeler instance;

    public static ErrorHandeler getInstance() {
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

    /**
     * ErrorHandeler doit être initializer a l'aide de {@link init}
     * @param canCrash Se qu'il faut tester
     */
    public void exec(CanCrash canCrash) {
        if (!inisialized) {
            throw new RuntimeException("ErrorHandeler n'a pas été initializer");
        }
        try {
            canCrash.run();
        } catch (ErrorHandelabelAdapter error) {

            JOptionPane.showMessageDialog(parent, error.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            error.crashed();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(parent, error.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

        }
    }
}
