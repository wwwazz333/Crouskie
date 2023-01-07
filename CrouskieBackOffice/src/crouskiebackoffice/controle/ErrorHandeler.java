package crouskiebackoffice.controle;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.CanCrash;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe singleton pour gérer les erreurs dans une interface utilisateur.
 * Utilise une fenêtre parent et un JOptionPane pour afficher des messages
 * d'erreur. Peut être initialisée avec une fenêtre parent à l'aide de la
 * méthode init(). Utilise la méthode exec() pour exécuter du code qui peut
 * lancer une exception et afficher un message d'erreur en cas d'erreur.
 */
public class ErrorHandeler {

    // Instance unique de la classe
    private static ErrorHandeler instance;

    /**
     * Obtient l'instance unique de la classe.
     *
     * @return L'instance unique de la classe
     */
    public static ErrorHandeler getInstance() {
        if (instance == null) {
            instance = new ErrorHandeler();
        }
        return instance;
    }

    // Indicateur de l'état d'initialisation de la classe
    private boolean inisialized = false;
    // Fenêtre parent pour afficher les messages d'erreur
    private JFrame parent;

    // Constructeur privé pour empêcher l'instanciation directe de la classe
    private ErrorHandeler() {
    }

    /**
     * Initialise la classe avec une fenêtre parent.
     *
     * @param mainWindow Fenêtre parent
     */
    public void init(JFrame mainWindow) {
        this.parent = mainWindow;
        inisialized = true;
    }

    /**
     * Exécute du code qui peut lancer une exception et affiche un message
     * d'erreur en cas d'erreur. Si la classe n'a pas été initialisée avec une
     * fenêtre parent, lance une exception RuntimeException.
     *
     * @param canCrash Code à exécuter
     * @return Le retour de la méthode run() de l'objet canCrash ou false en cas
     * d'erreur
     */
    public boolean exec(CanCrash canCrash) {
        // Vérifier si la classe a été initialisée avec une fenêtre parent
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
