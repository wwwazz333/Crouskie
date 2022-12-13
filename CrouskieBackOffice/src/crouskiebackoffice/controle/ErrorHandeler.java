package crouskiebackoffice.controle;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.CanCrash;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorHandeler {

    private static ErrorHandeler instance;

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
     *
     * 
     * renvoi le retour de la fonction canCrash ou false si il y à une erreur
     * @param canCrash Se qu'il faut tester
     */
    public boolean exec(CanCrash canCrash) {
        if (!inisialized) {
            throw new RuntimeException("ErrorHandeler n'a pas été initializer");
        }
        try {
            return canCrash.run();
        } catch (ErrorHandelabelAdapter error) {
            JOptionPane.showMessageDialog(parent, error.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            error.crashed();
            return false;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(parent, error.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;

        }
    }
}
