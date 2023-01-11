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
     * Code de la couleur, pour la représenté.
     */
    private final String code;

    /**
     * Constructeur de la couleur.
     *
     * @param name Nom de la couleur.
     */
    public Color(String name) {
        this.name = name;
        this.code = null;
    }

    /**
     * Constructeur de la couleur.
     *
     * @param name Nom de la couleur.
     * @param code Code represantant la couleur
     */
    public Color(String name, String code) {
        this.name = name;
        this.code = code;
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
     * Retourne le code de la couleur.
     *
     * @return Le code de la couleur
     */
    public String getCode() {
        return code;
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
