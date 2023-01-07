package crouskiebackoffice.model;

/**
 * Classe représentant une couleur.
 *
 * @author Nom de l'auteur
 */
public class Color implements HasName {

    /**
     * Nom de la couleur.
     */
    private final String name;

    /**
     * Constructeur de la couleur.
     *
     * @param name Nom de la couleur.
     */
    public Color(String name) {
        this.name = name;
    }

    /**
     * Retourne le nom de la couleur.
     *
     * @return Le nom de la couleur.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retourne le nom de la couleur.
     *
     * @return Le nom de la couleur.
     */
    @Override
    public String toString() {
        return name;
    }
}
