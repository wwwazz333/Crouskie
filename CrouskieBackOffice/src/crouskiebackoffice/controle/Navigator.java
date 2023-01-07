package crouskiebackoffice.controle;

import java.awt.CardLayout;
import java.util.Stack;
import javax.swing.JPanel;

/**
 * Classe singleton permettant de naviguer entre différents écrans dans une
 * interface utilisateur en utilisant un CardLayout et une pile de JPanel. Peut
 * être initialisée avec un JPanel et un nom de départ à l'aide de la méthode
 * init(). Utilise les méthodes goTo() pour ajouter un nouvel écran à la pile et
 * afficher celui-ci, et goBack() pour retourner à l'écran précédent.
 */
public class Navigator {

    // Instance unique de la classe
    private static Navigator instance;

    /**
     * Obtient l'instance unique de la classe.
     *
     * @return L'instance unique de la classe
     */
    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    // JPanel utilisé pour afficher les écrans
    private JPanel cardPanel;
    // CardLayout utilisé pour afficher les écrans
    private CardLayout cardLayout;
    // Pile de JPanel contenant les écrans
    private final Stack<JPanel> path = new Stack<>();
    // Pile de String contenant les noms des écrans
    private final Stack<String> pathName = new Stack<>();

    public Navigator() {
    }

    /**
     * Initialise la classe avec un JPanel et un nom de départ.
     *
     * @param cardPanel Le JPanel utilisé pour afficher les écrans
     * @param root Le nom de l'écran de départ
     */
    public void init(JPanel cardPanel, String root) {
        // Enregistrer le JPanel et le CardLayout
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        // Ajouter l'écran de départ à la pile et l'afficher
        path.push(cardPanel);
        pathName.push(root);
    }

    /**
     * Ajoute un nouvel écran à la pile et l'affiche.
     *
     * @param panel Le nouvel écran à afficher
     * @param name Le nom de l'écran
     */
    public void goTo(JPanel panel, String name) {
        // Ajouter l'écran à la pile
        path.push(panel);
        pathName.push(name);
        // Ajouter l'écran au JPanel et l'afficher
        cardPanel.add(pathName.lastElement(), path.lastElement());
        cardLayout.show(cardPanel, pathName.lastElement());
    }

    /**
     * Retourne à l'écran précédent de la pile.
     */
    public void goBack() {
        // Si la pile contient plus d'un écran, retourner à l'écran précédent
        if (path.size() > 1) {
            path.pop();
            pathName.pop();
            cardLayout.show(cardPanel, pathName.lastElement());
        } else {
            // Sinon, afficher un message indiquant qu'il est impossible d'aller plus loin en arrière
            System.out.println("Imposible d'aller en arrière, il n'y a plus rien de la pile");
        }
    }
}
